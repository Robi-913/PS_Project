package com.Robert.project.view;

import com.Robert.project.model.Product;
import com.Robert.project.model.Inventory;

import java.util.List;

public interface ICatalogView {

    void afiseazaListaProduse(List<Product> lista);

    void afiseazaDetaliiProdus(Product p);

    void afiseazaStoc(List<Inventory> lista);

    void afiseazaMesajEroare(String msg);
}