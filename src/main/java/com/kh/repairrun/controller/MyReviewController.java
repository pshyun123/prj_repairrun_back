package com.kh.repairrun.controller;

import com.kh.repairrun.dao.MemReviewDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/mypage")
public class MyReviewController {
    // Service 페이지
    @PostMapping("/myreview") //FAQ(리액트에서 들어오는값을 쓰는것)
    public ResponseEntity<List<Map<String, String>>> myReviewAll(@RequestBody Map<String,String> userId) {
        String userIdBox = userId.get("userId");
        MemReviewDAO dao = new MemReviewDAO();
        List<Map<String, String>> myReviewList = dao.userMyreview(userIdBox);
        System.out.println(userIdBox);
        return new ResponseEntity<>(myReviewList, HttpStatus.OK);
    }
}

