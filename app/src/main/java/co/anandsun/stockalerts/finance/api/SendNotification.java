package co.anandsun.stockalerts.finance.api;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import co.anandsun.stockalerts.MainActivity;

public class SendNotification {

    private NotificationManager notificationManager;

    public SendNotification(Context v)
    {
        notificationManager = (NotificationManager) v.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel("co.anandsun.stockalerts.finance.api.SendNotification","Notify Stocks",
                "Send Stock Alerts every minute");
    }

    private void createNotificationChannel(String id, String name, String description)
    {
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel = new NotificationChannel(id,name,importance);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(
                new long[]{100,200,300,400,500,400,300,200,400});
        notificationManager.createNotificationChannel(channel);
    }

    public void sendNotification(Context context, int id, String title, String content)
    {
        String channelId = "co.anandsun.stockalerts.finance.api.SendNotification";
        Notification notification = new Notification.Builder(context,channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelId)
                .build();
        notificationManager.notify(id,notification);
    }

}
