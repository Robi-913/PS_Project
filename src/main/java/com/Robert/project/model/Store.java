package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Magazin")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_magazin")
    private Integer storeId;

    @Column(name = "nume")
    private String name;

    @Column(name = "adresa")
    private String address;

    @Column(name = "telefon")
    private String phone;
}