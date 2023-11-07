package com.kh.repairrun.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ServiceVO {
    private String faqTitle;
    private String faqContent;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;

    // FAQ
    public ServiceVO(String faqTitle, String faqContent) {
        this.faqTitle = faqTitle;
        this.faqContent = faqContent;
    }
    // 공지사항
    public ServiceVO(String noticeTitle, String noticeContent, String noticeDate) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeDate = noticeDate;
    }
}
