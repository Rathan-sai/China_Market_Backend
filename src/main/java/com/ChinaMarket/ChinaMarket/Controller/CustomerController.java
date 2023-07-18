package com.ChinaMarket.ChinaMarket.Controller;

import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.RequestDto.CustomerRequestDto;
import com.ChinaMarket.ChinaMarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        return customerService.addCustomer(customerRequestDto);
    }

    //get all customers

    //get customers by id

    //delete customer by id

    //delete customer by email

    //update customer
}
