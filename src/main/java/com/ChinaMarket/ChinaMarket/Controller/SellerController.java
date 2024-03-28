package com.ChinaMarket.Chinamarket.Controller;

import com.ChinaMarket.ChinaMarket.Exception.SellerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Seller;
import com.ChinaMarket.ChinaMarket.RequestDto.SellerRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDto.UpdateSellerRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.SellerResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.UpdatedSellerResponseDto;
import com.ChinaMarket.ChinaMarket.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);
    }

    // Get all sellers
    @GetMapping("getAllSellers")
    public List<Seller> getAllSellers(){
        return sellerService.getAllSellers();
    }

    // get a seller by PAN Card
    @GetMapping("getSellerByPanCard/{panCardNo}")
    public ResponseEntity getSellerByPanCard(@PathVariable("panCardNo") String panCardNo){
        SellerResponseDto sellerResponseDto;
        try{
            sellerResponseDto = sellerService.getSellerByPanCard(panCardNo);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(sellerResponseDto, HttpStatus.ACCEPTED);
    }

    // find sellers of a particular age
    @GetMapping("getSellerByAge/{age}")
    public ResponseEntity getSellerByAge(@PathVariable("age") int age){
        List<SellerResponseDto> sellerResponseDtos;
        try{
            sellerResponseDtos = sellerService.getSellerByAge(age);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sellerResponseDtos, HttpStatus.ACCEPTED);
    }

    //update the seller
    @PutMapping("updateSeller")
    public ResponseEntity updateSeller(@RequestBody UpdateSellerRequestDto updateSellerRequestDto){
        UpdatedSellerResponseDto updatedSellerResponseDto;
        try{
            updatedSellerResponseDto = sellerService.updateSeller(updateSellerRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(updatedSellerResponseDto, HttpStatus.ACCEPTED);
    }
}