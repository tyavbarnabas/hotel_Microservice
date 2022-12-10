package com.codeafrica.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;
    @Column(name = "Address")
    private String address;
    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
