package com.ChinaMarket.ChinaMarket.ResponseDto;

import com.ChinaMarket.ChinaMarket.Model.Ordered;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCustomerResponseDto {

    private int customerId;
    private String name;
    private int age;
    private String email;
    private String mobNo;
    private String city;
    private List<Ordered> orderedList;
}
