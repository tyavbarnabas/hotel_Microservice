package com.codeafrica.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private String hotelRegNum;
    private String name;
    private String hotelAddress;
    private String description;


}
