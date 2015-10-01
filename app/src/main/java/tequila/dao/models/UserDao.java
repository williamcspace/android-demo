package tequila.dao.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Nickname = new Property(2, String.class, "nickname", false, "NICKNAME");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property EmailVerified = new Property(4, Boolean.class, "emailVerified", false, "EMAIL_VERIFIED");
        public final static Property Phone = new Property(5, String.class, "phone", false, "PHONE");
        public final static Property PhoneVerified = new Property(6, Boolean.class, "phoneVerified", false, "PHONE_VERIFIED");
        public final static Property WechatId = new Property(7, String.class, "wechatId", false, "WECHAT_ID");
        public final static Property Group = new Property(8, String.class, "group", false, "GROUP");
        public final static Property Age = new Property(9, String.class, "age", false, "AGE");
        public final static Property Gender = new Property(10, String.class, "gender", false, "GENDER");
        public final static Property Portrait = new Property(11, String.class, "portrait", false, "PORTRAIT");
        public final static Property Status = new Property(12, Integer.class, "status", false, "STATUS");
        public final static Property Balance = new Property(13, Double.class, "balance", false, "BALANCE");
        public final static Property Banned = new Property(14, Boolean.class, "banned", false, "BANNED");
        public final static Property Deleted = new Property(15, Boolean.class, "deleted", false, "DELETED");
        public final static Property CreatedAt = new Property(16, java.util.Date.class, "createdAt", false, "CREATED_AT");
        public final static Property LastLogin = new Property(17, java.util.Date.class, "lastLogin", false, "LAST_LOGIN");
        public final static Property MigrationId = new Property(18, Integer.class, "migrationId", false, "MIGRATION_ID");
        public final static Property IsAdmin = new Property(19, Boolean.class, "isAdmin", false, "IS_ADMIN");
        public final static Property GroupName = new Property(20, String.class, "groupName", false, "GROUP_NAME");
        public final static Property ExpertId = new Property(21, Integer.class, "expertId", false, "EXPERT_ID");
    };


    public UserDao(DaoConfig config) {
        super(config);
    }

    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USERNAME\" TEXT," + // 1: username
                "\"NICKNAME\" TEXT," + // 2: nickname
                "\"EMAIL\" TEXT," + // 3: email
                "\"EMAIL_VERIFIED\" INTEGER," + // 4: emailVerified
                "\"PHONE\" TEXT," + // 5: phone
                "\"PHONE_VERIFIED\" INTEGER," + // 6: phoneVerified
                "\"WECHAT_ID\" TEXT," + // 7: wechatId
                "\"GROUP\" TEXT," + // 8: group
                "\"AGE\" TEXT," + // 9: age
                "\"GENDER\" TEXT," + // 10: gender
                "\"PORTRAIT\" TEXT," + // 11: portrait
                "\"STATUS\" INTEGER," + // 12: status
                "\"BALANCE\" REAL," + // 13: balance
                "\"BANNED\" INTEGER," + // 14: banned
                "\"DELETED\" INTEGER," + // 15: deleted
                "\"CREATED_AT\" INTEGER," + // 16: createdAt
                "\"LAST_LOGIN\" INTEGER," + // 17: lastLogin
                "\"MIGRATION_ID\" INTEGER," + // 18: migrationId
                "\"IS_ADMIN\" INTEGER," + // 19: isAdmin
                "\"GROUP_NAME\" TEXT," + // 20: groupName
                "\"EXPERT_ID\" INTEGER);"); // 21: expertId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }

        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(3, nickname);
        }

        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }

        Boolean emailVerified = entity.getEmailVerified();
        if (emailVerified != null) {
            stmt.bindLong(5, emailVerified ? 1L: 0L);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(6, phone);
        }

        Boolean phoneVerified = entity.getPhoneVerified();
        if (phoneVerified != null) {
            stmt.bindLong(7, phoneVerified ? 1L: 0L);
        }

        String wechatId = entity.getWechatId();
        if (wechatId != null) {
            stmt.bindString(8, wechatId);
        }

        String group = entity.getGroup();
        if (group != null) {
            stmt.bindString(9, group);
        }

        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(10, age);
        }

        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(11, gender);
        }

        String portrait = entity.getPortrait();
        if (portrait != null) {
            stmt.bindString(12, portrait);
        }

        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(13, status);
        }

        Double balance = entity.getBalance();
        if (balance != null) {
            stmt.bindDouble(14, balance);
        }

        Boolean banned = entity.getBanned();
        if (banned != null) {
            stmt.bindLong(15, banned ? 1L: 0L);
        }

        Boolean deleted = entity.getDeleted();
        if (deleted != null) {
            stmt.bindLong(16, deleted ? 1L: 0L);
        }

        java.util.Date createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindLong(17, createdAt.getTime());
        }

        java.util.Date lastLogin = entity.getLastLogin();
        if (lastLogin != null) {
            stmt.bindLong(18, lastLogin.getTime());
        }

        Integer migrationId = entity.getMigrationId();
        if (migrationId != null) {
            stmt.bindLong(19, migrationId);
        }

        Boolean isAdmin = entity.getIsAdmin();
        if (isAdmin != null) {
            stmt.bindLong(20, isAdmin ? 1L: 0L);
        }

        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(21, groupName);
        }

        Integer expertId = entity.getExpertId();
        if (expertId != null) {
            stmt.bindLong(22, expertId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // nickname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // email
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // emailVerified
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // phone
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // phoneVerified
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // wechatId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // group
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // age
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // gender
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // portrait
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // status
            cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13), // balance
            cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0, // banned
            cursor.isNull(offset + 15) ? null : cursor.getShort(offset + 15) != 0, // deleted
            cursor.isNull(offset + 16) ? null : new java.util.Date(cursor.getLong(offset + 16)), // createdAt
            cursor.isNull(offset + 17) ? null : new java.util.Date(cursor.getLong(offset + 17)), // lastLogin
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // migrationId
            cursor.isNull(offset + 19) ? null : cursor.getShort(offset + 19) != 0, // isAdmin
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // groupName
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21) // expertId
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNickname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmailVerified(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setPhone(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPhoneVerified(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setWechatId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setGroup(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAge(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setGender(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPortrait(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setStatus(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setBalance(cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13));
        entity.setBanned(cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0);
        entity.setDeleted(cursor.isNull(offset + 15) ? null : cursor.getShort(offset + 15) != 0);
        entity.setCreatedAt(cursor.isNull(offset + 16) ? null : new java.util.Date(cursor.getLong(offset + 16)));
        entity.setLastLogin(cursor.isNull(offset + 17) ? null : new java.util.Date(cursor.getLong(offset + 17)));
        entity.setMigrationId(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setIsAdmin(cursor.isNull(offset + 19) ? null : cursor.getShort(offset + 19) != 0);
        entity.setGroupName(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setExpertId(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
     }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(User entity) {
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

}
