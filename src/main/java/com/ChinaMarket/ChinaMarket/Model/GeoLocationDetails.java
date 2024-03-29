package com.ChinaMarket.ChinaMarket.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationDetails {

    private double longitude;
    private double latitude;
}
