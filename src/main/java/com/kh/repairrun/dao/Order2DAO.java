package com.kh.repairrun.dao;

import com.kh.repairrun.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order2DAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    //주문 현황 정보를 불러옴 (이중 맵)
    public List<Map<String,Object>> orderStatusList (int type, String id) {
        System.out.println("id? : " + id);
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            String[] sql = {
                    "SELECT DISTINCT O.ORDER_NUM_PK AS ORDER_NUM, P.PTN_NAME AS NAME, PI.REPAIR_DETAIL_FK AS REPAIR_DETAIL, " +
                            "PI.REPAIR_ITEM AS REPAIR_ITEM, TO_CHAR(O.ORDER_DATE, 'YYYY/MM/DD') AS ORDER_DATE, " +
                            "O.IMG_FULL AS IMG_FULL, O.IMG_DETAIL_01 AS IMG_DET_01, O.IMG_DETAIL_02 AS IMG_DET_02, " +
                            "O.IMG_DETAIL_03 AS IMG_DET_03, O.IMG_COMP AS IMG_COMP " +
                            "FROM ORDER_TB O " +
                            "JOIN PARTNER_TB P ON O.PTN_ID_FK = P.PTN_ID_PK " +
                            "JOIN PARTNER_ITEM_TB PI ON O.REPAIR_DETAIL_FK = PI.REPAIR_DETAIL_FK " +
                            "WHERE O.USER_ID_FK = ? " +
                            "ORDER BY O.ORDER_NUM_PK DESC",
                    "SELECT DISTINCT O.ORDER_NUM_PK AS ORDER_NUM, O.USER_ID_FK AS NAME, PI.REPAIR_DETAIL_FK AS REPAIR_DETAIL, " +
                            "PI.REPAIR_ITEM AS REPAIR_ITEM, TO_CHAR(O.ORDER_DATE, 'YYYY/MM/DD') AS ORDER_DATE, " +
                            "O.IMG_FULL AS IMG_FULL, O.IMG_DETAIL_01 AS IMG_DET_01, O.IMG_DETAIL_02 AS IMG_DET_02, " +
                            "O.IMG_DETAIL_03 AS IMG_DET_03, O.IMG_COMP AS IMG_COMP " +
                            "FROM ORDER_TB O " +
                            "JOIN PARTNER_TB P ON O.PTN_ID_FK = P.PTN_ID_PK " +
                            "JOIN PARTNER_ITEM_TB PI ON O.REPAIR_DETAIL_FK = PI.REPAIR_DETAIL_FK " +
                            "WHERE O.PTN_ID_FK = ? " +
                            "ORDER BY O.ORDER_NUM_PK DESC"
            };
            pstmt = conn.prepareStatement(sql[type]);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String orderNum = rs.getString("ORDER_NUM");
                String name = rs.getString("NAME");
                String repairDetail = rs.getString("REPAIR_DETAIL");
                String repairItem = rs.getString("REPAIR_ITEM");
                String requestDate = rs.getString("ORDER_DATE");
                String imgFull = rs.getString("IMG_FULL");
                String imgDet01 = rs.getString("IMG_DET_01");
                String imgDet02 = rs.getString("IMG_DET_02");
                String imgDet03 = rs.getString("IMG_DET_03");
                String imgComp = rs.getString("IMG_COMP");

                Map<String, Object> orderMap = new HashMap<>();

                orderMap.put("orderNumber", orderNum);
                orderMap.put("name", name);
                orderMap.put("repairItem", repairItem);
                orderMap.put("repairDetail", repairDetail);
                orderMap.put("requestDate", requestDate);

                List<Map<String,String>> imgMapList = new ArrayList<>();
                String [] typeName = {"imgFullUrl", "imgDetUrl1", "imgDetUrl2", "imgDetUrl3", "imgCompUrl"};
                List<String> imgList = new ArrayList<>();
                imgList.add(imgFull);
                imgList.add(imgDet01);
                if(imgDet02 != null) imgList.add(imgDet02);
                if(imgDet03 != null) imgList.add(imgDet03);
                if(imgComp != null) imgList.add(imgComp);
                for(int i = 0; i < imgList.size(); i++) {
                    Map<String, String> imgMap = new HashMap<>();
                    imgMap.put("type", typeName[i]);
                    imgMap.put("imgUrl",imgList.get(i));
                    imgMapList.add(imgMap);
                }
                orderMap.put("imgListUrl", imgMapList);

                list.add(orderMap);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 특정 주문 현황 보여주기
    public Map<String, Object> orderStatus (String orderNum) {
        Map<String, Object> orderMap = new HashMap<>();
        String sql = "SELECT DISTINCT O.ORDER_NUM_PK, O.USER_ID_FK, PI.REPAIR_DETAIL_FK, " +
                "PI.REPAIR_ITEM, FLOOR(SYSDATE - O.COMPLETE_DATE) AS DDAY, " +
                "O.ORDER_REQUEST, O.BRAND, O.PRICE_TOTAL, O.IMG_FULL, O.IMG_DETAIL_01, O.IMG_DETAIL_02, O.IMG_DETAIL_03, O.IMG_COMP, O.ORDER_PRG " +
                "FROM ORDER_TB O " +
                "JOIN PARTNER_TB P ON O.PTN_ID_FK = P.PTN_ID_PK " +
                "JOIN PARTNER_ITEM_TB PI ON O.REPAIR_DETAIL_FK = PI.REPAIR_DETAIL_FK " +
                "WHERE O.ORDER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNum);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                String orderNumber = rs.getString("ORDER_NUM_PK");
                String userId = rs.getString("USER_ID_FK");
                String detail = rs.getString("REPAIR_DETAIL_FK");
                String item = rs.getString("REPAIR_ITEM");
                int dDay = rs.getInt("DDAY");
                String request = rs.getString("ORDER_REQUEST");
                String brand = rs.getString("BRAND");
                int totalPrice = rs.getInt("PRICE_TOTAL");
                String fullImg = rs.getString("IMG_FULL");
                String detImg01 = rs.getString("IMG_DETAIL_01");
                String detImg02 = rs.getString("IMG_DETAIL_02");
                String detImg03 = rs.getString("IMG_DETAIL_03");
                String compImg = rs.getString("IMG_COMP");
                String orderPrg = rs.getString("ORDER_PRG");

                String day;
                if (orderPrg.equalsIgnoreCase("완료")) day = "완료";
                else day = "D"+dDay;

                orderMap.put("orderNum", orderNumber);
                orderMap.put("orderId", userId);
                orderMap.put("detail", detail);
                orderMap.put("item", item);
                orderMap.put("dDay", day);
                orderMap.put("request", request);
                orderMap.put("brand", brand);
                orderMap.put("totalPrice", totalPrice);
                orderMap.put("orderPrg", orderPrg);

                List<Map<String,String>> imgMapList = new ArrayList<>();
                String [] typeName = {"imgFullUrl", "imgDetUrl1", "imgDetUrl2", "imgDetUrl3", "imgCompUrl"};
                List<String> imgList = new ArrayList<>();
                imgList.add(fullImg);
                imgList.add(detImg01);
                if(detImg02 != null) imgList.add(detImg02);
                if(detImg03 != null) imgList.add(detImg03);
                if(compImg != null) imgList.add(compImg);
                for(int i = 0; i < imgList.size(); i++) {
                    Map<String, String> imgMap = new HashMap<>();
                    imgMap.put("type", typeName[i]);
                    imgMap.put("imgUrl",imgList.get(i));
                    imgMapList.add(imgMap);
                }
                Common.close(rs);
                Common.close(pstmt);
                Common.close(conn);
                orderMap.put("imgListUrl", imgMapList);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return orderMap;
    }

    public Boolean updatePrg (String orderNum, String selPrg) {
        int result = 0;
        String sql = "UPDATE ORDER_TB SET ORDER_PRG = ? WHERE ORDER_NUM_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,selPrg);
            pstmt.setString(2,orderNum);
            result = pstmt.executeUpdate();
            System.out.println("수정 결과 : " + result);

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
}
