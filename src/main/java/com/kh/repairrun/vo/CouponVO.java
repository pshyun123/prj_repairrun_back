package com.kh.repairrun.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CouponVO {
    private String couponType; // 쿠폰 종류 (예: 할인 쿠폰, 무료 배송 쿠폰 등)
    private int discountAmount; // 할인 금액
    private int minPrice; // 최소 금액
    private String expDate; // 유효 기간


}
