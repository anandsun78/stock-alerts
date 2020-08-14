package co.anandsun.stockalerts.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;

import co.anandsun.stockalerts.R;
import co.anandsun.stockalerts.database.UserStock;
import co.anandsun.stockalerts.finance.api.RetrieveStockPrice;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private StockListAdapter adapter;
    private EditText stockSymbol;
    private EditText priceLow;
    private EditText priceHigh;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.setupFinanceService(getContext());
        stockSymbol = getView().findViewById(R.id.stockSymbol);
        priceLow = getView().findViewById(R.id.priceLow);
        priceHigh= getView().findViewById(R.id.priceHigh);
        listenerSetup();
        observerSetup();
        recyclerSetup();
    }
    private void clearFields()
    {
        stockSymbol.setText("");
        priceLow.setText("");
        priceHigh.setText("");
    }
    private void listenerSetup()
    {
        Button addButton = getView().findViewById(R.id.addButton);
        Button findButton = getView().findViewById(R.id.findButton);
        Button deleteButton = getView().findViewById(R.id.deleteButton);

        addButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                String symbol = stockSymbol.getText().toString();
                double userPriceLow = Double.parseDouble(priceLow.getText().toString());
                double userPriceHigh = Double.parseDouble(priceHigh.getText().toString());

                if(!symbol.equals("")  && !(userPriceHigh ==0)&& !(userPriceLow ==0))
                {
                    try {
                        RetrieveStockPrice stockPrice = new RetrieveStockPrice(symbol, userPriceLow, userPriceHigh,mViewModel,getView());
                        stockPrice.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, symbol);
                        clearFields();
                    }catch(Exception e)
                    {

                    }
                }
                else{
                    stockSymbol.setText("Incomplete Information");
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                mViewModel.deleteStock(stockSymbol.getText().toString());
                clearFields();
            }
        });

    }

    private void observerSetup()
    {
        mViewModel.getAllStocks().observe( getViewLifecycleOwner(), new Observer<List<UserStock>>() {
            @Override
            public void onChanged(List<UserStock> stocks) {
                adapter.setUserStockList(stocks);
            }
        });

    }

    private void recyclerSetup()
    {
        RecyclerView recyclerView;
        adapter = new StockListAdapter(R.layout.stock_card_layout);
        recyclerView= getView().findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


}
