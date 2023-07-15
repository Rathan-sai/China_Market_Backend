package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Converter.SellerConverter;
import com.ChinaMarket.ChinaMarket.Model.Seller;
import com.ChinaMarket.ChinaMarket.Repository.SellerRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.SellerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;
    public String addSeller(SellerRequestDto sellerRequestDto){
        Seller seller = SellerConverter.SellerRequestDtoSeller(sellerRequestDto);
        sellerRepository.save(seller);

        return "Congrats! Now you can sell in China Market !!!";
    }

    //get all sellers

    //get seller by pan card

    //find seller of particular gae
}
