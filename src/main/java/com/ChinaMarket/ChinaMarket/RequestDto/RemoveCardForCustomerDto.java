package com.ChinaMarket.ChinaMarket.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveCardForCustomerDto {

    private int customerId;
    private String CardNO;
}
