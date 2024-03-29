package com.ChinaMarket.ChinaMarket.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IP2LocationResponseDto {
    private double longitude;
    private double latitude;
}
