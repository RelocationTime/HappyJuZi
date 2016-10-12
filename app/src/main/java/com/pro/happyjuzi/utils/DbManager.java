package com.pro.happyjuzi.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pro.happyjuzi.gen.DaoMaster;
import com.pro.happyjuzi.gen.DaoSession;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class DbManager {

    /*
             使用说明：LYP
        DaoSession session = DbManager.getInstance(context).getDaoSession(context);

        session.getStudentDao().insert(new Student("123")); //插入
        String password = session.getStudentDao().queryBuilder().build().list().get(0).getPassword(); // 查询
        //其他类似, 只要有session, 就可以使用CRUD, 建立 javaBean就是ORM : 一个对象就是一个表，属性即字段，自行查阅资料
        Log.e(TAG, "onCreate: " + password );

     */
    private final static String dbName = "juzi_db";

    private static DbManager mInstance;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private Context mContext;

    private DbManager(Context context) {
        this.mContext  = context;
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        getDaoMaster(context);
        getDaoSession(context);
    }

    private static DaoMaster getDaoMaster(Context context) {
        if (mDaoMaster == null) {
            synchronized (DaoMaster.class) {
                if (mDaoMaster == null) {
                    mDaoMaster = new DaoMaster(getReadableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }
    public DaoSession getDaoSession(Context context) {
        if (mDaoSession == null) {
            synchronized (DaoSession.class) {
                if ((mDaoSession == null)) {
                    mDaoSession = getDaoMaster(context).newSession();
                }
            }
        }
        return mDaoSession;
    }

    public static DbManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DbManager.class) {
                if (mInstance == null) {
                    mInstance = new DbManager(context);
                }
            }
        }
        return mInstance;
    }

    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (mDevOpenHelper == null) {
            mDevOpenHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }

        return mDevOpenHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (mDevOpenHelper == null) {
            mDevOpenHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }
        return mDevOpenHelper.getWritableDatabase();
    }
}
