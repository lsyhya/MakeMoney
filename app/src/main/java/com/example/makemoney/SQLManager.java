package com.example.makemoney;

import android.content.Context;

import com.example.makemoney.db.DaoSession;
import com.example.makemoney.db.TransactionsDao;

import java.util.List;

public class SQLManager {


    TransactionsDao transactionsDao;

    public SQLManager(Context context){
        DaoSession daoSession=((APP)(context)).getDaoSession();
        transactionsDao= daoSession.getTransactionsDao();
    }

    //插入数据
    public long insert(Transactions transactions){
        return transactionsDao.insert(transactions);
    }

    //根据id查询数据
    public Transactions queryById(long id){
        return transactionsDao.loadByRowId(id);
    }

    //查询所有数据
    public List<Transactions> queryAll(){
        return transactionsDao.loadAll();
    }

    //删除所有数据
    public void deleteAll(){
         transactionsDao.deleteAll();
    }

}
