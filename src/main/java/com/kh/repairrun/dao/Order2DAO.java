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
}
