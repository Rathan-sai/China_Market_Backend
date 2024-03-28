package com.ChinaMarket.ChinaMarket.Convertor;

import com.ChinaMarket.ChinaMarket.Model.Seller;
import com.ChinaMarket.ChinaMarket.RequestDTO.SellerRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.SellerResponseDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;


public class SellerConvertor {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .specialization(sellerRequestDto.getSpecialization())
                .age(sellerRequestDto.getAge())
                .city(sellerRequestDto.getCity())
                .build();
    }
    public static SellerResponseDto SellertoSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .email(seller.getEmail())
                .panCardNo(seller.getPanNo())
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .specialization(seller.getSpecialization())
                .age(seller.getAge())
                .products(seller.getProducts())
                .city(seller.getCity())
                .build();
    }
}