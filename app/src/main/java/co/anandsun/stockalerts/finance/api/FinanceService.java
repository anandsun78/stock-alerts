package co.anandsun.stockalerts.finance.api;

import android.app.IntentService;
import android.content.Intent;

import java.io.IOException;
import java.util.Map;

import androidx.annotation.Nullable;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class FinanceService extends IntentService {



    public FinanceService() {
        super("FinanceService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try{

            String[] symbols = new String[] { "WKHS", "SHLL", "TSLA", "NKLA"};
            Map<String, Stock> stocks = YahooFinance.get(symbols);

            String notificationText = "WKHS: " + stocks.get("WKHS").getQuote().getPrice().toString() +
                    " SHLL: " + stocks.get("SHLL").getQuote().getPrice().toString() +
                    " TSLA: " + stocks.get("TSLA").getQuote().getPrice().toString() +
                    " NKLA: " + stocks.get("NKLA").getQuote().getPrice().toString();
            String title = "Your quotes are";
            SendNotification notification = new SendNotification(this);
            notification.sendNotification(this, 4 , title , notificationText);
        }
        catch(IOException e) {
            e.printStackTrace();
        }


    }
}
