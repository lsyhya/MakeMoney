package com.example.makemoney;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.makemoney.db.DaoMaster;
import com.example.makemoney.db.DaoSession;

public class APP extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDreenDao();
    }

    private void initDreenDao() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"test.db");
        SQLiteDatabase database=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(database);
        daoSession=daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
