package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.presenter.dto.CosmeticProductDTO;
import org.example.presenter.CosmeticProductPresenter;
import org.example.presenter.CosmeticProductView;

import java.util.List;

public class CosmeticProductViewImpl implements CosmeticProductView {

    private CosmeticProductPresenter presenter;
    private TableView<CosmeticProductDTO> productTable;
    private Label messageLabel;

    private TextField nameField;
    private TextField brandField;
    private TextField priceField;
    private TextField sizeField;
    private TextField categoryField;

    private Button addProductButton;
    private Button updateProductButton;
    private Button deleteProductButton;

    private VBox rootLayout;

    public CosmeticProductViewImpl() {
        initView();
        presenter = new CosmeticProductPresenter(this);
        presenter.loadAllCosmeticProducts();
    }

    public void initView() {
        productTable = new TableView<>();
        messageLabel = new Label();

        TableColumn<CosmeticProductDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50);

        TableColumn<CosmeticProductDTO, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(200);

        TableColumn<CosmeticProductDTO, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setPrefWidth(200);

        TableColumn<CosmeticProductDTO, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(100);

        TableColumn<CosmeticProductDTO, String> sizeColumn = new TableColumn<>("Size");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        sizeColumn.setPrefWidth(100);

        TableColumn<CosmeticProductDTO, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setPrefWidth(150);

        productTable.getColumns().addAll(idColumn, nameColumn, brandColumn, priceColumn, sizeColumn, categoryColumn);

        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nameField.setText(newSel.getName());
                brandField.setText(newSel.getBrand());
                priceField.setText(String.valueOf(newSel.getPrice()));
                sizeField.setText(newSel.getSize());
                categoryField.setText(newSel.getCategory());
            }
        });

        nameField = new TextField();
        nameField.setPromptText("Name");
        brandField = new TextField();
        brandField.setPromptText("Brand");
        priceField = new TextField();
        priceField.setPromptText("Price");
        sizeField = new TextField();
        sizeField.setPromptText("Size");
        categoryField = new TextField();
        categoryField.setPromptText("Category");

        addProductButton = new Button("Add Product");
        addProductButton.setOnAction(e -> {
            presenter.addCosmeticProduct();
            clearFields();
        });

        updateProductButton = new Button("Update Product");
        updateProductButton.setOnAction(e -> {
            presenter.updateCosmeticProduct();
            clearFields();
        });

        deleteProductButton = new Button("Delete Product");
        deleteProductButton.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText("Delete Product");
            confirm.setContentText("Are you sure you want to delete this product?");
            confirm.showAndWait().ifPresent(resp -> {
                if (resp == ButtonType.OK) {
                    presenter.deleteCosmeticProduct();
                    clearFields();
                }
            });
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(new Label("Name:"), 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(new Label("Brand:"), 0, 1);
        inputGrid.add(brandField, 1, 1);
        inputGrid.add(new Label("Price:"), 0, 2);
        inputGrid.add(priceField, 1, 2);
        inputGrid.add(new Label("Size:"), 0, 3);
        inputGrid.add(sizeField, 1, 3);
        inputGrid.add(new Label("Category:"), 0, 4);
        inputGrid.add(categoryField, 1, 4);

        HBox buttonBox = new HBox(10, addProductButton, updateProductButton, deleteProductButton);
        buttonBox.setPadding(new Insets(10));

        rootLayout = new VBox(10, productTable, inputGrid, buttonBox, messageLabel);
        rootLayout.setPadding(new Insets(10));
    }

    public VBox getView() {
        return rootLayout;
    }

    private void clearFields() {
        nameField.clear();
        brandField.clear();
        priceField.clear();
        sizeField.clear();
        categoryField.clear();
    }

    @Override
    public void displayCosmeticProducts(List<CosmeticProductDTO> products) {
        productTable.getItems().clear();
        productTable.getItems().addAll(products);
    }

    @Override
    public void displayCosmeticProduct(CosmeticProductDTO product) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Product Details");
        info.setHeaderText("Details for Product ID: " + product.getId());
        info.setContentText("Name: " + product.getName() +
                "\nBrand: " + product.getBrand() +
                "\nPrice: " + product.getPrice() +
                "\nSize: " + product.getSize() +
                "\nCategory: " + (product.getCategory() != null ? product.getCategory() : "N/A"));
        info.showAndWait();
    }

    @Override
    public void showMessage(String message) {
        messageLabel.setText(message);
    }

    @Override
    public void showError(String errorMessage) {
        messageLabel.setText(errorMessage);
    }

    @Override
    public String getProductName() {
        return nameField.getText();
    }

    @Override
    public String getProductBrand() {
        return brandField.getText();
    }

    @Override
    public String getProductPrice() {
        return priceField.getText();
    }

    @Override
    public String getProductSize() {
        return sizeField.getText();
    }

    @Override
    public String getProductCategory() {
        return categoryField.getText();
    }

    @Override
    public CosmeticProductDTO getSelectedProduct() {
        return productTable.getSelectionModel().getSelectedItem();
    }
}
