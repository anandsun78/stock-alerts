package co.anandsun.stockalerts.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.anandsun.stockalerts.R;
import co.anandsun.stockalerts.database.UserStock;
import yahoofinance.Stock;


public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.ViewHolder> {
    private int stockItemLayout;
    private List<UserStock> userStockList;
    private Stock stock;

    public StockListAdapter(int layoutId)
    {
        stockItemLayout = layoutId;
    }

    public void setUserStockList(List<UserStock> userStocks)
    {
        userStockList = userStocks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(stockItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StockListAdapter.ViewHolder holder, int position) {

        holder.stockSymbol.setText(userStockList.get(position).getSymbol());
        holder.stockName.setText(stock.getSymbol());
    }

    @Override
    public int getItemCount() {
        return userStockList == null ? 0: userStockList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView stockSymbol;
        TextView stockName;
        TextView stockPercent;
        TextView stockPrice;

        ViewHolder(View itemView){
            super(itemView);
            stockSymbol = itemView.findViewById(R.id.stockSymbol);
            stockName = itemView.findViewById(R.id.stockName);
            stockPercent = itemView.findViewById(R.id.stockPercent);
            stockPrice = itemView.findViewById(R.id.stockPrice);

        }
    }
}

