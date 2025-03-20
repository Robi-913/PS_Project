package org.example.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {

        CosmeticProductViewImpl cosmeticView = new CosmeticProductViewImpl();
        StoreViewImpl storeView = new StoreViewImpl();
        StoreProductViewImpl storeProductView = new StoreProductViewImpl();
        ProductFilterViewImpl filterView = new ProductFilterViewImpl();

        Button btnCosmetics = new Button("Cosmetic Products");
        Button btnStores = new Button("Stores");
        Button btnStoreProducts = new Button("Store Products");
        Button btnFilter = new Button("Filter & Export");

        HBox menuBar = new HBox(10, btnCosmetics, btnStores, btnStoreProducts, btnFilter);
        menuBar.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(cosmeticView.getView());

        btnCosmetics.setOnAction(e -> root.setCenter(cosmeticView.getView()));
        btnStores.setOnAction(e -> root.setCenter(storeView.getView()));
        btnStoreProducts.setOnAction(e -> root.setCenter(storeProductView.getView()));
        btnFilter.setOnAction(e -> root.setCenter(filterView.getView()));

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
