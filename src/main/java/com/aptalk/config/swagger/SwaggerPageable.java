package com.aptalk.config.swagger;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class SwaggerPageable {

    @ApiParam(value = "페이지 번호")
    @Nullable
    private Integer page;

    @ApiParam(value = "페이지 크기")
    @Nullable
    private Integer size;

    @ApiParam(value = "정렬 기준")
    @Nullable
    private String sort;
}
