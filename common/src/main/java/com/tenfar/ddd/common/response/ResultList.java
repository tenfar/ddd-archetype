package com.tenfar.ddd.common.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Unified response structure
 * Used to encapsulate API response information, including data, error information, metadata, and messages.
 */
@Data
public class ResultList<T> {
    /**
     * The data returned in the response.
     */
    private List<T> data;

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

    /**
     * Default constructor for ResultList.
     * Initializes the response with default values.
     */
    public ResultList() {
        this.data = new ArrayList<>(); // Initialize data as a mutable list
        this.code = 200; // Default response code
        this.error = null; // No error by default
        this.meta = new Meta(); // Initialize meta with default values
        this.message = ""; // Default message is empty
    }
}