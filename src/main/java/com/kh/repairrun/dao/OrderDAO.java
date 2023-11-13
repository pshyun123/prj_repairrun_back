package com.kh.repairrun.dao;

import com.kh.repairrun.common.Common;
import com.kh.repairrun.vo.OrderVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class OrderDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;


    // BasicRepair, Request.jsx 부분
    public List<String> orderSelect (String selItem) {//리액트에서 전달 될 값. 매개변수 자리에 원하는 이름 지정. 외부에서 값이 들어오는 경우는 매개변수라고 생각
        List<String> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            String sql = "SELECT DISTINCT REPAIR_DETAIL_FK FROM PARTNER_ITEM_TB WHERE REPAIR_ITEM = ?";


            pstmt = conn.prepareStatement(sql);// 보낼 쿼리문을 준비
            //?의 값을 지정
            pstmt.setString(1,selItem); // 1번 물음표에 selItem이 올 것임

            rs = pstmt.executeQuery();// 보냄. rs의 값 반환을 위한 것. set 형식으로 리스트 받겠다.


            while (rs.next()) { // 값이 있는 동안 한줄씩 반환
                String repairDetail = rs.getString("REPAIR_DETAIL_FK");
                list.add(repairDetail);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


// PartnerSelect.jsx 부분
    public List<Map<String,Object>> partnerSelect (String selDetail) {
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            String sql = "SELECT " +
                    "PT.PTN_LOGO, " +
                    "COALESCE(AVG(RV.RATING), 0) AS RATING, " +
                    "PT.PTN_NAME, " +
                    "PI.REPAIR_DETAIL_FK, " +
                    "PI.REPAIR_PRICE " +
                    "FROM " +
                    "PARTNER_ITEM_TB PI " +
                    "JOIN PARTNER_TB PT ON PI.PTN_ID_FK = PT.PTN_ID_PK " +
                    "LEFT JOIN ORDER_TB O ON PT.PTN_ID_PK = O.PTN_ID_FK " +
                    "LEFT JOIN REVIEW_TB RV ON O.ORDER_NUM_PK = RV.ORDER_NUM_FK WHERE PI.REPAIR_DETAIL_FK = ?" +
                    "GROUP BY " +
                    "PT.PTN_LOGO, PT.PTN_NAME, PI.REPAIR_DETAIL_FK, PI.REPAIR_PRICE";
            pstmt = conn.prepareStatement(sql);// 받을 준비
            pstmt.setString(1, selDetail);
            rs = pstmt.executeQuery();

            while (rs.next()) { // 값이 있는 동안 한줄씩 반환
                String ptnLogo = rs.getString("PTN_LOGO");
                double rating = rs.getDouble("RATING");
                String ptnName = rs.getString("PTN_NAME");
                String repairDetail = rs.getString("REPAIR_DETAIL_FK");
                int repairPrice = rs.getInt("REPAIR_PRICE");

                // 리액트에서 선언한 이름을 ""사이에
                Map<String, Object> ptnMap = new HashMap<>();
                ptnMap.put("ptnLogo",ptnLogo);
                ptnMap.put("rating",rating);
                ptnMap.put("ptnName",ptnName);
                ptnMap.put("repairItem",repairDetail);
                ptnMap.put("repairPrice",repairPrice);

                list.add(ptnMap);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);


        } catch (Exception e) {
            e.printStackTrace();
        }
    return list;
    }


// Payment.jsx 부분

    public List<Map<String,Object>> payment (String paySum) {
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            String sql = "SELECT " +
                    "M.USER_NAME, " +
                    "M.USER_PHONE, " +
                    "M.USER_ADDR, " +
                    "CUT.COUPON_TYPE_FK " +
                    "FROM " +
                    "MEMBER_TB M " +
                    "JOIN " +
                    "COUPON_USER_TB CUT ON M.USER_ID_PK = CUT.USER_ID_FK " +
                    "WHERE " +
                    "M.USER_NAME = ? ";


            pstmt = conn.prepareStatement(sql);// 보낼 쿼리문을 준비
            //?의 값을 지정
            pstmt.setString(1,paySum); //

            rs = pstmt.executeQuery();//

            while (rs.next()) { // 값이 있는 동안 한줄씩 반환
                String userName = rs.getString("USER_NAME");
                String userPhone = rs.getString("USER_PHONE");
                String userAddr = rs.getString("USER_ADDR");
                String couponType = rs.getString("COUPON_TYPE_FK");
                // 각 정보를 리스트에 추가
                Map<String,Object> couponMap = new HashMap<>();
                couponMap.put("name", userName);
                couponMap.put("phone", userPhone);
                couponMap.put("addr", userAddr);
                couponMap.put("coupon", couponType);
                list.add(couponMap);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}







