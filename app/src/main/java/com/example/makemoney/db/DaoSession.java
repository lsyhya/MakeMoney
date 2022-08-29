package com.example.makemoney.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.makemoney.Transactions;

import com.example.makemoney.db.TransactionsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig transactionsDaoConfig;

    private final TransactionsDao transactionsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        transactionsDaoConfig = daoConfigMap.get(TransactionsDao.class).clone();
        transactionsDaoConfig.initIdentityScope(type);

        transactionsDao = new TransactionsDao(transactionsDaoConfig, this);

        registerDao(Transactions.class, transactionsDao);
    }
    
    public void clear() {
        transactionsDaoConfig.clearIdentityScope();
    }

    public TransactionsDao getTransactionsDao() {
        return transactionsDao;
    }

}
