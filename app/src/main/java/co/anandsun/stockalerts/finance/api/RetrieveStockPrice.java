package co.anandsun.stockalerts.finance.api;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.io.IOException;

import co.anandsun.stockalerts.R;
import co.anandsun.stockalerts.database.UserStock;
import co.anandsun.stockalerts.ui.main.MainViewModel;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class RetrieveStockPrice extends AsyncTask<String,Void,Void> {

    private String symbol;
    private double userPriceLow;
    private double userPriceHigh;
    private UserStock userStock;
    private String stockName;
    private String percentChange;
    private double stockPrice;
    private EditText stockSymbol;
    private MainViewModel mViewModel;
    private View view;

    private Stock stock = null;

    public RetrieveStockPrice(String symbol, double userPriceLow, double userPriceHigh, MainViewModel mViewModel, View view) {
        this.symbol = symbol;
        this.userPriceLow = userPriceLow;
        this.userPriceHigh = userPriceHigh;
        this.mViewModel = mViewModel;
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... args) {

        try {
            Boolean aBoolean =YahooFinance.get(args[0]).isValid();

            if(aBoolean) {
                stock = YahooFinance.get(args[0]);
                this.stockPrice = Double.parseDouble(stock.getQuote().getPrice().toString());
                this.percentChange = String.valueOf(stock.getQuote().getChangeInPercent());
                this.stockName = stock.getName();
            }
            else{
              stockSymbol=  view.findViewById(R.id.stockSymbol);
              stockSymbol.setText("Invalid stock symbol");
                return null;
            }
        } catch (IOException e) {
           return null;
        }


        userStock = new UserStock(symbol,stockName,userPriceLow,userPriceHigh,stockPrice,percentChange);
        mViewModel.insertStock(userStock);
        return null;
    }

}
