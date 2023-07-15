package com.ChinaMarket.ChinaMarket.Converter;

import com.ChinaMarket.ChinaMarket.Model.Seller;
import com.ChinaMarket.ChinaMarket.RequestDto.SellerRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConverter {

    public Seller SellerRequestDtoSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .MobileNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }
}
