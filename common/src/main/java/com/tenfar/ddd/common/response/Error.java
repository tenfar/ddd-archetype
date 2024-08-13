package com.tenfar.ddd.common.response;

import lombok.Data;

/**
 * 錯誤響應類
 * 用於封裝錯誤信息，包括錯誤代碼和錯誤消息
 */
@Data
public class Error {
    /**
     * 錯誤代碼
     * 用於標識錯誤類型
     */
    private Integer code;

    /**
     * 錯誤消息
     * 用於描述錯誤詳情
     */
    private String message;
}


