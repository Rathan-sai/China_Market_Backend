package com.ChinaMarket.ChinaMarket.ResponseDto;

import com.ChinaMarket.ChinaMarket.Model.Ordered;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.Order;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveAllCardsResponseDto {

    private String res;
    private String name;
    private int age;
    private String email;
    private String mobNo;
    private List<Ordered> orders;
}
