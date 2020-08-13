package co.anandsun.stockalerts.ui.main;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import co.anandsun.stockalerts.database.UserStock;
import co.anandsun.stockalerts.database.StockRepository;

public class MainViewModel extends AndroidViewModel {

    private StockRepository repository;
    private LiveData<List<UserStock>> allStocks;

    public MainViewModel(Application application)
    {
        super(application);
        repository = new StockRepository(application);
        allStocks = repository.getAllStocks();
    }

    LiveData <List<UserStock>> getAllStocks()
    {
        return allStocks;
    }
    public void insertStock(UserStock userStock)
    {
        repository.insertStock(userStock);
    }
    public void deleteStock(String symbol)
    {
        repository.deleteStock(symbol);
    }


  /*  public void setupFinanceService(Context context)
    {
        Intent myIntent = new Intent(context,FinanceService.class);
        myIntent.putExtra("KEY_TRIGGER_TIME", "30*1000");
        PendingIntent pendingIntent = PendingIntent.getService(context,  0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + (5*1000), pendingIntent);


    }*/

}
