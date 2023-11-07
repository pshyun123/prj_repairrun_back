package com.kh.repairrun.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerVO {
    private String ptnId;
    private String ptnPw;
    private String ptnLogo;
    private String ptnName;
    private String ptnEmail;
    private String ptnPhone;
    private String ptnAddr;
    private String ptnDesc;
    private String repairItem;
    private int rating;
    private String repairDetail;
    private int repairDays;
    private int repairPrice;

    // 로고 슬라이드
    public PartnerVO(String ptnLogo) {
        this.ptnLogo = ptnLogo;
    }

    // 로그인
    public PartnerVO(String ptnId, String ptnPw) {
        this.ptnId = ptnId;
        this.ptnPw = ptnPw;
    }

    //회원가입 & 회원정보 수정
    public PartnerVO(String ptnId, String ptnPw, String ptnName, String ptnEmail, String ptnPhone, String ptnAddr, String ptnLogo, String ptnDesc) {
        this.ptnId = ptnId;
        this.ptnPw = ptnPw;
        this.ptnName = ptnName;
        this.ptnEmail = ptnEmail;
        this.ptnPhone = ptnPhone;
        this.ptnAddr = ptnAddr;
        this.ptnLogo = ptnLogo;
        this.ptnDesc = ptnDesc;
    }

    //파트너상세정보
    public PartnerVO( String ptnName, String ptnLogo, String ptnPhone, String ptnEmail, String ptnAddr, String ptnDesc) {
        this.ptnName = ptnName;
        this.ptnLogo = ptnLogo;
        this.ptnPhone = ptnPhone;
        this.ptnEmail = ptnEmail;
        this.ptnAddr = ptnAddr;
        this.ptnDesc = ptnDesc;
    }

    //파트너 요약정보
    public PartnerVO(String ptnLogo, String ptnName, String repairItem, int rating) {
        this.ptnLogo = ptnLogo;
        this.ptnName = ptnName;
        this.repairItem = repairItem;
        this.rating = rating;
    }

    //파트너 수선정보
    public PartnerVO(String repairItem, String repairDetail, int repairDays, int repairPrice) {
        this.repairItem = repairItem;
        this.repairDetail = repairDetail;
        this.repairDays = repairDays;
        this.repairPrice = repairPrice;
    }
}
