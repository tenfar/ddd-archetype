package com.tenfar.ddd.common.response;

import lombok.Data;

/**
 * Unified response structure
 * Used to encapsulate API response information, including data, error information, metadata, and messages.
 */
@Data
public class Result<T> {
    /**
     * The data returned in the response.
     */
    private T data;

    /**
     * Response code
     * Used to identify the response status.
     */
    private Integer code;

    /**
     * Error information
     * Used to describe the details of any error that occurred.
     */
    private Error error;

    /**
     * Metadata
     * Used to describe additional information such as pagination.
     */
    private Meta meta;

    /**
     * Response message
     * Used to describe the details of the response.
     */
    private String message;
}