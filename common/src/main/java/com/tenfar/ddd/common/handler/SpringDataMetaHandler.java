package com.tenfar.ddd.common.handler;

import com.tenfar.ddd.common.response.Meta;
import com.tenfar.ddd.common.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * SpringDataMetaHandler is responsible for handling pagination metadata
 * for Spring Data's Page objects.
 */
@Component
public class SpringDataMetaHandler implements MetaHandler {

    @Value("${service.base.url}") // Base URL from configuration file
    private String baseUrl;

    /**
     * Handles the metadata for the given body if it is an instance of Page.
     *
     * @param meta the Meta object to populate
     * @param body the object to process
     */
    @Override
    public void handle(Meta meta, Object body) {
        if (body instanceof Page<?>) {
            handlePage(meta, (Page<?>) body);
        }
    }

    /**
     * Processes the Page object to extract pagination information and
     * sets it in the provided Meta object.
     *
     * @param meta the Meta object to populate
     * @param page the Page object containing pagination data
     */
    private void handlePage(Meta meta, Page<?> page) {
        int currentPage = page.getNumber() + 1; // Spring Data uses 0-based index
        int totalPages = page.getTotalPages();
        long totalRecords = page.getTotalElements();
        int pageSize = page.getSize();

        // Use PaginationUtil to set metadata
        PaginationUtil.setPaginationMeta(meta, currentPage, totalPages, totalRecords, pageSize, baseUrl);
    }

    /**
     * Returns the class type that this handler processes.
     *
     * @return the class type handled by this handler
     */
    @Override
    public Class<?> getHandledType() {
        return Page.class; // Specify the type this handler processes
    }
}