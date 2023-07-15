package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Converter.ProductConvertor;
import com.ChinaMarket.ChinaMarket.Enum.Category;
import com.ChinaMarket.ChinaMarket.Exception.SellerNotFoundException;
import com.ChinaMarket.ChinaMarket.Model.Product;
import com.ChinaMarket.ChinaMarket.Model.Seller;
import com.ChinaMarket.ChinaMarket.Repository.ProductRepository;
import com.ChinaMarket.ChinaMarket.Repository.SellerRepository;
import com.ChinaMarket.ChinaMarket.RequestDto.ProductRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        Seller seller;

        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerNotFoundException("Invalid Seller Id");
        }
        Product product = ProductConvertor.productRequestDtotoProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);
        sellerRepository.save(seller);

        ProductResponseDto productResponseDto = ProductConvertor.producttoproductResponseDto(product);

        return productResponseDto;
    }
    public List<ProductResponseDto> getProductsByCategory(Category category){

        List<Product> products = productRepository.findAllByCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            ProductResponseDto productResponseDto = ProductConvertor.producttoproductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }

        return productResponseDtos;
    }
}
