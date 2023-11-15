package com.kh.repairrun.controller;

import com.kh.repairrun.dao.MemberDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/members")
public class MemberController {

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String memberId = loginData.get("id");
        String memberPw = loginData.get("pw");
        System.out.println("userId :" + memberId);
        System.out.println("userPw :" + memberPw);
        MemberDAO dao = new MemberDAO();
        boolean result = dao.memberLoginCheck(memberId, memberPw);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원정보 조회
    @PostMapping("/memberinfo")
    public ResponseEntity<Map<String, String>> memeberInfo(@RequestBody Map<String, String> memberData) {
        String memberId = memberData.get("memberId");
        MemberDAO dao = new MemberDAO();
        Map<String, String> result = dao.memberInfo(memberId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원정보 수정
    @PostMapping("/updateinfo")
    public ResponseEntity<Boolean> updateMemInfo(@RequestBody Map<String, String> updateList) {
        String userId = updateList.get("userId");
        String userPw = updateList.get("userPw");
        String userEmail = updateList.get("userEmail");
        String userPhone = updateList.get("userPhone");
        String userAddr = updateList.get("userAddr");
        String userImg = updateList.get("userImg");
        MemberDAO dao = new MemberDAO();
        boolean result = dao.memberUpdate(userId, userPw, userEmail, userPhone, userAddr, userImg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    //중복 체크
    @PostMapping("/uniquecheck")
    public ResponseEntity<Boolean> uniqueCheck(@RequestBody Map<String, String> checkData) {
        Integer type = Integer.parseInt(checkData.get("type"));
        String inputVal = checkData.get("inputVal");
        System.out.println("type : " + type);
        System.out.println("inputVal : " + inputVal);
        MemberDAO dao = new MemberDAO();
        boolean result = dao.checkUnique(type, inputVal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/new")
    public ResponseEntity<Boolean> newMember(@RequestBody Map<String, String> newMemData) {
        String id = newMemData.get("id");
        String pw = newMemData.get("pw");
        String name = newMemData.get("name");
        String userEmail = newMemData.get("userEmail");
        String userPhone = newMemData.get("userPhone");
        String userAddr = newMemData.get("userAddr");
        String userImg = newMemData.get("userImg");
        MemberDAO dao = new MemberDAO();
        boolean result = dao.newMemberInsert(id, pw, name, userEmail, userPhone, userAddr, userImg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
