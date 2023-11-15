package com.kh.repairrun.controller;


import com.kh.repairrun.dao.CouponDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/coupon")
public class CouponController {

    // Coupon 페이지
    @PostMapping("/data")
    public ResponseEntity<List<Map<String, String>>> coupon(@RequestBody Map<String,String> couponUserList) {
        String userCouponData = couponUserList.get("userId");
        CouponDAO dao = new CouponDAO();
        List<Map<String, String>> couponList = dao.userCoupon(userCouponData);
        System.out.println(couponList);
        return new ResponseEntity<>(couponList, HttpStatus.OK);
    }
}
