package com.ChinaMarket.ChinaMarket.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSellerRequestDto {

    private int sellerId;
    private String name;
    private int age;
    private String email;
    private String specialization;
    private String panNo;
    private String city;
    private String mobNo;
}
