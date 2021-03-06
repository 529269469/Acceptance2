package com.example.acceptance2.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.acceptance2.greendao.bean.RelatedDocumentIdSetBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RELATED_DOCUMENT_ID_SET_BEAN".
*/
public class RelatedDocumentIdSetBeanDao extends AbstractDao<RelatedDocumentIdSetBean, Long> {

    public static final String TABLENAME = "RELATED_DOCUMENT_ID_SET_BEAN";

    /**
     * Properties of entity RelatedDocumentIdSetBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property DataPackageId = new Property(1, String.class, "dataPackageId", false, "DATA_PACKAGE_ID");
        public final static Property CheckFileId = new Property(2, String.class, "checkFileId", false, "CHECK_FILE_ID");
        public final static Property CheckGroupId = new Property(3, String.class, "checkGroupId", false, "CHECK_GROUP_ID");
        public final static Property CheckItemId = new Property(4, String.class, "checkItemId", false, "CHECK_ITEM_ID");
        public final static Property RelatedDocumentId = new Property(5, String.class, "RelatedDocumentId", false, "RELATED_DOCUMENT_ID");
    }


    public RelatedDocumentIdSetBeanDao(DaoConfig config) {
        super(config);
    }
    
    public RelatedDocumentIdSetBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RELATED_DOCUMENT_ID_SET_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"DATA_PACKAGE_ID\" TEXT," + // 1: dataPackageId
                "\"CHECK_FILE_ID\" TEXT," + // 2: checkFileId
                "\"CHECK_GROUP_ID\" TEXT," + // 3: checkGroupId
                "\"CHECK_ITEM_ID\" TEXT," + // 4: checkItemId
                "\"RELATED_DOCUMENT_ID\" TEXT);"); // 5: RelatedDocumentId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RELATED_DOCUMENT_ID_SET_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RelatedDocumentIdSetBean entity) {
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
 
        String checkItemId = entity.getCheckItemId();
        if (checkItemId != null) {
            stmt.bindString(5, checkItemId);
        }
 
        String RelatedDocumentId = entity.getRelatedDocumentId();
        if (RelatedDocumentId != null) {
            stmt.bindString(6, RelatedDocumentId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RelatedDocumentIdSetBean entity) {
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
 
        String checkItemId = entity.getCheckItemId();
        if (checkItemId != null) {
            stmt.bindString(5, checkItemId);
        }
 
        String RelatedDocumentId = entity.getRelatedDocumentId();
        if (RelatedDocumentId != null) {
            stmt.bindString(6, RelatedDocumentId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RelatedDocumentIdSetBean readEntity(Cursor cursor, int offset) {
        RelatedDocumentIdSetBean entity = new RelatedDocumentIdSetBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // dataPackageId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // checkFileId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // checkGroupId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // checkItemId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // RelatedDocumentId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RelatedDocumentIdSetBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataPackageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCheckFileId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCheckGroupId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCheckItemId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRelatedDocumentId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RelatedDocumentIdSetBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RelatedDocumentIdSetBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RelatedDocumentIdSetBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
