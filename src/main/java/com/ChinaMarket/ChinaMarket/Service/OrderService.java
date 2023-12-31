package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Enum.ProductStatus;
import com.ChinaMarket.ChinaMarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.ChinaMarket.Exception.ProductNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.*;
import com.ChinaMarket.ChinaMarket.Repository.CustomerRepository;
import com.ChinaMarket.ChinaMarket.Repository.ProductRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.OrderRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception{
        Customer customer;
        System.out.println(orderRequestDto.getProductId()+" "+ orderRequestDto.getCustomerId());
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id !!!");
        }

        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("Invalid Product Id !!!");
        }

        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! required Quantity is not Available");
        }

        Ordered order = new Ordered();
        order.setTotal(orderRequestDto.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);
        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getOrderedItems().add(item);
        order.setCustomer(customer);

        System.out.println(order.getTotal());

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrder(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .ItemPrice(product.getPrice())
                .totalCost(order.getTotal())
                .deliveryCharge(40)
                .build();

        System.out.println(orderResponseDto.getTotalCost());

        // send an email
//        String text = "Congrats your order with total value "+order.getTotalCost()+" has been placed";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("backendavengers@gmail.com");
//        message.setTo(customer.getEmail());
//        message.setSubject("Order Placed Notification");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDto;
    }
}
