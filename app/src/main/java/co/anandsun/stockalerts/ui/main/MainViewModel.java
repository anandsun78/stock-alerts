package co.anandsun.stockalerts.ui.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;

import java.util.Calendar;

import androidx.lifecycle.ViewModel;
import co.anandsun.stockalerts.finance.api.FinanceService;

public class MainViewModel extends ViewModel {

    public void setupFinanceService(Context context)
    {
        Intent myIntent = new Intent(context,FinanceService.class);
        myIntent.putExtra("KEY_TRIGGER_TIME", "30*1000");
        PendingIntent pendingIntent = PendingIntent.getService(context,  0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + (5*1000), pendingIntent);


    }

}
