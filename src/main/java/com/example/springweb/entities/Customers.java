package com.example.springweb.entities;


import lombok.Data;

import java.time.LocalDate;
@Data
public class Customers {
    private int id;
    private String name;
    private String gender;
    private LocalDate birthOfDate;
    private String address;
    private String phone;
    private String email;
}

