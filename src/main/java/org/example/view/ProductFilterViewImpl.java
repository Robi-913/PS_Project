package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.presenter.dto.CosmeticProductDTO;
import org.example.presenter.dto.StoreProductDTO;
import org.example.presenter.ProductFilterPresenter;
import org.example.presenter.ProductFilterView;

import java.util.List;

public class ProductFilterViewImpl implements ProductFilterView {

    private ProductFilterPresenter presenter;

    private TableView<StoreProductDTO> storeProductTable;
    private TableView<CosmeticProductDTO> cosmeticTable;
    private Label messageLabel;

    private TextField storeIdField;
    private CheckBox onlyAvailableCheck;
    private TextField brandField;
    private CheckBox onlyValidCheck;
    private Button filterButton;

    private TextField searchNameField;
    private Button searchButton;

    private TextField exportStoreIdField;
    private TextField exportFilePathField;
    private Button exportCsvButton;
    private Button exportDocButton;

    private VBox rootLayout;

    public ProductFilterViewImpl() {
        initView();
        presenter = new ProductFilterPresenter(this);
    }

    private void initView() {
        storeProductTable = new TableView<>();
        cosmeticTable = new TableView<>();
        messageLabel = new Label();

        TableColumn<StoreProductDTO, Integer> spIdCol = new TableColumn<>("ID");
        spIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        spIdCol.setPrefWidth(50);

        TableColumn<StoreProductDTO, String> storeNameCol = new TableColumn<>("Store");
        storeNameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStoreName() != null
                        ? cellData.getValue().getStoreName()
                        : "N/A"));
        storeNameCol.setPrefWidth(150);

        TableColumn<StoreProductDTO, String> productNameCol = new TableColumn<>("Product");
        productNameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProductName() != null
                        ? cellData.getValue().getProductName()
                        : "N/A"));
        productNameCol.setPrefWidth(150);

        TableColumn<StoreProductDTO, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setPrefWidth(80);

        storeProductTable.getColumns().addAll(spIdCol, storeNameCol, productNameCol, quantityCol);

        TableColumn<CosmeticProductDTO, Integer> cIdCol = new TableColumn<>("ID");
        cIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cIdCol.setPrefWidth(50);

        TableColumn<CosmeticProductDTO, String> cNameCol = new TableColumn<>("Name");
        cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cNameCol.setPrefWidth(150);

        TableColumn<CosmeticProductDTO, String> cBrandCol = new TableColumn<>("Brand");
        cBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        cBrandCol.setPrefWidth(150);

        cosmeticTable.getColumns().addAll(cIdCol, cNameCol, cBrandCol);

        storeIdField = new TextField();
        storeIdField.setPromptText("Store ID");
        onlyAvailableCheck = new CheckBox("Only Available");
        brandField = new TextField();
        brandField.setPromptText("Brand");
        onlyValidCheck = new CheckBox("Only Valid");
        filterButton = new Button("Filter");
        filterButton.setOnAction(e -> presenter.filterProducts());

        searchNameField = new TextField();
        searchNameField.setPromptText("Search name...");
        searchButton = new Button("Search");
        searchButton.setOnAction(e -> presenter.searchCosmeticsByName());

        exportStoreIdField = new TextField();
        exportStoreIdField.setPromptText("Store ID for export");
        exportFilePathField = new TextField();
        exportFilePathField.setPromptText("File path");
        exportCsvButton = new Button("Export CSV");
        exportCsvButton.setOnAction(e -> presenter.exportOutOfStockToCSV());
        exportDocButton = new Button("Export DOC");
        exportDocButton.setOnAction(e -> presenter.exportOutOfStockToDoc());

        HBox filterBox = new HBox(10,
                new Label("Store ID:"), storeIdField,
                onlyAvailableCheck, new Label("Brand:"), brandField,
                onlyValidCheck, filterButton
        );
        filterBox.setPadding(new javafx.geometry.Insets(10));

        HBox searchBox = new HBox(10,
                new Label("Search name:"), searchNameField, searchButton
        );
        searchBox.setPadding(new javafx.geometry.Insets(10));

        HBox exportBox = new HBox(10,
                new Label("Export StoreID:"), exportStoreIdField,
                new Label("File:"), exportFilePathField,
                exportCsvButton, exportDocButton
        );
        exportBox.setPadding(new javafx.geometry.Insets(10));

        rootLayout = new VBox(10,
                filterBox, storeProductTable,
                searchBox, cosmeticTable,
                exportBox, messageLabel
        );
        rootLayout.setPadding(new javafx.geometry.Insets(10));
    }

    public VBox getView() {
        return rootLayout;
    }

    @Override
    public void displayFilteredProducts(List<StoreProductDTO> filtered) {
        storeProductTable.getItems().clear();
        storeProductTable.getItems().addAll(filtered);
    }

    @Override
    public void displaySearchedCosmetics(List<CosmeticProductDTO> found) {
        cosmeticTable.getItems().clear();
        cosmeticTable.getItems().addAll(found);
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
    public String getStoreIdForFilter() {
        return storeIdField.getText();
    }

    @Override
    public Boolean isOnlyAvailableSelected() {
        return onlyAvailableCheck.isSelected();
    }

    @Override
    public String getBrand() {
        return brandField.getText();
    }

    @Override
    public String getSearchName() {
        return searchNameField.getText();
    }

    @Override
    public String getExportStoreId() {
        return exportStoreIdField.getText();
    }

    @Override
    public String getExportFilePath() {
        return exportFilePathField.getText();
    }
}
