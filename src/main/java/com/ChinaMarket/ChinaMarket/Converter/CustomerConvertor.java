package com.ChinaMarket.ChinaMarket.Converter;

import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.RequestDto.CustomerRequestDto;

public class CustomerConvertor {

    public static Customer customerRequestDtotoCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobileNo(customerRequestDto.getMobno())
                .build();
    }
}
