package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.presenter.dto.StoreProductDTO;
import org.example.presenter.StoreProductPresenter;
import org.example.presenter.StoreProductView;

import java.util.List;

public class StoreProductViewImpl implements StoreProductView {

    private StoreProductPresenter presenter;
    private TableView<StoreProductDTO> storeProductTable;
    private Label messageLabel;

    private TextField storeIdField;
    private TextField productIdField;
    private TextField quantityField;

    private Button addButton;
    private Button updateButton;
    private Button deleteButton;

    private VBox rootLayout;

    public StoreProductViewImpl() {
        initView();
        presenter = new StoreProductPresenter(this);
        presenter.loadAllStoreProducts();
    }

    public void initView() {
        storeProductTable = new TableView<>();
        messageLabel = new Label();

        TableColumn<StoreProductDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50);

        TableColumn<StoreProductDTO, String> storeColumn = new TableColumn<>("Store");
        storeColumn.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        storeColumn.setPrefWidth(200);

        TableColumn<StoreProductDTO, String> productColumn = new TableColumn<>("Product");
        productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productColumn.setPrefWidth(200);

        TableColumn<StoreProductDTO, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setPrefWidth(100);

        storeProductTable.getColumns().addAll(idColumn, storeColumn, productColumn, quantityColumn);

        storeProductTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                storeIdField.setText(String.valueOf(newSel.getStoreId()));
                productIdField.setText(String.valueOf(newSel.getProductId()));
                quantityField.setText(String.valueOf(newSel.getQuantity()));
            }
        });

        storeIdField = new TextField();
        storeIdField.setPromptText("Store ID");
        productIdField = new TextField();
        productIdField.setPromptText("Product ID");
        quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        addButton = new Button("Add");
        addButton.setOnAction(e -> {
            presenter.addStoreProduct();
            clearFields();
        });

        updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            presenter.updateStoreProduct();
            clearFields();
        });

        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText("Delete Record");
            confirm.setContentText("Are you sure you want to delete this record?");
            confirm.showAndWait().ifPresent(resp -> {
                if (resp == ButtonType.OK) {
                    presenter.deleteStoreProduct();
                    clearFields();
                }
            });
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(new Label("Store ID:"), 0, 0);
        inputGrid.add(storeIdField, 1, 0);
        inputGrid.add(new Label("Product ID:"), 0, 1);
        inputGrid.add(productIdField, 1, 1);
        inputGrid.add(new Label("Quantity:"), 0, 2);
        inputGrid.add(quantityField, 1, 2);

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setPadding(new Insets(10));

        rootLayout = new VBox(10, storeProductTable, inputGrid, buttonBox, messageLabel);
        rootLayout.setPadding(new Insets(10));
    }

    public VBox getView() {
        return rootLayout;
    }

    private void clearFields() {
        storeIdField.clear();
        productIdField.clear();
        quantityField.clear();
    }

    @Override
    public void displayStoreProducts(List<StoreProductDTO> storeProducts) {
        storeProductTable.getItems().clear();
        storeProductTable.getItems().addAll(storeProducts);
    }

    @Override
    public void displayStoreProduct(StoreProductDTO storeProduct) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Record Details");
        info.setHeaderText("Details for Record ID: " + storeProduct.getId());
        info.setContentText("Store: " + storeProduct.getStoreName() +
                "\nProduct: " + storeProduct.getProductName() +
                "\nQuantity: " + storeProduct.getQuantity());
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
    public String getStoreIdInput() {
        return storeIdField.getText();
    }

    @Override
    public String getProductIdInput() {
        return productIdField.getText();
    }

    @Override
    public String getQuantityInput() {
        return quantityField.getText();
    }

    @Override
    public StoreProductDTO getSelectedStoreProduct() {
        return storeProductTable.getSelectionModel().getSelectedItem();
    }
}
