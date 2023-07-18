package com.ChinaMarket.ChinaMarket.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    private String name;
    private int age;
    private String mobno;
    private String email;
}
