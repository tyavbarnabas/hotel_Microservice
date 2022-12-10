package com.codeafrica.hotelservice.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
