package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InventoryId.class)
public class Inventory {

    @Id
    private Integer storeId;

    @Id
    private Integer productId;

    private Boolean available;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "storeId", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;
}