package com.example.appwarehouse.payload;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private Integer categoryId;
    private Integer photoId;
    private String code;
    private Integer measurementId;
}
