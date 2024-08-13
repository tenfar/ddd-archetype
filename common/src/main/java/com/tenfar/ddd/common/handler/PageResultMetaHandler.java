package com.tenfar.ddd.common.handler;

import com.tenfar.ddd.common.response.Meta;
import com.tenfar.ddd.common.response.PageResult;
import com.tenfar.ddd.common.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component; /*
  PageResult class for pagination results
 */

/**
 * PageResultMetaHandler is responsible for handling pagination metadata 
 * for PageResult objects.
 */
@Component
public class PageResultMetaHandler implements MetaHandler {

    @Value("${service.base.url}")
    /* Base URL from configuration file */
    private String baseUrl;

    /**
     * Handles the metadata for the given body if it is an instance of PageResult.
     *
     * @param meta the Meta object to populate
     * @param body the object to process
     */
    @Override
    public void handle(Meta meta, Object body) {
        if (body instanceof PageResult<?>) {
            handlePageResult(meta, (PageResult<?>) body);
        }
    }

    /**
     * Processes the PageResult object to extract pagination information and 
     * sets it in the provided Meta object.
     *
     * @param meta the Meta object to populate
     * @param pageResult the PageResult object containing pagination data
     */
    private void handlePageResult(Meta meta, PageResult<?> pageResult) {
        int currentPage = pageResult.getCurrPage(); /* Current page number */
        int totalPages = pageResult.getTotalPages(); /* Total number of pages */
        long totalRecords = pageResult.getTotalCount(); /* Total number of records */
        int pageSize = pageResult.getPageSize(); /* Size of each page */

        /* Use PaginationUtil to set metadata */
        PaginationUtil.setPaginationMeta(meta, currentPage, totalPages, totalRecords, pageSize, baseUrl);
    }

    /**
     * Returns the class type that this handler processes.
     *
     * @return the class type handled by this handler
     */
    @Override
    public Class<?> getHandledType() {
        return PageResult.class; /* Specify the type this handler processes */
    }
}