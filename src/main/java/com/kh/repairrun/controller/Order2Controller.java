package com.kh.repairrun.controller;

import com.kh.repairrun.dao.Order2DAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orderss")
public class Order2Controller {
    @PostMapping("/orderlist")
    public ResponseEntity<List<Map<String,Object>>> orderList(@RequestBody Map<String,String> userData) {
        int type = Integer.parseInt(userData.get("type"));
        String id = userData.get("id");
        Order2DAO dao = new Order2DAO();
        List<Map<String,Object>> result = dao.orderStatusList(type,id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<Map<String,Object>> oderInfo(@RequestBody Map<String,String> orderNum) {
        String orderNumber = orderNum.get("orderNum");
        Order2DAO dao = new Order2DAO();
        Map<String,Object> result = dao.orderStatus(orderNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
