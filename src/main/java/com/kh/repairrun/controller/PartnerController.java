package com.kh.repairrun.controller;

import com.kh.repairrun.dao.PartnerDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/partners")
public class PartnerController {
    @PostMapping("/login")
    public ResponseEntity<Boolean> partnerLogin(@RequestBody Map<String,String> loginData) {
        String partnerId = loginData.get("id");
        String partnerPw = loginData.get("pw");
        System.out.println("partnerId : " + partnerId);
        System.out.println("partnerPw : " + partnerPw);
        PartnerDAO dao = new PartnerDAO();
        boolean result = dao.partnerLoginCheck(partnerId, partnerPw);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
