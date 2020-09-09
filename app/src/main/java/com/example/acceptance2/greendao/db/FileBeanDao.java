package com.example.acceptance2.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.acceptance2.greendao.bean.FileBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FILE_BEAN".
*/
public class FileBeanDao extends AbstractDao<FileBean, Long> {

    public static final String TABLENAME = "FILE_BEAN";

    /**
     * Properties of entity FileBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property DataPackageId = new Property(1, String.class, "dataPackageId", false, "DATA_PACKAGE_ID");
        public final static Property DocumentId = new Property(2, String.class, "documentId", false, "DOCUMENT_ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Path = new Property(4, String.class, "path", false, "PATH");
        public final static Property Type = new Property(5, String.class, "type", false, "TYPE");
        public final static Property Secret = new Property(6, String.class, "secret", false, "SECRET");
        public final static Property DisabledSecret = new Property(7, String.class, "disabledSecret", false, "DISABLED_SECRET");
    }


    public FileBeanDao(DaoConfig config) {
        super(config);
    }
    
    public FileBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FILE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"DATA_PACKAGE_ID\" TEXT," + // 1: dataPackageId
                "\"DOCUMENT_ID\" TEXT," + // 2: documentId
                "\"NAME\" TEXT," + // 3: name
                "\"PATH\" TEXT," + // 4: path
                "\"TYPE\" TEXT," + // 5: type
                "\"SECRET\" TEXT," + // 6: secret
                "\"DISABLED_SECRET\" TEXT);"); // 7: disabledSecret
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FILE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FileBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String dataPackageId = entity.getDataPackageId();
        if (dataPackageId != null) {
            stmt.bindString(2, dataPackageId);
        }
 
        String documentId = entity.getDocumentId();
        if (documentId != null) {
            stmt.bindString(3, documentId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(5, path);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(6, type);
        }
 
        String secret = entity.getSecret();
        if (secret != null) {
            stmt.bindString(7, secret);
        }
 
        String disabledSecret = entity.getDisabledSecret();
        if (disabledSecret != null) {
            stmt.bindString(8, disabledSecret);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FileBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String dataPackageId = entity.getDataPackageId();
        if (dataPackageId != null) {
            stmt.bindString(2, dataPackageId);
        }
 
        String documentId = entity.getDocumentId();
        if (documentId != null) {
            stmt.bindString(3, documentId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(5, path);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(6, type);
        }
 
        String secret = entity.getSecret();
        if (secret != null) {
            stmt.bindString(7, secret);
        }
 
        String disabledSecret = entity.getDisabledSecret();
        if (disabledSecret != null) {
            stmt.bindString(8, disabledSecret);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FileBean readEntity(Cursor cursor, int offset) {
        FileBean entity = new FileBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // dataPackageId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // documentId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // path
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // secret
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // disabledSecret
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FileBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataPackageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDocumentId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setType(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSecret(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDisabledSecret(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FileBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FileBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FileBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
