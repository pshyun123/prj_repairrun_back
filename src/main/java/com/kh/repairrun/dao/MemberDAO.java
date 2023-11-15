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

public class MemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 로그인 시 회원 여부 체크
    public boolean memberLoginCheck(String userId, String userPw) {
        boolean isMember = false;
        try {
            conn = Common.getConnection();
            String sql = "SELECT COUNT(*) FROM MEMBER_TB WHERE USER_ID_PK = ? AND USER_PW = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,userPw);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                if(rs.getInt("Count(*)") == 1) {
                    isMember = true;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return isMember;
    }

    // 회원 정보 조회
    public Map<String, String> memberInfo(String memberId) {
        Map<String,String> memberInfo = new HashMap<>();
        String sql = "SELECT USER_NAME, USER_EMAIL, USER_PHONE, USER_ADDR, USER_IMG FROM MEMBER_TB WHERE USER_ID_PK = ?";
        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                String userName = rs.getString("USER_NAME");
                String userEmail = rs.getString("USER_EMAIL");
                String userPhone = rs.getString("USER_PHONE");
                String userAddr = rs.getString("USER_ADDR");
                String userImg = rs.getString("USER_IMG");

                memberInfo.put("userName", userName);
                memberInfo.put("userEmail", userEmail);
                memberInfo.put("userPhone", userPhone);
                memberInfo.put("userAddr", userAddr);
                memberInfo.put("userImg", userImg);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return memberInfo;
    }

    // 중복체크
    public boolean checkUnique(int type, String inputVal) {
        boolean isUnique = false;
        String[] sqlList = {
                "SELECT COUNT(*) FROM MEMBER_TB WHERE USER_ID_PK = ?",
                "SELECT COUNT(*) FROM MEMBER_TB WHERE USER_PHONE = ?",
                "SELECT COUNT(*) FROM MEMBER_TB WHERE USER_EMAIL = ?"
        };
        try {
            conn = Common.getConnection();
            String sql = sqlList[type];
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,inputVal);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                if (rs.getInt("Count(*)") == 1) {
                    isUnique = true; // 이미 존재하는 값
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pstmt);
        Common.close(conn);
        return isUnique;
    }

    //회원가입
    public boolean newMemberInsert(String id, String pw, String name, String userEmail, String userPhone, String userAddr, String userImg) {
        int result = 0;
        String sql = "INSERT INTO MEMBER_TB (USER_ID_PK, USER_PW, USER_NAME, USER_EMAIL, USER_PHONE, USER_ADDR, USER_IMG) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, userEmail);
            pstmt.setString(5, userPhone);
            pstmt.setString(6, userAddr);
            pstmt.setString(7, userImg);
            result = pstmt.executeUpdate();
            System.out.println("가입 결과 : " + result);

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    //회원 정보 수정
    public boolean memberUpdate(String userId, String userPw, String userEmail, String userPhone, String userAddr, String userImg) {
        int result = 0;
        String sql = "UPDATE MEMBER_TB SET USER_PW = ?, USER_EMAIL = ?, USER_PHONE = ?, USER_ADDR = ?, USER_IMG = ? WHERE USER_ID_PK = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userPw);
            pstmt.setString(2, userEmail);
            pstmt.setString(3, userPhone);
            pstmt.setString(4, userAddr);
            pstmt.setString(5, userImg);
            pstmt.setString(6, userId);
            result = pstmt.executeUpdate();
            System.out.println("회원수정 결과 : " + result);
        }catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
}
