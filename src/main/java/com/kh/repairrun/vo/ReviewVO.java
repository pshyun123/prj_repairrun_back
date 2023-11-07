package com.kh.repairrun.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
    private String ptnName;
    private String imgFullUrl;
    private String imgCompUrl;
    private String repairItem;
    private int rating;
    private String userId;
    private String reviewContents;
    private String compDate;
    private int orderNum;
    private boolean isReview;




    // 리뷰요약
    public ReviewVO( String ptnName, String imgFullUrl, String imgCompUrl, String repairItem, int rating, String userId, String reviewContents, String compDate) {
        this.ptnName= ptnName;
        this.imgFullUrl= imgFullUrl;
        this.imgCompUrl= imgCompUrl;
        this.repairItem= repairItem;
        this.rating= rating;
        this.userId= userId;
        this.reviewContents= reviewContents;


    }

    //회원페이지 리뷰
    public ReviewVO( int orderNum, String imgFullUrl, String imgCompUrl, int rating, String ptnName, String repairItem, String compDate, String reviewContents, boolean isReview) {
        this.orderNum= orderNum;
        this.imgFullUrl= imgFullUrl;
        this.imgCompUrl = imgCompUrl;
        this.rating = rating;
        this.ptnName = ptnName;
        this.repairItem = repairItem;
        this.compDate = compDate;
        this.reviewContents = reviewContents;
        this.isReview = isReview;
    }
}