package com.example.makemoney;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {


    private RecyclerView recycler;
    private List<Transactions> transactionsList=new ArrayList<>();
    private SQLManager sqlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sqlManager=new SQLManager(getApplicationContext());
        initData();
        initView();
    }

    private void initData() {
//        Transactions transactions1=new Transactions();
//        transactions1.setVarieties("EURUSD");
//        transactions1.setProfitOrLossM(100);
//        Transactions transactions2=new Transactions();
//        transactions2.setVarieties("GBPUSD");
//        transactions2.setProfitOrLossM(-100);
//        transactionsList.add(transactions1);
//        transactionsList.add(transactions2);
          transactionsList=sqlManager.queryAll();
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        OrderAdapter orderAdapter=new OrderAdapter(this,transactionsList);
        recycler.setAdapter(orderAdapter);
    }
}
