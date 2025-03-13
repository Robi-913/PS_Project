package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VariantaProdus")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_varianta")
    private Integer variantId;

    @Column(name = "id_produs")
    private Integer productId;

    @Column(name = "culoare")
    private String color;

    @Column(name = "marime")
    private String size;

    @Column(name = "imagine")
    private String imageUrl;

    @Column(name = "descriere")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_produs", insertable = false, updatable = false)
    private Product product;
}