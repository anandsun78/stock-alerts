package co.anandsun.stockalerts.ui.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.Calendar;

import androidx.lifecycle.ViewModel;
import co.anandsun.stockalerts.finance.api.FinanceService;

public class MainViewModel extends ViewModel {

    public void setupFinanceService(View v)
    {
        Intent myIntent = new Intent(v.getContext(), FinanceService.class);
        PendingIntent pendingIntent = PendingIntent.getService(v.getContext(),  0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)v.getContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 60); // first time
        long frequency= 5 ; // in ms
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pendingIntent);


    }

}
