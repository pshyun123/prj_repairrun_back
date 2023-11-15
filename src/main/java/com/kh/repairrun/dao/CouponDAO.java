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

    public List<Map<String, String>> userCoupon(String couponUserId) {
        List<Map<String, String>> couponList = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT " +
                    "CU.COUPON_TYPE_FK, " +
                    "CT.DISCOUNT_AMOUNT, " +
                    "CASE " +
                    "WHEN CT.MIN_PRICE IS NOT NULL THEN CT.MIN_PRICE " +
                    "ELSE 0 " +
                    "END AS MIN_PRICE, " +
                    "TO_CHAR(CU.END_DATE, 'YYYY/MM/DD') AS END_DATE " +
                    "FROM " +
                    "COUPON_USER_TB CU " +
                    "JOIN " +
                    "COUPON_TYPE_TB CT ON CU.COUPON_TYPE_FK = CT.COUPON_TYPE_PK " +
                    "WHERE " +
                    "CU.END_DATE > SYSDATE " +
                    "AND CU.USER_ID_FK = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, couponUserId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String,String> couponMap = new HashMap<>();
                String couponType = rs.getString("COUPON_TYPE_FK");
                String endDate = rs.getString("END_DATE");
                String discountAmount = rs.getString("DISCOUNT_AMOUNT");
                String minPrice = rs.getString("MIN_PRICE");
                couponMap.put("couponType",couponType);
                couponMap.put("endDate", endDate);
                couponMap.put("discountAmount", discountAmount);
                couponMap.put("minPrice", minPrice);
                couponList.add(couponMap);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);

        System.out.println(couponList);
        return couponList;
    }
}
