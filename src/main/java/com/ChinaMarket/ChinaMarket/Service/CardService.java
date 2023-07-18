package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Exception.CardNotFoundException;
import com.ChinaMarket.ChinaMarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Card;
import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.Repository.CardRepository;
import com.ChinaMarket.ChinaMarket.Repository.CartRepository;
import com.ChinaMarket.ChinaMarket.Repository.CustomerRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.CardRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDto.RemoveCardForCustomerDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.CardDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer;
        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid customer Id");
        }

        //Make a card object
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer)
                .build();

        //Add card to current list of the customer
        customer.getCards().add(card);

        customerRepository.save(customer);

        //Prepare ResponseDto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        //Convert every card to cardDtos
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1 : customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDtoList.add(cardDto);
            card.setCardType(card1.getCardType());
        }

        cardResponseDto.setCards(cardDtoList);

        return cardResponseDto;
    }

    public String removeCardFromACustomer(RemoveCardForCustomerDto removeCardForCustomerDto) throws Exception{
        Customer customer;
        try{
            customer = customerRepository.findById(removeCardForCustomerDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid CustomerId");
        }

        Card card;
        try{
            card = cardRepository.findBy(removeCardForCustomerDto.getCardNO()).get();
        }
    }
}
