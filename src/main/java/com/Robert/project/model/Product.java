package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;
    private String manufacturer;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    private String category;
}