package com.ChinaMarket.ChinaMarket.Convertor;

import com.ChinaMarket.ChinaMarket.ResponseDto.GeolocationResponseDto;
import com.maxmind.geoip2.model.CityResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeoLocationConverter {

    public static GeolocationResponseDto LocationToGeoLocationResponseConverter(CityResponse cityResponse){
        return GeolocationResponseDto.builder()
                .cityName(cityResponse.getCity().getName())
                .countryName(cityResponse.getCountry().getName())
                .postal(cityResponse.getPostal().getCode())
                .state(cityResponse.getLeastSpecificSubdivision().getName())
                .build();

    }
}
