package com.kh.repairrun.dao;

import com.kh.repairrun.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponDAO {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public List<Map<String , String>> userCoupon() {
        List<Map<String, String>> couponList = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT USER_ID_FK AS USERID, COUPON_TYPE_FK AS COUPONTYPE, TO_CHAR(END_DATE, 'YYYY/MM/DD') AS ENDDATE, DISCOUNT_AMOUNT AS DISCOUNTAMOUNT, MIN_PRICE AS MINPIRCE, EXP_DATE AS EXPDATE FROM COUPON_USER_TB JOIN COUPON_TYPE_TB ON COUPON_TYPE_FK = COUPON_TYPE_PK";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String,String> couponMap = new HashMap<>();
                String userId = rs.getString("USERID");
                String couponType = rs.getString("COUPONTYPE");
                String endDate = rs.getString("ENDDATE");
                String discountAmount = rs.getString("DISCOUNTAMOUNT");
                String minPrice = rs.getString("MINPIRCE");
                String expDate = rs.getString("DISCOUNTAMOUNT");
                System.out.println(expDate);
                couponMap.put("userId", userId);
                couponMap.put("couponType",couponType);
                couponMap.put("endDate", endDate);
                couponMap.put("discountAmount", discountAmount);
                couponMap.put("minPrice", minPrice);
                couponMap.put("expDate", expDate);
                couponList.add(couponMap);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return couponList;
    }
}
