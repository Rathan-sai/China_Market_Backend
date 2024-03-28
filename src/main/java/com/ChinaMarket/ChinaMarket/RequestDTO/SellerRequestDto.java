package com.ChinaMarket.ChinaMarket.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerRequestDto {

    private String name;

    private String mobNo;

    private String specialization;

    private String email;

    private String panNo;

    private String city;

    private int age;
}
