package co.anandsun.stockalerts.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tracked_stocks")
public class Stock {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stockID")
    @NonNull
    private int id;

    @ColumnInfo(name = "stockSymbol")
    private String symbol;

    @ColumnInfo(name = "priceLow")
    private double priceLow;

    @ColumnInfo(name = "priceHigh")
    private double priceHigh;

    public Stock(String symbol, int priceLow, int priceHigh)
    {

        this.symbol = symbol;
        this.priceLow = priceLow;
        this.priceHigh = priceHigh;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(double priceLow) {
        this.priceLow = priceLow;
    }

    public double getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(double priceHigh) {
        this.priceHigh = priceHigh;
    }
}
