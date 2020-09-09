package com.example.acceptance2.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.acceptance2.greendao.bean.DocumentBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOCUMENT_BEAN".
*/
public class DocumentBeanDao extends AbstractDao<DocumentBean, Long> {

    public static final String TABLENAME = "DOCUMENT_BEAN";

    /**
     * Properties of entity DocumentBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property DataPackageId = new Property(1, String.class, "dataPackageId", false, "DATA_PACKAGE_ID");
        public final static Property Id = new Property(2, String.class, "id", false, "ID");
        public final static Property Code = new Property(3, String.class, "code", false, "CODE");
        public final static Property Name = new Property(4, String.class, "name", false, "NAME");
        public final static Property Secret = new Property(5, String.class, "secret", false, "SECRET");
        public final static Property PayClassify = new Property(6, String.class, "payClassify", false, "PAY_CLASSIFY");
        public final static Property PayClassifyName = new Property(7, String.class, "payClassifyName", false, "PAY_CLASSIFY_NAME");
        public final static Property ModalCode = new Property(8, String.class, "modalCode", false, "MODAL_CODE");
        public final static Property ProductCodeName = new Property(9, String.class, "productCodeName", false, "PRODUCT_CODE_NAME");
        public final static Property ProductCode = new Property(10, String.class, "productCode", false, "PRODUCT_CODE");
        public final static Property Stage = new Property(11, String.class, "stage", false, "STAGE");
        public final static Property TechStatus = new Property(12, String.class, "techStatus", false, "TECH_STATUS");
        public final static Property Approver = new Property(13, String.class, "approver", false, "APPROVER");
        public final static Property ApprovalDate = new Property(14, String.class, "approvalDate", false, "APPROVAL_DATE");
        public final static Property Issl = new Property(15, String.class, "issl", false, "ISSL");
        public final static Property Conclusion = new Property(16, String.class, "conclusion", false, "CONCLUSION");
        public final static Property Description = new Property(17, String.class, "description", false, "DESCRIPTION");
        public final static Property OnLine = new Property(18, String.class, "onLine", false, "ON_LINE");
        public final static Property InfoUrl = new Property(19, String.class, "infoUrl", false, "INFO_URL");
        public final static Property UniqueValue = new Property(20, String.class, "uniqueValue", false, "UNIQUE_VALUE");
    }


    public DocumentBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DocumentBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOCUMENT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"DATA_PACKAGE_ID\" TEXT," + // 1: dataPackageId
                "\"ID\" TEXT," + // 2: id
                "\"CODE\" TEXT," + // 3: code
                "\"NAME\" TEXT," + // 4: name
                "\"SECRET\" TEXT," + // 5: secret
                "\"PAY_CLASSIFY\" TEXT," + // 6: payClassify
                "\"PAY_CLASSIFY_NAME\" TEXT," + // 7: payClassifyName
                "\"MODAL_CODE\" TEXT," + // 8: modalCode
                "\"PRODUCT_CODE_NAME\" TEXT," + // 9: productCodeName
                "\"PRODUCT_CODE\" TEXT," + // 10: productCode
                "\"STAGE\" TEXT," + // 11: stage
                "\"TECH_STATUS\" TEXT," + // 12: techStatus
                "\"APPROVER\" TEXT," + // 13: approver
                "\"APPROVAL_DATE\" TEXT," + // 14: approvalDate
                "\"ISSL\" TEXT," + // 15: issl
                "\"CONCLUSION\" TEXT," + // 16: conclusion
                "\"DESCRIPTION\" TEXT," + // 17: description
                "\"ON_LINE\" TEXT," + // 18: onLine
                "\"INFO_URL\" TEXT," + // 19: infoUrl
                "\"UNIQUE_VALUE\" TEXT);"); // 20: uniqueValue
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOCUMENT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DocumentBean entity) {
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
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(4, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
 
        String secret = entity.getSecret();
        if (secret != null) {
            stmt.bindString(6, secret);
        }
 
        String payClassify = entity.getPayClassify();
        if (payClassify != null) {
            stmt.bindString(7, payClassify);
        }
 
        String payClassifyName = entity.getPayClassifyName();
        if (payClassifyName != null) {
            stmt.bindString(8, payClassifyName);
        }
 
        String modalCode = entity.getModalCode();
        if (modalCode != null) {
            stmt.bindString(9, modalCode);
        }
 
        String productCodeName = entity.getProductCodeName();
        if (productCodeName != null) {
            stmt.bindString(10, productCodeName);
        }
 
        String productCode = entity.getProductCode();
        if (productCode != null) {
            stmt.bindString(11, productCode);
        }
 
        String stage = entity.getStage();
        if (stage != null) {
            stmt.bindString(12, stage);
        }
 
        String techStatus = entity.getTechStatus();
        if (techStatus != null) {
            stmt.bindString(13, techStatus);
        }
 
        String approver = entity.getApprover();
        if (approver != null) {
            stmt.bindString(14, approver);
        }
 
        String approvalDate = entity.getApprovalDate();
        if (approvalDate != null) {
            stmt.bindString(15, approvalDate);
        }
 
        String issl = entity.getIssl();
        if (issl != null) {
            stmt.bindString(16, issl);
        }
 
        String conclusion = entity.getConclusion();
        if (conclusion != null) {
            stmt.bindString(17, conclusion);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(18, description);
        }
 
        String onLine = entity.getOnLine();
        if (onLine != null) {
            stmt.bindString(19, onLine);
        }
 
        String infoUrl = entity.getInfoUrl();
        if (infoUrl != null) {
            stmt.bindString(20, infoUrl);
        }
 
        String uniqueValue = entity.getUniqueValue();
        if (uniqueValue != null) {
            stmt.bindString(21, uniqueValue);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DocumentBean entity) {
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
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(4, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
 
        String secret = entity.getSecret();
        if (secret != null) {
            stmt.bindString(6, secret);
        }
 
        String payClassify = entity.getPayClassify();
        if (payClassify != null) {
            stmt.bindString(7, payClassify);
        }
 
        String payClassifyName = entity.getPayClassifyName();
        if (payClassifyName != null) {
            stmt.bindString(8, payClassifyName);
        }
 
        String modalCode = entity.getModalCode();
        if (modalCode != null) {
            stmt.bindString(9, modalCode);
        }
 
        String productCodeName = entity.getProductCodeName();
        if (productCodeName != null) {
            stmt.bindString(10, productCodeName);
        }
 
        String productCode = entity.getProductCode();
        if (productCode != null) {
            stmt.bindString(11, productCode);
        }
 
        String stage = entity.getStage();
        if (stage != null) {
            stmt.bindString(12, stage);
        }
 
        String techStatus = entity.getTechStatus();
        if (techStatus != null) {
            stmt.bindString(13, techStatus);
        }
 
        String approver = entity.getApprover();
        if (approver != null) {
            stmt.bindString(14, approver);
        }
 
        String approvalDate = entity.getApprovalDate();
        if (approvalDate != null) {
            stmt.bindString(15, approvalDate);
        }
 
        String issl = entity.getIssl();
        if (issl != null) {
            stmt.bindString(16, issl);
        }
 
        String conclusion = entity.getConclusion();
        if (conclusion != null) {
            stmt.bindString(17, conclusion);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(18, description);
        }
 
        String onLine = entity.getOnLine();
        if (onLine != null) {
            stmt.bindString(19, onLine);
        }
 
        String infoUrl = entity.getInfoUrl();
        if (infoUrl != null) {
            stmt.bindString(20, infoUrl);
        }
 
        String uniqueValue = entity.getUniqueValue();
        if (uniqueValue != null) {
            stmt.bindString(21, uniqueValue);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DocumentBean readEntity(Cursor cursor, int offset) {
        DocumentBean entity = new DocumentBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // dataPackageId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // code
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // secret
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // payClassify
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // payClassifyName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // modalCode
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // productCodeName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // productCode
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // stage
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // techStatus
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // approver
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // approvalDate
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // issl
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // conclusion
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // description
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // onLine
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // infoUrl
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20) // uniqueValue
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DocumentBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDataPackageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSecret(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPayClassify(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPayClassifyName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setModalCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProductCodeName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setProductCode(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setStage(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setTechStatus(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setApprover(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setApprovalDate(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setIssl(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setConclusion(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setDescription(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setOnLine(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setInfoUrl(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setUniqueValue(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DocumentBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DocumentBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DocumentBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
