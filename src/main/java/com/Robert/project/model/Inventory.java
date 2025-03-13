package com.Robert.project.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Stoc")
@IdClass(InventoryId.class)
public class Inventory {

    @Id
    @Column(name = "id_magazin")
    private Integer storeId;

    @Id
    @Column(name = "id_produs")
    private Integer productId;

    @Column(name = "disponibilitate")
    private Boolean available;

    @Column(name = "cantitate")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_magazin", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "id_produs", insertable = false, updatable = false)
    private Product product;
}
