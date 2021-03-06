package tequila.dao.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;

import java.util.ArrayList;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "ORDERS".
*/
public class OrderDao extends AbstractDao<Order, Long> {

    public static final String TABLENAME = "ORDERS";

    /**
     * Properties of entity Order.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, String.class, "type", false, "TYPE");
        public final static Property Topic = new Property(2, String.class, "topic", false, "TOPIC");
        public final static Property StartTime = new Property(3, String.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(4, String.class, "endTime", false, "END_TIME");
        public final static Property Price = new Property(5, Double.class, "price", false, "PRICE");
        public final static Property Realtime = new Property(6, Boolean.class, "realtime", false, "REALTIME");
        public final static Property ContactName = new Property(7, String.class, "contactName", false, "CONTACT_NAME");
        public final static Property ContactGender = new Property(8, String.class, "contactGender", false, "CONTACT_GENDER");
        public final static Property ContactPhone = new Property(9, String.class, "contactPhone", false, "CONTACT_PHONE");
        public final static Property ExpertId = new Property(10, Long.class, "expertId", false, "EXPERT_ID");
        public final static Property UserId = new Property(11, Long.class, "userId", false, "USER_ID");
    };

    private DaoSession daoSession;


    public OrderDao(DaoConfig config) {
        super(config);
    }

    public OrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDERS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TYPE\" TEXT," + // 1: type
                "\"TOPIC\" TEXT," + // 2: topic
                "\"START_TIME\" TEXT," + // 3: startTime
                "\"END_TIME\" TEXT," + // 4: endTime
                "\"PRICE\" REAL," + // 5: price
                "\"REALTIME\" INTEGER," + // 6: realtime
                "\"CONTACT_NAME\" TEXT," + // 7: contactName
                "\"CONTACT_GENDER\" TEXT," + // 8: contactGender
                "\"CONTACT_PHONE\" TEXT," + // 9: contactPhone
                "\"EXPERT_ID\" INTEGER," + // 10: expertId
                "\"USER_ID\" INTEGER);"); // 11: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDERS\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Order entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String type = entity.getType();
        if (type != null) {
            stmt.bindString(2, type);
        }

        String topic = entity.getTopic();
        if (topic != null) {
            stmt.bindString(3, topic);
        }

        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(4, startTime);
        }

        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(5, endTime);
        }

        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(6, price);
        }

        Boolean realtime = entity.getRealtime();
        if (realtime != null) {
            stmt.bindLong(7, realtime ? 1L: 0L);
        }

        String contactName = entity.getContactName();
        if (contactName != null) {
            stmt.bindString(8, contactName);
        }

        String contactGender = entity.getContactGender();
        if (contactGender != null) {
            stmt.bindString(9, contactGender);
        }

        String contactPhone = entity.getContactPhone();
        if (contactPhone != null) {
            stmt.bindString(10, contactPhone);
        }

        Long expertId = entity.getExpertId();
        if (expertId != null) {
            stmt.bindLong(11, expertId);
        }

        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(12, userId);
        }
    }

    @Override
    protected void attachEntity(Order entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public Order readEntity(Cursor cursor, int offset) {
        Order entity = new Order( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // topic
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // startTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // endTime
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // price
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // realtime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // contactName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // contactGender
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // contactPhone
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // expertId
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11) // userId
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Order entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTopic(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStartTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEndTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPrice(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setRealtime(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setContactName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setContactGender(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setContactPhone(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setExpertId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setUserId(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
     }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Order entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(Order entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getExpertDao().getAllColumns());
            builder.append(" FROM ORDERS T");
            builder.append(" LEFT JOIN USER T0 ON T.\"USER_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN EXPERT T1 ON T.\"EXPERT_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected Order loadCurrentDeep(Cursor cursor, boolean lock) {
        Order entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);
        offset += daoSession.getUserDao().getAllColumns().length;

        Expert expert = loadCurrentOther(daoSession.getExpertDao(), cursor, offset);
        entity.setExpert(expert);

        return entity;
    }

    public Order loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Order> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Order> list = new ArrayList<Order>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }

    protected List<Order> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }


    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Order> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

}
