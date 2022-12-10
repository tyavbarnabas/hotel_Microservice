package com.codeafrica.hotelservice.serviceImpl;

import com.codeafrica.hotelservice.dto.HotelDto;
import com.codeafrica.hotelservice.exception.HotelNotFoundException;
import com.codeafrica.hotelservice.model.Hotel;
import com.codeafrica.hotelservice.repository.HotelRepository;
import com.codeafrica.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(HotelDto hotelDto) {
        if(hotelDto.getHotelRegNum().length() != 6){
            throw new IllegalArgumentException("Hotel registration number should be 6 characters!");
        }
        Optional<Hotel> hotel = hotelRepository.findByHotelRegNum(hotelDto.getHotelRegNum());
        if(hotel.isPresent()){
            throw new HotelNotFoundException("Hotel already exist");
        }
        Hotel newHotel = new Hotel();
        newHotel.setHotelId(UUID.randomUUID().toString().split("-")[0]);
        newHotel.setHotelRegNum(hotelDto.getHotelRegNum());
        newHotel.setName(hotelDto.getName());
        newHotel.setHotelAddress(hotelDto.getHotelAddress());
        newHotel.setDescription(hotelDto.getDescription());
        return hotelRepository.save(newHotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        Hotel newHotel = new Hotel();
        BeanUtils.copyProperties(hotel,newHotel);
        return newHotel;
    }

    @Override
    public Hotel updateHotel(String hotelId, HotelDto hotelDto) {
        Hotel newHotel =hotelRepository.findById(hotelId).get();
        newHotel.setName(hotelDto.getName());
        newHotel.setHotelAddress(hotelDto.getHotelAddress());
        newHotel.setDescription(hotelDto.getDescription());
        return hotelRepository.save(newHotel);

    }

    @Override
    public void DeleteHotel(String hotelId) {
        boolean exist = hotelRepository.existsById(hotelId);
        if (!exist){
            throw new IllegalStateException("Hotel with id" + hotelId + "does not exist");
        }
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public List<Hotel> searchHotels(String query) {
        return hotelRepository.searchHotels(query);
    }

}
