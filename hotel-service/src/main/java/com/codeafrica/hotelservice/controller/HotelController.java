package com.codeafrica.hotelservice.controller;

import com.codeafrica.hotelservice.dto.HotelDto;
import com.codeafrica.hotelservice.model.Hotel;
import com.codeafrica.hotelservice.response.HotelResponse;
import com.codeafrica.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HotelController {

    private final HotelService hotelService;


    @PostMapping("/create")
    public ResponseEntity<HotelResponse<?>>createHotel(@RequestBody HotelDto hotelDto){
        try {
            Hotel hotel = hotelService.createHotel(hotelDto);
            return new ResponseEntity<>(new HotelResponse<>(true,
                    "Hotel with name " + hotelDto.getName() + " was created Successfully ",hotel), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new HotelResponse<>(false,e.getMessage(),"try later"),HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/hotels")
    public  ResponseEntity<HotelResponse<?>>getAllHotels(){
        List<Hotel> allHotels= hotelService.getAllHotels();
        return new ResponseEntity<>(new HotelResponse<>(true, "All hotels found",allHotels),HttpStatus.OK);
    }


    @GetMapping("hotel/{hotelId}")
    public ResponseEntity<HotelResponse<?>>getHotelById(@PathVariable String hotelId){
        Hotel  hotel = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(new HotelResponse<>(true,"hotel found",hotel),HttpStatus.OK);

    }


    @PutMapping("/update/{hotelId}")
    public ResponseEntity<HotelResponse<?>> updateHotel(@PathVariable("hotelId")String hotelId,@RequestBody HotelDto hotelDto){
        Hotel hotel = hotelService.updateHotel(hotelId, hotelDto);
        return new ResponseEntity<>(new HotelResponse<>(true,"Hotel Updated successfully ",hotel),HttpStatus.OK);
    }


    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<HotelResponse<?>>DeleteHotel(@PathVariable("hotelId")  String hotelId){
         hotelService.DeleteHotel(hotelId);
        return new ResponseEntity<>(new HotelResponse<>(true,"Hotel with "
                +hotelId+ " was deleted successfully", null),HttpStatus.OK);
    }

    @GetMapping("/hotels/search")
    public ResponseEntity<HotelResponse<?>>searchHotels(@RequestParam("query") String query){
       List <Hotel>hotels = hotelService.searchHotels(query);
        return new ResponseEntity<>(new HotelResponse<>(true,"Hotels found according to your search",hotels),HttpStatus.OK);
    }


}
