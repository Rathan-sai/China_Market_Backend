package com.ChinaMarket.ChinaMarket.RequestDto;

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
