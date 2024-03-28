package com.ChinaMarket.ChinaMarket.Controller;

import com.ChinaMarket.ChinaMarket.RequestDTO.CardRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDTO.RemoveCardRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.CardResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.RemoveAllCardsResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.RemoveCardResponseDto;
import com.ChinaMarket.ChinaMarket.Service.CardService;
import com.ChinaMarket.ChinaMarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        CardResponseDto cardResponseDto;
        try{
            cardResponseDto = cardService.addCard(cardRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
    }

    // remove card
    @PutMapping("/removeCard")
    public ResponseEntity removeCard(@RequestBody RemoveCardRequestDto removeCardRequestDto){
        RemoveCardResponseDto removeCardResponseDto;
        try {
            removeCardResponseDto = cardService.removeCard(removeCardRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(removeCardResponseDto, HttpStatus.ACCEPTED);
    }

    // remove all cards for a particular customer id
    @PostMapping("/removeAllCards/{customerId}")
    public ResponseEntity removeAllCards(@PathVariable("customerId") int customerId){
        RemoveAllCardsResponseDto removeAllCardsResponseDto;
        try{
            removeAllCardsResponseDto = cardService.removeAllCards(customerId);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(removeAllCardsResponseDto, HttpStatus.ACCEPTED);
    }
}
