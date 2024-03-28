package com.ChinaMarket.ChinaMarket.Convertor;

import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.RequestDto.CustomerRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.GetCustomerResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .city(customerRequestDto.getCity())
                .build();
    }

    public static GetCustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return GetCustomerResponseDto.builder()
                .name(customer.getName())
                .customerId(customer.getId())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .city(customer.getCity())
                .orderedList(customer.getOrders())
                .build();
    }
}