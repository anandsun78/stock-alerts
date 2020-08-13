package co.anandsun.stockalerts.finance.api;

import android.os.AsyncTask;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.io.IOException;

import co.anandsun.stockalerts.database.UserStock;
import co.anandsun.stockalerts.ui.main.MainFragment;
import co.anandsun.stockalerts.ui.main.MainViewModel;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class RetrieveStockPrice extends AsyncTask<String,Void,Void> {

    private String symbol;
    private double userPriceLow;
    private double userPriceHigh;
    private UserStock userStock;

    private MainViewModel mViewModel;

    public RetrieveStockPrice(String symbol, double userPriceLow, double userPriceHigh, MainViewModel mViewModel) {
        this.symbol = symbol;
        this.userPriceLow = userPriceLow;
        this.userPriceHigh = userPriceHigh;
        this.mViewModel = mViewModel;
    }

    @Override
    protected Void doInBackground(String... args) {
        String stockName = "";
        String percentChange= "";
        double price=0;
        try {
            Stock stock = YahooFinance.get(args[0]);
            price = Double.parseDouble(stock.getQuote().getPrice().toString());
            percentChange= String.valueOf(stock.getQuote().getChangeInPercent());
            stockName = stock.getName();

        } catch (IOException e) {
            e.printStackTrace();
        }
        userStock = new UserStock(this.symbol,stockName,this.userPriceLow,this.userPriceHigh,price,percentChange);
        mViewModel.insertStock(this.userStock);
        return null;
    }

}
