package co.anandsun.stockalerts.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stock.class}, version = 1)
public abstract class StockRoomDatabase extends RoomDatabase {

    public abstract StockDao stockDao();
    private static StockRoomDatabase INSTANCE;

    static StockRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (StockRoomDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), StockRoomDatabase.class, "stocks_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
