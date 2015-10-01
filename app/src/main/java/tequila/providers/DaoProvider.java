package tequila.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tangxinli.android.tequila.dao.models.DaoMaster;
import com.tangxinli.android.tequila.dao.models.DaoSession;
import com.tangxinli.android.tequila.utils.LogUtils;

/**
 * Created by williamc1986 on 8/4/15.
 */
public class DaoProvider {
    public static final String TAG = LogUtils.makeLogTag(DaoProvider.class);

    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase mDB;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static Context mContext;

    private DaoProvider() {
    }

    public static void init(Context context){
        mContext = context;
        mHelper = new DaoMaster.DevOpenHelper(mContext, "tequila-db", null);
        mDB = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDB);
        mDaoSession = mDaoMaster.newSession();
    }

    public static synchronized DaoSession getSession() {
        if (mDaoSession == null) {
            throw new IllegalStateException(TAG + " 尚未实例化，请先使用init(Context context)调用");
        }
        return mDaoSession;
    }
}
