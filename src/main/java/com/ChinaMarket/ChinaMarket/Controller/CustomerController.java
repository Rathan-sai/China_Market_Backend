package com.ChinaMarket.ChinaMarket.Controller;

import com.ChinaMarket.ChinaMarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.Repository.CustomerRepository;
import com.ChinaMarket.ChinaMarket.RequestDTO.CustomerRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDTO.CustomerUpdateResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDTO.GetCustomerResponseDto;
import com.ChinaMarket.ChinaMarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);
    }

    // get customer by id
    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity getCustomerById(@PathVariable("customerId") int customerId){
        Customer customer;
        try{
            customer = customerService.getCustomerById(customerId);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customer, HttpStatus.ACCEPTED);
    }

    // view all customers
    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    // delete customer by id
    @PostMapping("deleteCustomer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId")int customerId){
        String res;
        try{
            res = customerService.deleteCustomer(customerId);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }

    // get a customer by email
    @GetMapping("getCustomerByEmail/{email}")
    public ResponseEntity getCustomerByEmail(@PathVariable("email")String email){
        GetCustomerResponseDto getCustomerResponseDto;
        try{
            getCustomerResponseDto = customerService.getCustomerByEmail(email);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(getCustomerResponseDto, HttpStatus.ACCEPTED);
    }

    //get a customer by number
    @GetMapping("getCustomerByNumber/{mobNo}")
    public ResponseEntity getCustomerByNumber(@PathVariable("mobNo")String number){
        GetCustomerResponseDto getCustomerResponseDto;
        try{
            getCustomerResponseDto = customerService.getCustomerByNumber(number);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(getCustomerResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("getCustomersByAge/{age}")
    public ResponseEntity getCustomerByAge(@PathVariable("age")int age){
        List<GetCustomerResponseDto> getCustomerResponseDtos;
        getCustomerResponseDtos = customerService.getCustomersByAge(age);
        return new ResponseEntity(getCustomerResponseDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("getCustomersByAgeAbove/{age}")
    public ResponseEntity getCustomerByAgeAbove(@PathVariable("age")int age){
        List<GetCustomerResponseDto> getCustomerResponseDtos;
        getCustomerResponseDtos = customerService.getCustomersByAgeAbove(age);
        return new ResponseEntity(getCustomerResponseDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("getCustomersByAgeBelow/{age}")
    public ResponseEntity getCustomerByAgeBelow(@PathVariable("age")int age){
        List<GetCustomerResponseDto> getCustomerResponseDtos;
        getCustomerResponseDtos = customerService.getCustomersByAgeBelow(age);
        return new ResponseEntity(getCustomerResponseDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("getCustomersByCity/{city}")
    public ResponseEntity getCustomersByCity(@PathVariable("city")String city){
        List<GetCustomerResponseDto> getCustomerResponseDtos;
        getCustomerResponseDtos = customerService.getCustomersByCity(city);
        return new ResponseEntity(getCustomerResponseDtos, HttpStatus.ACCEPTED);
    }

    // update customer
    @PutMapping("updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody CustomerUpdateResponseDto customerUpdateRequestDto){
        Customer customer;
        try{
            customer = customerService.updateCustomer(customerUpdateRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customer, HttpStatus.ACCEPTED);

    }
}