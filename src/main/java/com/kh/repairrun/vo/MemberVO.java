package com.kh.repairrun.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor


public class MemberVO {
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddr;
    private String userImg;

    // 회원가입
    public MemberVO(String userId, String userPw, String userName, String userEmail, String userPhone, String userAddr, String userImg) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddr = userAddr;
        this.userImg = userImg;
    }

    // 로그인
    public MemberVO(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }
    // 회원정보 조회
    public MemberVO(String userId, String userName, String userEmail, String userPhone, String userAddr, String userImg) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddr = userAddr;
        this.userImg = userImg;
    }

}




