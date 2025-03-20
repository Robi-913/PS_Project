package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.presenter.dto.StoreDTO;
import org.example.presenter.dto.CosmeticProductDTO;
import org.example.presenter.StorePresenter;
import org.example.presenter.StoreView;

import java.util.List;

public class StoreViewImpl implements StoreView {

    private StorePresenter presenter;
    private TableView<StoreDTO> storeTable;
    private Label messageLabel;

    private TextField nameField;
    private TextField addressField;
    private TextField phoneField;

    private Button addStoreButton;
    private Button updateStoreButton;
    private Button deleteStoreButton;
    private Button showStoresWithCosmeticsButton;

    private VBox rootLayout;

    public StoreViewImpl() {
        initView();
        presenter = new StorePresenter(this);
        presenter.loadAllStores();
    }

    public void initView() {
        storeTable = new TableView<>();
        messageLabel = new Label();

        TableColumn<StoreDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50);

        TableColumn<StoreDTO, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(200);

        TableColumn<StoreDTO, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setPrefWidth(280);

        TableColumn<StoreDTO, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneColumn.setPrefWidth(200);

        storeTable.getColumns().addAll(idColumn, nameColumn, addressColumn, phoneColumn);

        storeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nameField.setText(newSel.getName());
                addressField.setText(newSel.getAddress());
                phoneField.setText(newSel.getPhone());
            }
        });

        nameField = new TextField();
        nameField.setPromptText("Name");
        addressField = new TextField();
        addressField.setPromptText("Address");
        phoneField = new TextField();
        phoneField.setPromptText("Phone");

        addStoreButton = new Button("Add Store");
        addStoreButton.setOnAction(e -> {
            presenter.addStore();
            clearFields();
        });

        updateStoreButton = new Button("Update Store");
        updateStoreButton.setOnAction(e -> {
            presenter.updateStore();
            clearFields();
        });

        deleteStoreButton = new Button("Delete Store");
        deleteStoreButton.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText("Delete Store");
            confirm.setContentText("Are you sure you want to delete the selected store?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    presenter.deleteStore();
                    clearFields();
                }
            });
        });

        showStoresWithCosmeticsButton = new Button("Stores with Cosmetics");
        showStoresWithCosmeticsButton.setOnAction(e -> presenter.loadStoresWithCosmetics());

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(5);
        inputGrid.setPadding(new Insets(10));

        Label nameLabel = new Label("Name:");
        Label addressLabel = new Label("Address:");
        Label phoneLabel = new Label("Phone:");

        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(addressLabel, 0, 1);
        inputGrid.add(addressField, 1, 1);
        inputGrid.add(phoneLabel, 0, 2);
        inputGrid.add(phoneField, 1, 2);

        HBox buttonBox = new HBox(10, addStoreButton, updateStoreButton, deleteStoreButton, showStoresWithCosmeticsButton);
        buttonBox.setPadding(new Insets(10));

        rootLayout = new VBox(8, storeTable, inputGrid, buttonBox, messageLabel);
        rootLayout.setPadding(new Insets(10));
    }

    public VBox getView() {
        return rootLayout;
    }

    private void clearFields() {
        nameField.clear();
        addressField.clear();
        phoneField.clear();
    }

    @Override
    public void displayStores(List<StoreDTO> stores) {
        storeTable.getItems().clear();
        storeTable.getItems().addAll(stores);
    }

    @Override
    public void displayStore(StoreDTO store) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Store Details");
        alert.setHeaderText("Details for Store ID: " + store.getId());
        alert.setContentText("Name: " + store.getName() +
                "\nAddress: " + store.getAddress() +
                "\nPhone: " + store.getPhone());
        alert.showAndWait();
    }

    @Override
    public void showMessage(String message) {
        messageLabel.setText(message);
    }

    @Override
    public void showError(String errorMessage) {
        messageLabel.setText(errorMessage);
    }

    // Getter-ele pentru input
    @Override
    public String getStoreName() {
        return nameField.getText();
    }

    @Override
    public String getStoreAddress() {
        return addressField.getText();
    }

    @Override
    public String getStorePhone() {
        return phoneField.getText();
    }

    @Override
    public StoreDTO getSelectedStore() {
        return storeTable.getSelectionModel().getSelectedItem();
    }

    @Override
    public void displayStoreCosmetics(List<CosmeticProductDTO> cosmetics, String storeName) {
        Stage popupStage = new Stage();

        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Cosmetics for Store: " + storeName);

        TableView<CosmeticProductDTO> table = new TableView<>();

        TableColumn<CosmeticProductDTO, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50);

        TableColumn<CosmeticProductDTO, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(150);

        TableColumn<CosmeticProductDTO, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setPrefWidth(150);

        TableColumn<CosmeticProductDTO, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(100);

        table.getColumns().addAll(idColumn, nameColumn, brandColumn, priceColumn);
        table.getItems().addAll(cosmetics);

        VBox layout = new VBox(10, table);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}
