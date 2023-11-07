package com.kh.repairrun.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderVO {
    private int orderNum;
    private String ptnName;
    private String repairDetail;
    private String orderRequest;
    private String orderDate;
    private String orderPrg;
    private String imgFull;
    private String imgComp;
    private String imgDetail01;
    private String imgDetail02;
    private String imgDetail03;
    private String ptnAddr;
    private String repairItem;
    private String ptnLogo;
    private BigDecimal ratingAVG;
    private int repairPrice;
    private String userName;
    private String userPhone;
    private String userAddr;
    private String couponType;
    private String repairDays;
    private int priceTotal;
    private String brand;
    private int discoutAmount;


    // 주문 현황
    public OrderVO(int orderNum, String ptnName, String repairDetail, String orderRequest, String orderDate, String orderPrg, String imgFullUrl, String imgCompUrl, String imgDetail01, String imgDetail02, String imgDetail03, int priceTotal, String brand) {
        this.orderNum = orderNum;
        this.ptnName = ptnName;
        this.repairDetail = repairDetail;
        this.orderRequest = orderRequest;
        this.orderDate = orderDate;
        this.orderPrg = orderPrg;
        this.imgFull = imgFullUrl;
        this.imgComp = imgCompUrl;
        this.imgDetail01 = imgDetail01;
        this.imgDetail02 = imgDetail02;
        this.imgDetail03 = imgDetail03;
        this.priceTotal = priceTotal;
        this.brand = brand;
    }

    // 수선사진 등록

    // 요청사항

    // 빠른 매칭 지역
    public OrderVO(String ptnAddr) {
        this.ptnAddr = ptnAddr;

    }

    // 파트너 선택
    public OrderVO(String ptnName, String repairItem, String ptnLogo, BigDecimal ratingAVG, int repairPrice) {
        this.ptnName = ptnName;
        this.repairItem = repairItem;
        this.ptnLogo = ptnLogo;
        this.ratingAVG = ratingAVG;
        this.repairPrice = repairPrice;
    }


    // 수거 / 배송
    public OrderVO(String userName, String userPhone, String userAddr) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddr= userAddr;
    }

    // 쿠폰 적용
    public OrderVO(String couponType, int discoutAmount) {
        this.couponType = couponType;
        this.discoutAmount = discoutAmount;
    }
    // 접수 정보 확인
    public OrderVO(String ptnName, String orderRequest, String orderDate, String ptnAddr, String repairDays, String brand) {
        this.ptnName = ptnName;
        this.orderRequest = orderRequest;
        this.orderDate = orderDate;
        this.ptnAddr = ptnAddr;
        this.repairDays = repairDays;
        this.brand = brand;
    }
}