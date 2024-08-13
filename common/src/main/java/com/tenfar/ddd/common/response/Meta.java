package com.tenfar.ddd.common.response;

import lombok.Data;

@Data
public class Meta {
    /**
     * 上一頁的鏈接
     */
    private String prev;

    /**
     * 下一頁的鏈接
     */
    private String next;

    /**
     * 當前頁的鏈接
     */
    private String curr;

    /**
     * 總記錄數
     */
    private Long total; // 使用包裝類型

    /**
     * 當前頁碼
     */
    private Integer currPage; // 使用包裝類型

    /**
     * 每頁顯示的記錄數
     */
    private Integer pageSize; // 使用包裝類型
}