package com.example.clase11clirestgtics.dao;

import com.example.clase11clirestgtics.entity.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SupplierDao {
    public List<Supplier> listarProveedores() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Supplier[]> response = restTemplate.getForEntity(
                "http://localhost:8080/supplier", Supplier[].class);

        return Arrays.asList(response.getBody());
    }

}


