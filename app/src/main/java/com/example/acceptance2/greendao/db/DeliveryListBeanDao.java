package com.example.acceptance2.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.acceptance2.greendao.bean.DeliveryListBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DELIVERY_LIST_BEAN".
*/
public class DeliveryListBeanDao extends AbstractDao<DeliveryListBean, Long> {

    public static final String TABLENAME = "DELIVERY_LIST_BEAN";

    /**
     * Properties of entity DeliveryListBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property DataPackageId = new Property(1, String.class, "dataPackageId", false, "DATA_PACKAGE_ID");
        public final static Property Id = new Property(2, String.class, "id", false, "ID");
        public final static Property IsParent = new Property(3, String.class, "isParent", false, "IS_PARENT");
        public final static Property Project = new Property(4, String.class, "project", false, "PROJECT");
        public final static Property ParentId = new Property(5, String.class, "parentId", false, "PARENT_ID");
        public final static Property UniqueValue = new Property(6, String.class, "uniqueValue", false, "UNIQUE_VALUE");
        public final static Property TypeDisplay = new Property(7, String.class, "typeDisplay", false, "TYPE_DISPLAY");
        public final static Property SortBy = new Property(8, String.class, "sortBy", false, "SORT_BY");
        public final static Property Sort = new Property(9, String.class, "sort", false, "SORT");
    }


    public DeliveryListBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DeliveryListBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DELIVERY_LIST_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"DATA_PACKAGE_ID\" TEXT," + // 1: dataPackageId
                "\"ID\" TEXT," + // 2: id
                "\"IS_PARENT\" TEXT," + // 3: isParent
                "\"PROJECT\" TEXT," + // 4: project
                "\"PARENT_ID\" TEXT," + // 5: parentId
                "\"UNIQUE_VALUE\" TEXT," + // 6: uniqueValue
                "\"TYPE_DISPLAY\" TEXT," + // 7: typeDisplay
                "\"SORT_BY\" TEXT," + // 8: sortBy
                "\"SORT\" TEXT);"); // 9: sort
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DELIVERY_LIST_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DeliveryListBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String dataPackageId = entity.getDataPackageId();
        if (dataPackageId != null) {
            stmt.bindString(2, dataPackageId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(3, id);
        }
 
        String isParent = entity.getIsParent();
        if (isParent != null) {
            stmt.bindString(4, isParent);
        }
 
        String project = entity.getProject();
        if (project != null) {
            stmt.bindString(5, project);
        }
 
        String parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindString(6, parentId);
        }
 
        String uniqueValue = entity.getUniqueValue();
        if (uniqueValue != null) {
            stmt.bindString(7, uniqueValue);
        }
 
        String typeDisplay = entity.getTypeDisplay();
        if (typeDisplay != null) {
            stmt.bindString(8, typeDisplay);
        }
 
        String sortBy = entity.getSortBy();
        if (sortBy != null) {
            stmt.bindString(9, sortBy);
        }
 
        String sort = entity.getSort();
        if (sort != null) {
            stmt.bindString(10, sort);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DeliveryListBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String dataPackageId = entity.getDataPackageId();
        if (dataPackageId != null) {
            stmt.bindString(2, dataPackageId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(3, id);
        }
 
        String isParent = entity.getIsParent();
        if (isParent != null) {
            stmt.bindString(4, isParent);
        }
 
        String project = entity.getProject();
        if (project != null) {
            stmt.bindString(5, project);
        }
 
        String parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindString(6, parentId);
        }
 
        String uniqueValue = entity.getUniqueValue();
        if (uniqueValue != null) {
            stmt.bindString(7, uniqueValue);
        }
 
        String typeDisplay = entity.getTypeDisplay();
        if (typeDisplay != null) {
            stmt.bindString(8, typeDisplay);
        }
 
        String sortBy = entity.getSortBy();
        if (sortBy != null) {
            stmt.bindString(9, sortBy);
        }
 
        String sort = entity.getSort();
        if (sort != null) {
            stmt.bindString(10, sort);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DeliveryListBean readEntity(Cursor cursor, int offset) {
        DeliveryListBean entity = new DeliveryListBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // dataPackageId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // isParent
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // project
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // parentId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // uniqueValue
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // typeDisplay
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // sortBy
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // sort
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DeliveryListBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataPackageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIsParent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProject(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setParentId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUniqueValue(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTypeDisplay(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSortBy(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSort(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DeliveryListBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DeliveryListBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DeliveryListBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
