package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Exception.CardNotFoundException;
import com.ChinaMarket.ChinaMarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Card;
import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.Repository.CustomerRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.CardRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDto.RemoveCardRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.CardDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.CardResponseDto;
import com.ChinaMarket.ChinaMarket.Repository.CardRepository;
import com.ChinaMarket.ChinaMarket.ResponseDto.RemoveAllCardsResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.RemoveCardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer;

        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        // Make a card object
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer)
                .build();

        // add the card to current card list of customer
        customer.getCards().add(card);

        customerRepository.save(customer); // save both customer and card

        // prepare Response Dto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        // convert every card to cardDto
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1: customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        cardResponseDto.setCardDtos(cardDtoList);
        return cardResponseDto;
    }

    //remove card
    public RemoveCardResponseDto removeCard(RemoveCardRequestDto removeCardRequestDto) throws Exception {

        Customer customer;

        try{
            customer = customerRepository.findById(removeCardRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        Card card;
        try{
            card = cardRepository.findByCardNo(removeCardRequestDto.getCardNo());
        }
        catch (Exception e){
            throw new CardNotFoundException("Invalid CardNo");
        }
        if(card == null){
            throw new CardNotFoundException("Invalid CardNo");
        }

        System.out.println(card.getCardNo());
        cardRepository.delete(card);

        RemoveCardResponseDto removeCardResponseDto = new RemoveCardResponseDto();
        removeCardResponseDto.setName(customer.getName());

        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1: customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        removeCardResponseDto.setCardDtos(cardDtoList);
        return removeCardResponseDto;
    }

    //remove all card from a customer
    public RemoveAllCardsResponseDto removeAllCards(int customerId) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        List<Card> cards = customer.getCards();
        for(Card card : cards) {
            cardRepository.delete(card);
        }

        RemoveAllCardsResponseDto removeAllCardsResponseDto = RemoveAllCardsResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .res("All Cards are been removed")
                .orders(customer.getOrders())
                .build();
        return removeAllCardsResponseDto;
    }
}
