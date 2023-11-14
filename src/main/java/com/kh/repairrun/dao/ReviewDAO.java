package com.kh.repairrun.dao;

import com.kh.repairrun.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDAO {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    //        public List<Map<String, String>> serviceFaq(리액트에서 들어오는 값) {
    public List<Map<String, String>> review() {
        List<Map<String, String>> reviewList = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT DISTINCT\n" +
                    "    PT.PTN_NAME,\n" +
                    "    OT.IMG_FULL,\n" +
                    "    OT.IMG_COMP,\n" +
                    "    RT.RATING,\n" +
                    "    RT.COMP_DATE,\n" +
                    "    RT.REVIEW_CONTENTS,\n" +
                    "    MT.USER_ID_PK,\n" +
                    "    PIT.REPAIR_ITEM\n" +
                    "FROM\n" +
                    "    REVIEW_TB RT\n" +
                    "JOIN\n" +
                    "    ORDER_TB OT ON RT.ORDER_NUM_FK = OT.ORDER_NUM_PK\n" +
                    "JOIN\n" +
                    "    MEMBER_TB MT ON OT.USER_ID_FK = MT.USER_ID_PK\n" +
                    "JOIN\n" +
                    "    PARTNER_TB PT ON OT.PTN_ID_FK = PT.PTN_ID_PK\n" +
                    "JOIN\n" +
                    "    PARTNER_ITEM_TB PIT ON OT.REPAIR_DETAIL_FK = PIT.REPAIR_DETAIL_FK\n" +
                    "WHERE\n" +
                    "    RT.IS_REVIEW = 'TRUE'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String,String> reviewMap = new HashMap<>();
                String ptnName = rs.getString("PTN_NAME");
                String imgFull = rs.getString("IMG_FULL");
                String imgComp = rs.getString("IMG_COMP");
                String compDate = rs.getString("COMP_DATE");
                String reviewContents = rs.getString("REVIEW_CONTENTS");
                int rating = rs.getInt("RATING");
                String useId = rs.getString("USER_ID_PK");
                String repairItem = rs.getString("REPAIR_ITEM");
                System.out.println(compDate);
                reviewMap.put("ptnName", ptnName);
                reviewMap.put("imgFull",imgFull);
                reviewMap.put("imgComp", imgComp);
                reviewMap.put("rating", "★".repeat(rating));
                reviewMap.put("compDate", compDate);
                reviewMap.put("reviewContents", reviewContents);
                reviewMap.put("userId", useId);
                reviewMap.put("repairItem", repairItem);
                reviewList.add(reviewMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return reviewList;
    }
}
