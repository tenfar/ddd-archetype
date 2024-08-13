package com.tenfar.ddd.common.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 結果代碼枚舉類
 * 用於定義系統中的各種響應代碼及其對應的消息
 */
@Getter
public enum ResultCode implements BaseEnum<Integer> {
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失敗"),
    NOT_FOUND(404, "資源未找到"),
    BAD_REQUEST(400, "錯誤的請求");

    // 用於根據code獲取枚舉實例
    private static final Map<Integer, ResultCode> codeMap = new HashMap<>();

    static {
        for (ResultCode resultCode : ResultCode.values()) {
            codeMap.put(resultCode.getCode(), resultCode);
        }
    }

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根據代碼獲取枚舉實例
     *
     * @param code 響應代碼
     * @return 對應的枚舉實例
     */
    public static ResultCode getByCode(int code) {
        return codeMap.get(code);
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}