package com.kh.repairrun.controller;

import com.kh.repairrun.dao.ReviewDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import static com.kh.repairrun.common.Common.CORS_ORIGIN;

@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/reviewstock")
public class ReviewController {
    // Service 페이지
    @PostMapping("/data") //FAQ(리액트에서 들어오는값을 쓰는것)
    public ResponseEntity<List<Map<String, String>>> reviewAll() {
        ReviewDAO dao = new ReviewDAO();
            List<Map<String, String>> reviewList = dao.review();
        System.out.println(reviewList);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }
}
