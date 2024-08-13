package com.tenfar.ddd.common.utils;

import com.tenfar.ddd.common.response.Meta;

public class PaginationUtil {

    /**
     * Sets pagination metadata in the Meta object.
     *
     * @param meta         The Meta object to populate.
     * @param currentPage  The current page number.
     * @param totalPages   The total number of pages.
     * @param totalRecords The total number of records.
     * @param pageSize     The number of records per page.
     * @param baseUrl      The base URL for pagination links.
     */
    public static void setPaginationMeta(Meta meta, int currentPage, int totalPages, long totalRecords, int pageSize, String baseUrl) {
        meta.setTotal(totalRecords);
        meta.setCurrPage(currentPage);
        meta.setPageSize(pageSize);
        meta.setPrev(currentPage > 1 ? baseUrl + "?page=" + (currentPage - 1) : null);
        meta.setNext(currentPage < totalPages ? baseUrl + "?page=" + (currentPage + 1) : null);
        meta.setCurr(baseUrl + "?page=" + currentPage);
    }
}