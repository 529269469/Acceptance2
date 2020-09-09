package com.example.acceptance2.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.acceptance2.greendao.bean.AcceptDeviceBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACCEPT_DEVICE_BEAN".
*/
public class AcceptDeviceBeanDao extends AbstractDao<AcceptDeviceBean, Long> {

    public static final String TABLENAME = "ACCEPT_DEVICE_BEAN";

    /**
     * Properties of entity AcceptDeviceBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property DataPackageId = new Property(1, String.class, "dataPackageId", false, "DATA_PACKAGE_ID");
        public final static Property CheckFileId = new Property(2, String.class, "checkFileId", false, "CHECK_FILE_ID");
        public final static Property CheckGroupId = new Property(3, String.class, "checkGroupId", false, "CHECK_GROUP_ID");
        public final static Property Id = new Property(4, String.class, "id", false, "ID");
        public final static Property Name = new Property(5, String.class, "name", false, "NAME");
        public final static Property Specification = new Property(6, String.class, "specification", false, "SPECIFICATION");
        public final static Property Accuracy = new Property(7, String.class, "accuracy", false, "ACCURACY");
        public final static Property Certificate = new Property(8, String.class, "certificate", false, "CERTIFICATE");
        public final static Property Description = new Property(9, String.class, "description", false, "DESCRIPTION");
    }


    public AcceptDeviceBeanDao(DaoConfig config) {
        super(config);
    }
    
    public AcceptDeviceBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACCEPT_DEVICE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"DATA_PACKAGE_ID\" TEXT," + // 1: dataPackageId
                "\"CHECK_FILE_ID\" TEXT," + // 2: checkFileId
                "\"CHECK_GROUP_ID\" TEXT," + // 3: checkGroupId
                "\"ID\" TEXT," + // 4: id
                "\"NAME\" TEXT," + // 5: name
                "\"SPECIFICATION\" TEXT," + // 6: specification
                "\"ACCURACY\" TEXT," + // 7: accuracy
                "\"CERTIFICATE\" TEXT," + // 8: certificate
                "\"DESCRIPTION\" TEXT);"); // 9: description
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACCEPT_DEVICE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AcceptDeviceBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String dataPackageId = entity.getDataPackageId();
        if (dataPackageId != null) {
            stmt.bindString(2, dataPackageId);
        }
 
        String checkFileId = entity.getCheckFileId();
        if (checkFileId != null) {
            stmt.bindString(3, checkFileId);
        }
 
        String checkGroupId = entity.getCheckGroupId();
        if (checkGroupId != null) {
            stmt.bindString(4, checkGroupId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(6, name);
        }
 
        String specification = entity.getSpecification();
        if (specification != null) {
            stmt.bindString(7, specification);
        }
 
        String accuracy = entity.getAccuracy();
        if (accuracy != null) {
            stmt.bindString(8, accuracy);
        }
 
        String certificate = entity.getCertificate();
        if (certificate != null) {
            stmt.bindString(9, certificate);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(10, description);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AcceptDeviceBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String dataPackageId = entity.getDataPackageId();
        if (dataPackageId != null) {
            stmt.bindString(2, dataPackageId);
        }
 
        String checkFileId = entity.getCheckFileId();
        if (checkFileId != null) {
            stmt.bindString(3, checkFileId);
        }
 
        String checkGroupId = entity.getCheckGroupId();
        if (checkGroupId != null) {
            stmt.bindString(4, checkGroupId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(6, name);
        }
 
        String specification = entity.getSpecification();
        if (specification != null) {
            stmt.bindString(7, specification);
        }
 
        String accuracy = entity.getAccuracy();
        if (accuracy != null) {
            stmt.bindString(8, accuracy);
        }
 
        String certificate = entity.getCertificate();
        if (certificate != null) {
            stmt.bindString(9, certificate);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(10, description);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AcceptDeviceBean readEntity(Cursor cursor, int offset) {
        AcceptDeviceBean entity = new AcceptDeviceBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // dataPackageId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // checkFileId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // checkGroupId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // id
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // name
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // specification
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // accuracy
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // certificate
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // description
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AcceptDeviceBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataPackageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCheckFileId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCheckGroupId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSpecification(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAccuracy(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCertificate(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDescription(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AcceptDeviceBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AcceptDeviceBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AcceptDeviceBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
