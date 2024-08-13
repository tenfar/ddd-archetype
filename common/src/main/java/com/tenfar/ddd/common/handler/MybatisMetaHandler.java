package com.tenfar.ddd.common.handler; /*
  Package declaration for MybatisMetaHandler
 */

import com.github.pagehelper.PageInfo;
import com.tenfar.ddd.common.response.Meta;
import com.tenfar.ddd.common.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component; /* Utility class for pagination */

/** MybatisMetaHandler is responsible for handling pagination metadata for MyBatis PageInfo objects. */
@Component
public class MybatisMetaHandler implements MetaHandler {

    @Value("${service.base.url}")
    /* Base URL from configuration file */
    private String baseUrl;

    /** Handles the metadata for the given body if it is an instance of PageInfo. */
    @Override
    public void handle(Meta meta, Object body) {
        if (body instanceof PageInfo<?>) {
            handlePageInfo(meta, (PageInfo<?>) body);
        }
    }

    /** Processes the PageInfo object to extract pagination information and sets it in the provided Meta object. */
    private void handlePageInfo(Meta meta, PageInfo<?> info) {
        int currentPage = info.getPageNum(); /* Current page number */
        int totalPages = info.getPages(); /* Total number of pages */
        long totalRecords = info.getTotal(); /* Total number of records */
        int pageSize = info.getPageSize(); /* Size of each page */

        /* Use PaginationUtil to set metadata */
        PaginationUtil.setPaginationMeta(meta, currentPage, totalPages, totalRecords, pageSize, baseUrl);
    }

    /** Returns the class type that this handler processes. */
    @Override
    public Class<?> getHandledType() {
        return PageInfo.class; /* Specify the type this handler processes */
    }
}