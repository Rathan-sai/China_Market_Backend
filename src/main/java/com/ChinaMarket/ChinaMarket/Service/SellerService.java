package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Exception.SellerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Seller;
import com.ChinaMarket.ChinaMarket.Repository.SellerRepository;
import com.ChinaMarket.ChinaMarket.RequestDTO.SellerRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDTO.UpdateSellerRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.SellerResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.UpdatedSellerResponseDto;
import com.ChinaMarket.ChinaMarket.Convertor.SellerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepo;

    public String addSeller(SellerRequestDto sellerRequestDto){
        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);
        sellerRepo.save(seller);
        return "Congrats! Now you can sell on Chine Market !!!";
    }

    public List<Seller> getAllSellers() {
        return sellerRepo.findAll();
    }

    public SellerResponseDto getSellerByPanCard(String panCardNo) throws SellerNotFoundException {
        Seller seller;
        try {
            seller = sellerRepo.findByPanNo(panCardNo);
        }
        catch (Exception e){
            throw new SellerNotFoundException("Invalid Seller panCardNo");
        }

        SellerResponseDto sellerResponseDto = SellerConvertor.SellertoSellerResponseDto(seller);

        return sellerResponseDto;
    }

    public List<SellerResponseDto> getSellerByAge(int age) throws SellerNotFoundException {
        if(age < 0)
            throw new SellerNotFoundException("Enter a value greater Zero");
        List<Seller> sellers = sellerRepo.findAllByAge(age);
        if(sellers.isEmpty())
            throw new SellerNotFoundException("No seller Above that age");
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for(Seller seller : sellers){
            SellerResponseDto sellerResponseDto = SellerConvertor.SellertoSellerResponseDto(seller);
            sellerResponseDtos.add(sellerResponseDto);
        }
        return sellerResponseDtos;
    }

    public UpdatedSellerResponseDto updateSeller(UpdateSellerRequestDto updateSellerRequestDto) throws SellerNotFoundException {
        Seller seller;

        int id = updateSellerRequestDto.getSellerId();
        try{
            seller = sellerRepo.findById(2).get();
        }catch (Exception e){
            throw new SellerNotFoundException("Invalid SellerId");
        }
        System.out.println(seller);

        if(updateSellerRequestDto.getName() != null){
            seller.setName(updateSellerRequestDto.getName());
        }
        if(updateSellerRequestDto.getEmail() != null){
            seller.setEmail(updateSellerRequestDto.getEmail());
        }
        if(updateSellerRequestDto.getSpecialization() != null){
            seller.setSpecialization(updateSellerRequestDto.getSpecialization());
        }
        if(updateSellerRequestDto.getPanNo() != null){
            seller.setPanNo(updateSellerRequestDto.getPanNo());
        }
        if(updateSellerRequestDto.getMobNo() != null){
            seller.setMobNo(updateSellerRequestDto.getMobNo());
        }
        if(updateSellerRequestDto.getCity() != null){
            seller.setCity(updateSellerRequestDto.getCity());
        }

        sellerRepo.save(seller);

        return UpdatedSellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .mobNo(seller.getMobNo())
                .specialization(seller.getSpecialization())
                .email(seller.getEmail())
                .panNo(seller.getPanNo())
                .products(seller.getProducts())
                .city(seller.getCity())
                .build();
    }
}
