package com.tenfar.ddd.common.response;

import lombok.Data;

import java.util.List;

/**
 * PageResult class represents a paginated result set.
 * It contains the data for the current page, current page number,
 * page size, total count of items, and total number of pages.
 */
@Data
public class PageResult<T> {
    /**
     * The list of data items for the current page.
     */
    private List<T> data;

    /**
     * The current page number.
     */
    private Integer currPage;

    /**
     * The number of items per page.
     */
    private Integer pageSize;

    /**
     * The total count of items across all pages.
     */
    private Long totalCount;

    /**
     * The total number of pages based on the total count and page size.
     */
    private Integer totalPages;

    /**
     * Constructor to initialize PageResult with provided data and pagination details.
     *
     * @param data       The list of data items for the current page.
     * @param currPage   The current page number.
     * @param pageSize   The number of items per page.
     * @param totalCount The total count of items across all pages.
     */
    public PageResult(List<T> data, Integer currPage, Integer pageSize, Long totalCount) {
        this.data = data != null ? data : List.of();
        this.currPage = currPage != null ? currPage : 1;
        this.pageSize = pageSize != null ? pageSize : 10;
        this.totalCount = totalCount != null ? totalCount : 0L;
        this.totalPages = calculateTotalPages(this.totalCount, this.pageSize);
    }

    /**
     * Calculates the total number of pages based on total count and page size.
     *
     * @param totalCount The total count of items.
     * @param pageSize   The number of items per page.
     * @return The total number of pages.
     */
    private Integer calculateTotalPages(Long totalCount, Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * Gets the limit of items per page.
     *
     * @return The number of items per page.
     */
    public Integer getLimit() {
        return pageSize != null ? pageSize : 10;
    }

    /**
     * Calculates the offset for the current page.
     *
     * @return The offset for the current page.
     */
    public Integer getOffset() {
        return (currPage != null && currPage > 0 ? currPage - 1 : 0) * (pageSize != null ? pageSize : 10);
    }

    /**
     * Gets the previous page number.
     *
     * @return The previous page number, or 1 if on the first page.
     */
    public Integer getPrevPage() {
        return currPage != null && currPage > 1 ? currPage - 1 : 1;
    }

    /**
     * Gets the next page number.
     *
     * @return The next page number, or the total number of pages if on the last page.
     */
    public Integer getNextPage() {
        return (currPage != null && currPage > 0 && totalPages != null && currPage < totalPages) ? currPage + 1 : (totalPages != null ? totalPages : 1);
    }
}