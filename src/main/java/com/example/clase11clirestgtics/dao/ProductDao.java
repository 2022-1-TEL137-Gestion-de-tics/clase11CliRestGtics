package com.example.clase11clirestgtics.dao;

import com.example.clase11clirestgtics.dto.ProductDto;
import com.example.clase11clirestgtics.entity.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {

    public List<Product> listarProductos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> response = restTemplate.getForEntity(
                "http://localhost:8080/product", Product[].class);

        return Arrays.asList(response.getBody());
    }

    public void guardarProducto(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "http://localhost:8080/product";
        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);

        RestTemplate restTemplate = new RestTemplate();
        if (product.getId() > 0) {
            restTemplate.put(url, httpEntity, Product.class);
        } else {
            restTemplate.postForEntity(url, httpEntity, Product.class);
        }

    }

    public Product obtenerProductoPorId(int id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/product/" + id;

        ResponseEntity<ProductDto> responseMap = restTemplate.getForEntity(url, ProductDto.class);

        ProductDto productDto = responseMap.getBody();

        return productDto.getProduct();
    }

    public void borrarProducto(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/product/" + id);
    }


}

