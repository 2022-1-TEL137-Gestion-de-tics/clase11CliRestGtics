package com.example.clase11clirestgtics.dto;

import com.example.clase11clirestgtics.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String result;
    private String msg;
    private Product product;
}
