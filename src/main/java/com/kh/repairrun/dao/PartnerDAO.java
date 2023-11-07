package com.kh.repairrun.dao;

import com.kh.repairrun.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PartnerDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 로그인 시 회원 여부 체크
    public boolean partnerLoginCheck(String partnerId, String partnerPw) {
        boolean isPartner = false;
        try {
            conn = Common.getConnection();
            String sql = "SELECT COUNT(*) FROM PARTNER_TB WHERE PTN_ID_PK = ? AND PTN_PW = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,partnerId);
            pstmt.setString(2,partnerPw);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                if(rs.getInt("Count(*)") == 1) {
                    isPartner = true;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return isPartner;
    }
}
