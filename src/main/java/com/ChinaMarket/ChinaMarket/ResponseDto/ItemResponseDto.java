package com.ChinaMarket.ChinaMarket.ResponseDto;

import com.ChinaMarket.ChinaMarket.Enum.Category;
import com.ChinaMarket.ChinaMarket.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {

    private String productName;
    private int price;
    private Category category;
    private ProductStatus productStatus;
}
