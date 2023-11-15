package com.kh.repairrun.controller;

import com.kh.repairrun.dao.OrderDAO;
import com.kh.repairrun.vo.OrderVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@CrossOrigin (origins = "http://localhost:3000")// 같은 로컬 호스트 쓰는 법
@RestController // restAPI 쓰겠다는 선언! 알아서 json 데이터로 변환, 리액트에서는 axios가 같은 역할
@RequestMapping("/order") // 접근주소

public class OrderController {
    @PostMapping("/detail")
    // 매개변수는 () 사용 ()안의 값에는 리액트에서 넘어오는 값, 키와 밸류의 자료형 선언^^
    public ResponseEntity<List<String>> detailItem (@RequestBody Map<String,String> selectItem) {//리스트로 받을거라고 선언
        String selItem = selectItem.get("repairItem"); // 리액트에서 받아온 정보를 repairItem으로 사용하겠다고 선언
        OrderDAO dao = new OrderDAO(); // 객체 생성
        List<String> result = dao.orderSelect(selItem); // 리스트로 값을 반환
        return new ResponseEntity<>(result, HttpStatus.OK); // 반환해주는 변수 이름이 result,HttpStatus 200일 경우에만 반환

    }

    @PostMapping("/item")
    // 매개변수는 () 사용 ()안의 값에는 리액트에서 넘어오는 값, 키와 밸류의 자료형 선언^^
    public ResponseEntity<String> repairItem (@RequestBody Map<String,String> selectDetail) {//리스트로 받을거라고 선언
        String selDetail = selectDetail.get("detail"); // 리액트에서 받아온 정보를 repairItem으로 사용하겠다고 선언
        OrderDAO dao = new OrderDAO(); // 객체 생성
        String result = dao.itemInfo(selDetail); // 리스트로 값을 반환
        return new ResponseEntity<>(result, HttpStatus.OK); // 반환해주는 변수 이름이 result,HttpStatus 200일 경우에만 반환

    }


//json은 string으로 받아서 "" 필수

//    메소드 명은 소문자 함수도..
    //get 방식은 - 주소창에 정보가 보임, post 방식은 바디안에 숨어서
    @PostMapping("/ptnlist")
    public ResponseEntity<List<Map<String,Object>>> ptnSelList(@RequestBody Map<String,String> selectDetail) {
        String selDetail = selectDetail.get("detail");
        OrderDAO dao = new OrderDAO();
        List<Map<String,Object>> result = dao.partnerSelect(selDetail);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<List<Map<String,Object>>> paymentProcess(@RequestBody Map<String, String > process){
        String processP = process.get("userId");
        System.out.println(processP);
        OrderDAO dao = new OrderDAO();
        List<Map<String,Object>> result = dao.payment(processP);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @PostMapping("/neworder")
    public ResponseEntity<Boolean> newOrderInsert(@RequestBody Map<String, String> orderData) {
        String orderNum = orderData.get("orderNum");
        String userId = orderData.get("userId");
        String ptnId = orderData.get("ptnId");
        String brand = orderData.get("brand");
        String repairDetail = orderData.get("repairDetail");
        String request = orderData.get("request");
        Integer priceTotal = Integer.parseInt(orderData.get("priceTotal"));
        Integer days = Integer.parseInt(orderData.get("days"));
        String imgFull = orderData.get("imgFull");
        String imgDet01 = orderData.get("imgDet01");
        String imgDet02 = orderData.get("imgDet02");
        String imgDet03 = orderData.get("imgDet03");
        OrderDAO dao = new OrderDAO();
        Boolean result = dao.newOrder(orderNum, userId, ptnId, brand, repairDetail, request, priceTotal, days, imgFull, imgDet01, imgDet02, imgDet03);
        return new ResponseEntity<>(result, HttpStatus.OK);


    }


}
