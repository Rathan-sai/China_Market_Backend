package com.ChinaMarket.ChinaMarket.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveCardRequestDto {

    private int customerId;
    private String cardNo;
}
