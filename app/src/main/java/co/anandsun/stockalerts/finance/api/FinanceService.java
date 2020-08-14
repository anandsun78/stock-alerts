package co.anandsun.stockalerts.finance.api;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.Nullable;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class FinanceService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        PendingIntent pendingIntent = PendingIntent.getService(getBaseContext(),  0, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getBaseContext().getSystemService(Context.ALARM_SERVICE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + (5*1000),  pendingIntent);
        AsyncTask task = new SrvClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,startId);
        return Service.START_STICKY;

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i("hello","In the service restart");
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, RestartService.class);
        this.sendBroadcast(broadcastIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class SrvClass extends AsyncTask<Integer,Integer,String>
    {

        @Override
        protected String doInBackground(Integer... params) {

            try{
                    String[] symbols = new String[] { "WKHS", "SHLL", "TSLA", "NKLA"};
                    Map<String, Stock> stocks = YahooFinance.get(symbols);

                    String notificationText = "WKHS: " + stocks.get("WKHS").getQuote().getPrice().toString() +
                            " SHLL: " + stocks.get("SHLL").getQuote().getPrice().toString() +
                            " TSLA: " + stocks.get("TSLA").getQuote().getPrice().toString() +
                            " NKLA: " + stocks.get("NKLA").getQuote().getPrice().toString();
                    String title = "Your quotes are";
                    SendNotification notification = new SendNotification(getBaseContext());

                int id = createID();
                notification.sendNotification(getBaseContext(), id , title , notificationText);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
            return ("Service complete " );
        }

        @Override
        protected void onPreExecute() {

        }


    }

    /*public void myMethod()
    {
        try{

            String[] symbols = new String[] { "WKHS", "SHLL", "TSLA", "NKLA"};
//            Map<String, UserStock> stocks = YahooFinance.get(symbols);

            String notificationText = "WKHS: " + YahooFinance.get("WKHS").getQuote().getPrice().toString();
            String title = "Your quotes are";
            SendNotification notification = new SendNotification(this);

            int id = createID();
            notification.sendNotification(this, id , title , notificationText);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
*/
    public int createID(){
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss",  Locale.US).format(now));
        return id;
    }
}
