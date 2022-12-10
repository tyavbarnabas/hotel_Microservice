package com.codeafrica.hotelservice.service;

import com.codeafrica.hotelservice.dto.HotelDto;
import com.codeafrica.hotelservice.model.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel createHotel(HotelDto hotelDto);

    //getAll
    List<Hotel> getAllHotels();

    //hotel
    Hotel getHotelById(String hotelId);

    Hotel  updateHotel(String hotelId,HotelDto hotelDto);


    void DeleteHotel(String hotelId);

    List<Hotel> searchHotels(String query);




}
