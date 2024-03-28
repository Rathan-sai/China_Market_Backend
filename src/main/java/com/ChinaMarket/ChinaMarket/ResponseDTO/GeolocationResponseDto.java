package com.ChinaMarket.ChinaMarket.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeolocationResponseDto {

    String countryName;
    String cityName;
    String postal;
    String state;
}
