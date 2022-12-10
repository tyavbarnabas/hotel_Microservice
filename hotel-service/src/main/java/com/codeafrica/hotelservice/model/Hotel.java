package com.codeafrica.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    private String hotelId;
    @Column(unique = true, name = "regNumber")
    private String hotelRegNum;
    @Column(name = "hotelName")
    private String name;
    @Column(name = " hotelAddress")
    private String hotelAddress;
    private String description;

}
