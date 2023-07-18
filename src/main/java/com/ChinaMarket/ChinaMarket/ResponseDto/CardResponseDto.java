package com.ChinaMarket.ChinaMarket.ResponseDto;

import com.ChinaMarket.ChinaMarket.RequestDto.CardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    private String name;
    private List<CardDto> cards;
}
