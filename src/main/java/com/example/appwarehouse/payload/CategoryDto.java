package com.example.appwarehouse.payload;

import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private Integer parentCategoryId;
}
