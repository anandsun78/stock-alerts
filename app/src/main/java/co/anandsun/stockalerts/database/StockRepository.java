package co.anandsun.stockalerts.database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class StockRepository {

    private StockRoomDatabase db;
    private StockDao stockDao;
    private LiveData<List<UserStock>> allStocks;

    public StockRepository(Application application)
    {

        db = StockRoomDatabase.getDatabase(application);
        stockDao = db.stockDao();
        allStocks = stockDao.getAllStocks();

    }

    public void insertStock(UserStock newUserStock)
    {
        InsertAsyncTask task = new InsertAsyncTask(stockDao);
        task.execute(newUserStock);
    }

    public void deleteStock(String symbol)
    {
        DeleteAsyncTask task = new DeleteAsyncTask(stockDao);
        task.execute(symbol);
    }

    public LiveData<List<UserStock>> getAllStocks()
    {
        return allStocks;
    }


    private static class InsertAsyncTask extends AsyncTask<UserStock,Void,Void>
    {
        private StockDao asyncTaskDao;

        InsertAsyncTask(StockDao dao)
        {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserStock... params) {
            asyncTaskDao.insertStock(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String,Void,Void>
    {
        private StockDao asyncTaskDao;

        DeleteAsyncTask(StockDao dao)
        {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteStock(params[0]);
            return null;
        }
    }


}
