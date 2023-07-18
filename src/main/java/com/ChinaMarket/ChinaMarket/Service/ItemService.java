package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Exception.ProductNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Item;
import com.ChinaMarket.ChinaMarket.Model.Product;
import com.ChinaMarket.ChinaMarket.Repository.ProductRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.ItemRequestDto;
import com.ChinaMarket.ChinaMarket.RequestDto.ProductRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ProductRepository productRepository;
    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {

        Product product;
        try {
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Sorry! Invalid productId");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        // map Item to product
        product.setItem(item);

        productRepository.save(product);

        // prepare the responseDto
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .category(product.getCategory())
                .build();

        return itemResponseDto;
    }
}
