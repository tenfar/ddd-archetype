package com.tenfar.ddd.common.response;

import lombok.Data;
@Data
public class Meta {
    private String prev;
    private String next;
    private String curr;
    private Integer total;
    private Integer currPage;
    private Integer pageSize;
}