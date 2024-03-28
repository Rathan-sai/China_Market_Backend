package com.ChinaMarket.ChinaMarket.ResponseDto;

import com.ChinaMarket.ChinaMarket.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDto {

    private String name;
    private String email;
    private String mobNo;
    private String panCardNo;
    private String specialization;
    private int age;
    private String city;
    private List<Product> products;
}
