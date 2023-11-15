package com.kh.repairrun.dao;
import com.kh.repairrun.common.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemReviewDAO {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public List<Map<String , String>> userMyreview(String userId) {
        List<Map<String, String>> myReviewList = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT DISTINCT " +
                    "RT.ORDER_NUM_FK, " +
                    "PT.PTN_NAME, " +
                    "OT.IMG_FULL, " +
                    "OT.IMG_COMP, " +
                    "RT.RATING, " +
                    "RT.COMP_DATE, " +
                    "RT.REVIEW_CONTENTS, " +
                    "MT.USER_ID_PK, " +
                    "PIT.REPAIR_ITEM, " +
                    "RT.IS_REVIEW " +
                    "FROM " +
                    "REVIEW_TB RT " +
                    "JOIN " +
                    "ORDER_TB OT ON RT.ORDER_NUM_FK = OT.ORDER_NUM_PK " +
                    "JOIN " +
                    "MEMBER_TB MT ON OT.USER_ID_FK = MT.USER_ID_PK " +
                    "JOIN " +
                    "PARTNER_TB PT ON OT.PTN_ID_FK = PT.PTN_ID_PK " +
                    "JOIN " +
                    "PARTNER_ITEM_TB PIT ON OT.REPAIR_DETAIL_FK = PIT.REPAIR_DETAIL_FK " +
                    "WHERE " +
                    "MT.USER_ID_PK = ?";

            System.out.println("SQL Query: " + sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String,String> myReviewMap = new HashMap<>();
                String orderNumFk = rs.getString("ORDER_NUM_FK");
                String ptnName = rs.getString("PTN_NAME");
                String imgFull = rs.getString("IMG_FULL");
                String imgComp = rs.getString("IMG_COMP");
                int rating = rs.getInt("RATING");
                String compDate = rs.getString("COMP_DATE");
                String reviewContents = rs.getString("REVIEW_CONTENTS");
                String reviewUserId = rs.getString("USER_ID_PK");
                String repairItem = rs.getString("REPAIR_ITEM");
                String isReview = rs.getString("IS_REVIEW");
                System.out.println();
                myReviewMap.put("orderNumFk", orderNumFk);
                myReviewMap.put("ptnName", ptnName);
                myReviewMap.put("imgFull",imgFull);
                myReviewMap.put("imgComp", imgComp);
                myReviewMap.put("rating", "★".repeat(rating));
                myReviewMap.put("compDate", compDate);
                myReviewMap.put("reviewContents", reviewContents);
                myReviewMap.put("userId", reviewUserId);
                myReviewMap.put("repairItem", repairItem);
                myReviewMap.put("isReview", isReview);
                myReviewList.add(myReviewMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return myReviewList;
    }
}

