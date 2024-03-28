package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Convertor.CustomerConvertor;
import com.ChinaMarket.ChinaMarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Card;
import com.ChinaMarket.ChinaMarket.Model.Cart;
import com.ChinaMarket.ChinaMarket.Model.Customer;
import com.ChinaMarket.ChinaMarket.Repository.CardRepository;
import com.ChinaMarket.ChinaMarket.Repository.CartRepository;
import com.ChinaMarket.ChinaMarket.Repository.CustomerRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.CustomerRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDto.CustomerUpdateResponseDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.GetCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CartRepository cartRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto){

        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        // allocate a cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        // set cart in customer
        customer.setCart(cart);

        customerRepository.save(customer);
        return "Congrats "+ customer.getName()+ "\n" +"!! Welcome to our ChinaMarket app! We're excited to have you on board.";
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public GetCustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer;
        Optional<Customer> optionalCustomer;
        try{
            optionalCustomer = customerRepository.findByEmail(email);
            customer = optionalCustomer.get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Email");
        }
        GetCustomerResponseDto getCustomerResponseDto = GetCustomerResponseDto.builder()
                .customerId(customer.getId())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .orderedList(customer.getOrders())
                .name(customer.getName())
                .city(customer.getCity())
                .build();

        return getCustomerResponseDto;
    }

    public Customer updateCustomer(CustomerUpdateResponseDto customerUpdateRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerUpdateRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        if(customerUpdateRequestDto.getEmail() != null){
            customer.setEmail(customerUpdateRequestDto.getEmail());
        }
        if(customerUpdateRequestDto.getMobNo() != null){
            customer.setMobNo(customerUpdateRequestDto.getMobNo());
        }
        customerRepository.save(customer);
        return customer;
    }

    public Customer getCustomerById(int customerId) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid CustomerId");
        }
        return customer;
    }

    public String deleteCustomer(int customerId) throws Exception{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Invalid CustomerId");
        }
        List<Card> cards = customer.getCards();
        for(Card card : cards){
            cardRepository.delete(card);
        }
        String name = customer.getName();
        cartRepository.deleteById(customerId);
        customerRepository.deleteById(customerId);
        return "Customer "+name+" has been removed";
    }

    public GetCustomerResponseDto getCustomerByNumber(String mobNo) throws CustomerNotFoundException {
        Customer customer;
        Optional<Customer> optionalCustomer;
        try{
            optionalCustomer = customerRepository.findBymobNo(mobNo);
            customer = optionalCustomer.get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Number");
        }

        GetCustomerResponseDto getCustomerResponseDto = CustomerConvertor.CustomerToCustomerResponseDto(customer);

        return getCustomerResponseDto;
    }

    public List<GetCustomerResponseDto> getCustomersByCity(String city) {
        List<GetCustomerResponseDto> getCustomerResponseDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findByCity(city);
        for(Customer customer : customers){
            getCustomerResponseDtos.add(CustomerConvertor.CustomerToCustomerResponseDto(customer));
        }

        return getCustomerResponseDtos;
    }

    public List<GetCustomerResponseDto> getCustomersByAge(int age) {
        List<GetCustomerResponseDto> getCustomerResponseDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findByAge(age);
        for(Customer customer : customers){
            getCustomerResponseDtos.add(CustomerConvertor.CustomerToCustomerResponseDto(customer));
        }

        return getCustomerResponseDtos;
    }

    public List<GetCustomerResponseDto> getCustomersByAgeAbove(int age) {
        List<GetCustomerResponseDto> getCustomerResponseDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findByAgeGreaterThan(age);
        for(Customer customer : customers){
            getCustomerResponseDtos.add(CustomerConvertor.CustomerToCustomerResponseDto(customer));
        }

        return getCustomerResponseDtos;
    }

    public List<GetCustomerResponseDto> getCustomersByAgeBelow(int age) {
        List<GetCustomerResponseDto> getCustomerResponseDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findByAgeLessThan(age);
        for(Customer customer : customers){
            getCustomerResponseDtos.add(CustomerConvertor.CustomerToCustomerResponseDto(customer));
        }

        return getCustomerResponseDtos;
    }
}
