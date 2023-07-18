package com.ChinaMarket.ChinaMarket.RequestDto;

import com.ChinaMarket.ChinaMarket.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    private int customerId;
    private String cardNo;
    private int cvv;
    private CardType cardType;
}
