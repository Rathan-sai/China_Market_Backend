package com.ChinaMarket.ChinaMarket.Converter;

import com.ChinaMarket.ChinaMarket.Enum.ProductStatus;
import com.ChinaMarket.ChinaMarket.Model.Product;
import com.ChinaMarket.ChinaMarket.RequestDto.ProductRequestDto;
import com.ChinaMarket.ChinaMarket.ResponseDto.ProductResponseDto;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

public class ProductConvertor {

    public static Product productRequestDtotoProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .category(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto producttoproductResponseDto(Product product){

        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
