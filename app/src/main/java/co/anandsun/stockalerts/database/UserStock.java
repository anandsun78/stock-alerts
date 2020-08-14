package co.anandsun.stockalerts.database;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.IOException;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Entity(tableName = "tracked_stocks")
public class UserStock {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stockID")
    @NonNull
    private int id;

    @ColumnInfo(name = "stockSymbol")
    private String symbol;

    @ColumnInfo(name = "stockPrice")
    private double stockPrice;

    @ColumnInfo(name="stockName")
    private String stockName;

    @ColumnInfo(name="stockPercent")
    private String stockPercent;

    @ColumnInfo(name = "priceLow")
    private double priceLow;

    @ColumnInfo(name = "priceHigh")
    private double priceHigh;

    public UserStock(String symbol, String stockName, double priceLow, double priceHigh, double stockPrice, String stockPercent)
    {
        this.id = id;
        this.symbol = symbol;
        this.stockName = stockName;
        this.priceLow = priceLow;
        this.priceHigh = priceHigh;
        this.stockPrice = stockPrice;
        this.stockPercent = stockPercent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockPercent() {
        return stockPercent;
    }

    public void setStockPercent(String stockPercent) {
        this.stockPercent = stockPercent;
    }



}
