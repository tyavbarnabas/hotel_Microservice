package com.codeafrica.hotelservice.repository;

import com.codeafrica.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,String> {
    Optional<Hotel> findByHotelRegNum(String regNum);

    @Query("SELECT h From Hotel h WHERE " + "h.name LIKE CONCAT('%',:query,'%')" +
        "Or h.description LIKE CONCAT('%',:query,'%')" )
    List<Hotel> searchHotels(String query);
}
