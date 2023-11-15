package com.kh.repairrun.controller;


import com.kh.repairrun.dao.ServiceDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kh.repairrun.common.Common.CORS_ORIGIN;

@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/service")

public class ServiceController {

    // Service 페이지
    @PostMapping("/data") //FAQ(리액트에서 들어오는값을 쓰는것)
    public ResponseEntity<List<Map<String, String>>> noticeFaq(@RequestBody Map<String, String> type) {
        Integer type2 = Integer.parseInt(type.get("type"));
        ServiceDAO dao = new ServiceDAO();
        List<Map<String, String>> serviceList = dao.serviceFaq(type2);
        System.out.println(serviceList);
        return new ResponseEntity<>(serviceList, HttpStatus.OK);
    }
}
