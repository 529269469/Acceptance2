package com.example.acceptance2.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.acceptance2.greendao.bean.CheckTaskBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHECK_TASK_BEAN".
*/
public class CheckTaskBeanDao extends AbstractDao<CheckTaskBean, Long> {

    public static final String TABLENAME = "CHECK_TASK_BEAN";

    /**
     * Properties of entity CheckTaskBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property DataPackageId = new Property(1, String.class, "dataPackageId", false, "DATA_PACKAGE_ID");
        public final static Property Id = new Property(2, String.class, "id", false, "ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Code = new Property(4, String.class, "code", false, "CODE");
        public final static Property Issuer = new Property(5, String.class, "issuer", false, "ISSUER");
        public final static Property IssueDept = new Property(6, String.class, "issueDept", false, "ISSUE_DEPT");
        public final static Property Accepter = new Property(7, String.class, "accepter", false, "ACCEPTER");
        public final static Property AcceptDate = new Property(8, String.class, "acceptDate", false, "ACCEPT_DATE");
        public final static Property CheckDate = new Property(9, String.class, "checkDate", false, "CHECK_DATE");
        public final static Property Applicant = new Property(10, String.class, "applicant", false, "APPLICANT");
        public final static Property ApplyCompany = new Property(11, String.class, "applyCompany", false, "APPLY_COMPANY");
        public final static Property Phone = new Property(12, String.class, "phone", false, "PHONE");
        public final static Property DocTypeVal = new Property(13, String.class, "docTypeVal", false, "DOC_TYPE_VAL");
    }


    public CheckTaskBeanDao(DaoConfig config) {
        super(config);
    }
    
    public CheckTaskBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHECK_TASK_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"DATA_PACKAGE_ID\" TEXT," + // 1: dataPackageId
                "\"ID\" TEXT," + // 2: id
                "\"NAME\" TEXT," + // 3: name
                "\"CODE\" TEXT," + // 4: code
                "\"ISSUER\" TEXT," + // 5: issuer
                "\"ISSUE_DEPT\" TEXT," + // 6: issueDept
                "\"ACCEPTER\" TEXT," + // 7: accepter
                "\"ACCEPT_DATE\" TEXT," + // 8: acceptDate
                "\"CHECK_DATE\" TEXT," + // 9: checkDate
                "\"APPLICANT\" TEXT," + // 10: applicant
                "\"APPLY_COMPANY\" TEXT," + // 11: applyCompany
                "\"PHONE\" TEXT," + // 12: phone
                "\"DOC_TYPE_VAL\" TEXT);"); // 13: docTypeVal
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHECK_TASK_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CheckTaskBean entity) {
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
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(5, code);
        }
 
        String issuer = entity.getIssuer();
        if (issuer != null) {
            stmt.bindString(6, issuer);
        }
 
        String issueDept = entity.getIssueDept();
        if (issueDept != null) {
            stmt.bindString(7, issueDept);
        }
 
        String accepter = entity.getAccepter();
        if (accepter != null) {
            stmt.bindString(8, accepter);
        }
 
        String acceptDate = entity.getAcceptDate();
        if (acceptDate != null) {
            stmt.bindString(9, acceptDate);
        }
 
        String checkDate = entity.getCheckDate();
        if (checkDate != null) {
            stmt.bindString(10, checkDate);
        }
 
        String applicant = entity.getApplicant();
        if (applicant != null) {
            stmt.bindString(11, applicant);
        }
 
        String applyCompany = entity.getApplyCompany();
        if (applyCompany != null) {
            stmt.bindString(12, applyCompany);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(13, phone);
        }
 
        String docTypeVal = entity.getDocTypeVal();
        if (docTypeVal != null) {
            stmt.bindString(14, docTypeVal);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CheckTaskBean entity) {
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
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(5, code);
        }
 
        String issuer = entity.getIssuer();
        if (issuer != null) {
            stmt.bindString(6, issuer);
        }
 
        String issueDept = entity.getIssueDept();
        if (issueDept != null) {
            stmt.bindString(7, issueDept);
        }
 
        String accepter = entity.getAccepter();
        if (accepter != null) {
            stmt.bindString(8, accepter);
        }
 
        String acceptDate = entity.getAcceptDate();
        if (acceptDate != null) {
            stmt.bindString(9, acceptDate);
        }
 
        String checkDate = entity.getCheckDate();
        if (checkDate != null) {
            stmt.bindString(10, checkDate);
        }
 
        String applicant = entity.getApplicant();
        if (applicant != null) {
            stmt.bindString(11, applicant);
        }
 
        String applyCompany = entity.getApplyCompany();
        if (applyCompany != null) {
            stmt.bindString(12, applyCompany);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(13, phone);
        }
 
        String docTypeVal = entity.getDocTypeVal();
        if (docTypeVal != null) {
            stmt.bindString(14, docTypeVal);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CheckTaskBean readEntity(Cursor cursor, int offset) {
        CheckTaskBean entity = new CheckTaskBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // dataPackageId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // code
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // issuer
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // issueDept
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // accepter
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // acceptDate
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // checkDate
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // applicant
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // applyCompany
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // phone
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // docTypeVal
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CheckTaskBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataPackageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIssuer(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIssueDept(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAccepter(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAcceptDate(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCheckDate(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setApplicant(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setApplyCompany(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPhone(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setDocTypeVal(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CheckTaskBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CheckTaskBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CheckTaskBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
