package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer variantId;

    private String color;
    private String size;
    private String imageUrl;
    private String description;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}