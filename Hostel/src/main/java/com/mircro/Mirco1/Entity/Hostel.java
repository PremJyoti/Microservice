package com.mircro.Mirco1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hostel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hostelID;

    private String hostelName;

    private int studentId;

}
