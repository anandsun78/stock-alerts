package co.anandsun.stockalerts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import co.anandsun.stockalerts.finance.api.FinanceService;
import co.anandsun.stockalerts.finance.api.RestartService;
import co.anandsun.stockalerts.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    @Override
    protected void onDestroy() {
        //stopService(mServiceIntent);
        Log.i("hello","In the main activity restart");
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, RestartService.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }
}
