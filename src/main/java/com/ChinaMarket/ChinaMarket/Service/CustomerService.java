package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Converter.CustomerConvertor;
import com.ChinaMarket.ChinaMarket.Model.Cart;
import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.Repository.CustomerRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto){

        Customer customer = CustomerConvertor.customerRequestDtotoCustomer(customerRequestDto);

        //add cart to the customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //set cart in customer
        customer.setCart(cart);

        customerRepository.save(customer);

        return "Congrats! Welcome to China-Market!!!";
    }
}
