package co.anandsun.stockalerts.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StockDao {

    @Insert
    void insertStock(UserStock userStock);

    @Query("DELETE FROM tracked_stocks WHERE stockSymbol= :name")
    void deleteStock(String name);

    @Query("SELECT * FROM tracked_stocks")
    LiveData<List<UserStock>> getAllStocks();
}
