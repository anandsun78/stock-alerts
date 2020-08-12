package co.anandsun.stockalerts.database;

import android.app.Application;

public class StockRepository {

    public StockRepository(Application application)
    {
        StockRoomDatabase db;
        db = StockRoomDatabase.getDatabase(application);
        db.stockDao();
        //stockDao = db.stockDao();
        //allProducts = stockDao.getAllProducts();

    }
}
