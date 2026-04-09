package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Stock {
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final DoubleProperty price;
    private final StringProperty category;
    private final StringProperty supplier;
    private final StringProperty lastUpdated;

    public Stock(String name, int quantity, double price, String category, String supplier, LocalDate lastUpdated) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleStringProperty(category);
        this.supplier = new SimpleStringProperty(supplier);
        this.lastUpdated = new SimpleStringProperty(lastUpdated.format(DateTimeFormatter.ISO_DATE));
    }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }

    public String getCategory() { return category.get(); }
    public void setCategory(String category) { this.category.set(category); }
    public StringProperty categoryProperty() { return category; }

    public String getSupplier() { return supplier.get(); }
    public void setSupplier(String supplier) { this.supplier.set(supplier); }
    public StringProperty supplierProperty() { return supplier; }

    public String getLastUpdated() { return lastUpdated.get(); }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated.set(lastUpdated); }
    public StringProperty lastUpdatedProperty() { return lastUpdated; }

    // Default initial data
    public static ObservableList<Stock> getInitialStockData() {
        return FXCollections.observableArrayList(
                new Stock("Apple Inc.", 120, 175.50, "Technology", "GlobalTech Distributors", LocalDate.now()),
                new Stock("Coca-Cola", 300, 59.75, "Consumer Goods", "FreshBeverages Co.", LocalDate.now().minusDays(1)),
                new Stock("Nike Shoes", 80, 99.90, "Apparel", "Sportify Supplies", LocalDate.now().minusDays(2)),
                new Stock("Toyota Prius", 15, 24000.00, "Automotive", "AutoWorld Imports", LocalDate.now()),
                new Stock("Samsung Galaxy S24", 45, 1099.00, "Electronics", "TechBridge", LocalDate.now().minusDays(3))
        );
    }
}
