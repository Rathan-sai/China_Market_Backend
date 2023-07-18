package com.ChinaMarket.ChinaMarket.Controller;

import com.ChinaMarket.ChinaMarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.ChinaMarket.RequestDto.CardRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDto.RemoveCardForCustomerDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.CardResponseDto;
import com.ChinaMarket.ChinaMarket.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        CardResponseDto cardResponseDto;
        try {
            cardResponseDto = cardService.addCard(cardRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cardResponseDto, HttpStatus.ACCEPTED);
    }

    //remove card for a particular customer
    public ResponseEntity removeCardFromACustomer(@RequestBody RemoveCardForCustomerDto removeCardForCustomerDto){
        String res;
        try{

        }
    }

    //remove all card from a particular customer id
}
