package com.ChinaMarket.ChinaMarket.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveCardResponseDto {

    private String name;
    List<CardDto> cardDtos;
}
