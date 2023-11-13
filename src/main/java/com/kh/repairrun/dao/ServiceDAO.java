package com.kh.repairrun.dao;

import com.kh.repairrun.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ServiceDAO {
        private Connection conn = null;
        private ResultSet rs = null;
        private PreparedStatement pstmt = null;

//        public List<Map<String, String>> serviceFaq(리액트에서 들어오는 값) {
        public List<Map<String, String>> serviceFaq(int type) {
                List<Map<String, String>> serviceList = new ArrayList<>();
                String [] sqlList = {
                        "SELECT NOTICE_TITLE_PK AS TITLE, NOTICE_CONTENT AS CONTENTS, TO_CHAR(NOTICE_DATE, 'YYYY/MM/DD') AS NOTICEDATE  \n" +
                                "FROM NOTICE_TB",
                        "SELECT FAQ_TITLE_PK AS TITLE, FAQ_CONTENTS AS CONTENTS FROM FAQ_TB"
                };
                try {
                        conn = Common.getConnection();
                        String sql = sqlList[type];
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                                Map<String,String> serviceMap = new HashMap<>();
                                String title = rs.getString("TITLE");
                                String contents = rs.getString("CONTENTS");
                                serviceMap.put("title",title);
                                serviceMap.put("contents", contents);
                                if (type == 0) {
                                        String noticeDate = rs.getString("NOTICEDATE");
                                        System.out.println(noticeDate);
                                        serviceMap.put("date", noticeDate);
                                }
                                serviceList.add(serviceMap);

                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }
                Common.close(rs);
                Common.close(pstmt);
                Common.close(conn);
                return serviceList;
        }

};
