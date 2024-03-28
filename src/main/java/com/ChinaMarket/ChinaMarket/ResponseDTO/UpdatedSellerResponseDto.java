package com.ChinaMarket.ChinaMarket.ResponseDTO;

import com.ChinaMarket.ChinaMarket.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatedSellerResponseDto {
    private String name;
    private int age;
    private String panNo;
    private String email;
    private String specialization;
    private String mobNo;
    private String city;
    private List<Product> products;
}
