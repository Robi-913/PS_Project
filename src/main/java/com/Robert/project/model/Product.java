package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Produs")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produs")
    private Integer productId;

    @Column(name = "denumire")
    private String name;

    @Column(name = "producator")
    private String manufacturer;

    @Column(name = "valabilitate")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "categorie")
    private String category;
}