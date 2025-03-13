package com.Robert.project.view;

import com.Robert.project.model.Product;
import com.Robert.project.model.Inventory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogWebView implements ICatalogView {

    @Override
    public void afiseazaListaProduse(List<Product> lista) {
        // Implementează logica de actualizare a frontend-ului
        // (ex: trimite un eveniment către frontend prin WebSockets sau SSE)
        System.out.println("Lista produse actualizată: " + lista);
    }

    @Override
    public void afiseazaDetaliiProdus(Product p) {
        // Implementează logica de actualizare a frontend-ului
        // (ex: trimite un eveniment către frontend prin WebSockets sau SSE)
        System.out.println("Detalii produs actualizate: " + p);
    }

    @Override
    public void afiseazaStoc(List<Inventory> lista) {
        // Implementează logica de actualizare a frontend-ului
        // (ex: trimite un eveniment către frontend prin WebSockets sau SSE)
        System.out.println("Stoc actualizat: " + lista);
    }

    @Override
    public void afiseazaMesajEroare(String msg) {
        // Implementează logica de actualizare a frontend-ului
        // (ex: trimite un eveniment către frontend prin WebSockets sau SSE)
        System.out.println("Eroare: " + msg);
    }
}