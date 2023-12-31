package com.ChinaMarket.ChinaMarket.ResponseDto;

import com.ChinaMarket.ChinaMarket.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private String cardNo;
    private CardType cardType;
}
