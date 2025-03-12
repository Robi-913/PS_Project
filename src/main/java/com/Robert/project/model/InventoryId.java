package com.Robert.project.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryId implements Serializable {
    private Integer storeId;
    private Integer productId;
}