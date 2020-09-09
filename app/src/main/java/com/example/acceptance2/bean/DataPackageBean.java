package com.example.acceptance2.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/12 17
 */
@XStreamAlias("DataPackage")
public class DataPackageBean {
    @XStreamAsAttribute()
    @XStreamAlias("id") //属性注解
    private String id;
    private String name;
    private String code;
    private String type;
    private String responseUnit;
    private String productName;
    private String productCode;
    private String productType;
    private String batch;
    private String creator;
    private String createTime;
    private String modelCode;
    private String modelSeries;
    private String modelSeriesName;
    private String pkgTemplateId;
    private String lifecycleStateId;
    private String lifecycleStateIdentifier;
    private String baseType;
    private String modelSeriesId;
    private String repositoryId;
    private String isTemplate;
    private String ownerId;
    private String defaultTemplate;
    private CheckApplyBean checkApply;
    private CheckTaskBean checkTask;
    private ApplyItemSetBean ApplyItemSet;
    private CheckFileSetBean CheckFileSet;
    private CheckVerdBean checkVerd;
    private CheckUnresolvedBean checkUnresolved;
    private UnresolvedSetBean UnresolvedSet;
    private DeliveryListsBean DeliveryLists;
    private DocumentListSetBean DocumentListSet;

    private String productTypeValue;
    private String applyCompany;
    private String acceptorUnit;
    private String stage;
    private String uniqueValue;

    private String versionInfo;

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getAcceptorUnit() {
        return acceptorUnit;
    }

    public void setAcceptorUnit(String acceptorUnit) {
        this.acceptorUnit = acceptorUnit;
    }

    public String getProductTypeValue() {
        return productTypeValue;
    }

    public void setProductTypeValue(String productTypeValue) {
        this.productTypeValue = productTypeValue;
    }

    public String getApplyCompany() {
        return applyCompany;
    }

    public void setApplyCompany(String applyCompany) {
        this.applyCompany = applyCompany;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }

    public String getModelSeriesId() {
        return modelSeriesId;
    }

    public void setModelSeriesId(String modelSeriesId) {
        this.modelSeriesId = modelSeriesId;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getLifecycleStateIdentifier() {
        return lifecycleStateIdentifier;
    }

    public void setLifecycleStateIdentifier(String lifecycleStateIdentifier) {
        this.lifecycleStateIdentifier = lifecycleStateIdentifier;
    }

    public String getPkgTemplateId() {
        return pkgTemplateId;
    }

    public void setPkgTemplateId(String pkgTemplateId) {
        this.pkgTemplateId = pkgTemplateId;
    }

    public String getLifecycleStateId() {
        return lifecycleStateId;
    }

    public void setLifecycleStateId(String lifecycleStateId) {
        this.lifecycleStateId = lifecycleStateId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelSeries() {
        return modelSeries;
    }

    public void setModelSeries(String modelSeries) {
        this.modelSeries = modelSeries;
    }

    public String getModelSeriesName() {
        return modelSeriesName;
    }

    public void setModelSeriesName(String modelSeriesName) {
        this.modelSeriesName = modelSeriesName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponseUnit() {
        return responseUnit;
    }

    public void setResponseUnit(String responseUnit) {
        this.responseUnit = responseUnit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public CheckApplyBean getCheckApply() {
        return checkApply;
    }

    public void setCheckApply(CheckApplyBean checkApply) {
        this.checkApply = checkApply;
    }

    public CheckTaskBean getCheckTask() {
        return checkTask;
    }

    public void setCheckTask(CheckTaskBean checkTask) {
        this.checkTask = checkTask;
    }

    public ApplyItemSetBean getApplyItemSet() {
        return ApplyItemSet;
    }

    public void setApplyItemSet(ApplyItemSetBean ApplyItemSet) {
        this.ApplyItemSet = ApplyItemSet;
    }

    public CheckFileSetBean getCheckFileSet() {
        return CheckFileSet;
    }

    public void setCheckFileSet(CheckFileSetBean CheckFileSet) {
        this.CheckFileSet = CheckFileSet;
    }

    public CheckVerdBean getCheckVerd() {
        return checkVerd;
    }

    public void setCheckVerd(CheckVerdBean checkVerd) {
        this.checkVerd = checkVerd;
    }

    public CheckUnresolvedBean getCheckUnresolved() {
        return checkUnresolved;
    }

    public void setCheckUnresolved(CheckUnresolvedBean checkUnresolved) {
        this.checkUnresolved = checkUnresolved;
    }

    public UnresolvedSetBean getUnresolvedSet() {
        return UnresolvedSet;
    }

    public void setUnresolvedSet(UnresolvedSetBean UnresolvedSet) {
        this.UnresolvedSet = UnresolvedSet;
    }

    public DeliveryListsBean getDeliveryLists() {
        return DeliveryLists;
    }

    public void setDeliveryLists(DeliveryListsBean DeliveryLists) {
        this.DeliveryLists = DeliveryLists;
    }

    public DocumentListSetBean getDocumentListSet() {
        return DocumentListSet;
    }

    public void setDocumentListSet(DocumentListSetBean DocumentListSet) {
        this.DocumentListSet = DocumentListSet;
    }

    public static class CheckApplyBean {
        /**
         * -id : 361408589110538240
         * name : Pad导出导入测试验收申请
         * code : padImport01-SQ
         * contractCode : 合同编号··
         * contractName : 合同编号
         * applicant : 申请人
         * applyCompany : 申请单位
         * phone : 1568
         * conclusion : 测试验收申请内部审查结论
         * description : 测试验收申请备注
         */

        @XStreamAsAttribute()
        @XStreamAlias("id") //属性注解
        private String id;
        private String name;
        private String code;
        private String contractCode;
        private String contractName;
        private String applicant;
        private String applyCompany;
        private String phone;
        private String conclusion;
        private String description;
        private String docTypeVal;
        private DocumentListSetBean.DocumentBean.FileSetBean FileSet;
        private String acceptorUnit;
        private String acceptor;
        private String acceptorDept;

        public String getAcceptorDept() {
            return acceptorDept;
        }

        public void setAcceptorDept(String acceptorDept) {
            this.acceptorDept = acceptorDept;
        }

        public String getAcceptor() {
            return acceptor;
        }

        public void setAcceptor(String acceptor) {
            this.acceptor = acceptor;
        }

        public DocumentListSetBean.DocumentBean.FileSetBean getFileSet() {
            return FileSet;
        }

        public void setFileSet(DocumentListSetBean.DocumentBean.FileSetBean fileSet) {
            FileSet = fileSet;
        }

        public String getAcceptorUnit() {
            return acceptorUnit;
        }

        public void setAcceptorUnit(String acceptorUnit) {
            this.acceptorUnit = acceptorUnit;
        }

        public String getDocTypeVal() {
            return docTypeVal;
        }

        public void setDocTypeVal(String docTypeVal) {
            this.docTypeVal = docTypeVal;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getContractCode() {
            return contractCode;
        }

        public void setContractCode(String contractCode) {
            this.contractCode = contractCode;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }

        public String getApplicant() {
            return applicant;
        }

        public void setApplicant(String applicant) {
            this.applicant = applicant;
        }

        public String getApplyCompany() {
            return applyCompany;
        }

        public void setApplyCompany(String applyCompany) {
            this.applyCompany = applyCompany;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getConclusion() {
            return conclusion;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class CheckTaskBean {
        /**
         * -id : 361408589127315456
         * name : Pad导出导入测试验收任务单
         * code : padImport01-RW
         * issuer : 签发人
         * issueDept : 签发部门
         * accepter : 产品接收人
         * acceptDate : 2019-09-26
         * checkDate : 2019-09-28
         * applicant : 申请人
         * applyCompany : 申请单位
         * phone : 1568
         */

        @XStreamAsAttribute()
        @XStreamAlias("id") //属性注解
        private String id;
        private String name;
        private String code;
        private String issuer;
        private String issueDept;
        private String accepter;
        private String acceptDate;
        private String checkDate;
        private String applicant;
        private String applyCompany;
        private String phone;
        private String docTypeVal;
        private ApplyDeptSetBean ApplyDeptSet;

        public String getDocTypeVal() {
            return docTypeVal;
        }

        public void setDocTypeVal(String docTypeVal) {
            this.docTypeVal = docTypeVal;
        }

        public ApplyDeptSetBean getApplyDeptSet() {
            return ApplyDeptSet;
        }

        public void setApplyDeptSet(ApplyDeptSetBean applyDeptSet) {
            ApplyDeptSet = applyDeptSet;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIssuer() {
            return issuer;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public String getIssueDept() {
            return issueDept;
        }

        public void setIssueDept(String issueDept) {
            this.issueDept = issueDept;
        }

        public String getAccepter() {
            return accepter;
        }

        public void setAccepter(String accepter) {
            this.accepter = accepter;
        }

        public String getAcceptDate() {
            return acceptDate;
        }

        public void setAcceptDate(String acceptDate) {
            this.acceptDate = acceptDate;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getApplicant() {
            return applicant;
        }

        public void setApplicant(String applicant) {
            this.applicant = applicant;
        }

        public String getApplyCompany() {
            return applyCompany;
        }

        public void setApplyCompany(String applyCompany) {
            this.applyCompany = applyCompany;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public static class ApplyDeptSetBean {
            @XStreamImplicit(itemFieldName = "applyDept")
            private List<ApplyDeptBean> ApplyDept;

            public List<ApplyDeptBean> getApplyDept() {
                return ApplyDept;
            }

            public void setApplyDept(List<ApplyDeptBean> applyDept) {
                ApplyDept = applyDept;
            }

            public static class ApplyDeptBean {
                @XStreamAsAttribute()
                @XStreamAlias("id") //属性注解
                private String id;
                private String department;
                private String acceptor;
                private String other;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDepartment() {
                    return department;
                }

                public void setDepartment(String department) {
                    this.department = department;
                }

                public String getAcceptor() {
                    return acceptor;
                }

                public void setAcceptor(String acceptor) {
                    this.acceptor = acceptor;
                }

                public String getOther() {
                    return other;
                }

                public void setOther(String other) {
                    this.other = other;
                }
            }

        }

    }

    public static class ApplyItemSetBean {
        @XStreamImplicit(itemFieldName = "ApplyItem")
        private List<ApplyItemBean> ApplyItem;

        public List<ApplyItemBean> getApplyItem() {
            return ApplyItem;
        }

        public void setApplyItem(List<ApplyItemBean> ApplyItem) {
            this.ApplyItem = ApplyItem;
        }

        public static class ApplyItemBean {
            /**
             * -id : 361408589278310400
             * productCodeName : cpdh
             * productCode : cpbh
             * productStatus : cpzt
             * isPureCheck : true
             * isArmyCheck : true
             * isCompleteChoice : true
             * isCompleteRoutine : true
             * isSatisfyRequire : true
             * description : 申请向备注
             * productName : 产品名称
             */

            @XStreamAsAttribute()
            @XStreamAlias("id") //属性注解
            private String id;
            private String productCodeName;
            private String productCode;
            private String productStatus;
            private String isPureCheck;
            private String isArmyCheck;
            private String isCompleteChoice;
            private String isCompleteRoutine;
            private String isSatisfyRequire;
            private String description;
            private String productName;
            private String checkCount;
            private String passCheck;
            private String uniqueValue;

            public String getUniqueValue() {
                return uniqueValue;
            }

            public void setUniqueValue(String uniqueValue) {
                this.uniqueValue = uniqueValue;
            }

            public String getPassCheck() {
                return passCheck;
            }

            public void setPassCheck(String passCheck) {
                this.passCheck = passCheck;
            }

            public String getCheckCount() {
                return checkCount;
            }

            public void setCheckCount(String checkCount) {
                this.checkCount = checkCount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductCodeName() {
                return productCodeName;
            }

            public void setProductCodeName(String productCodeName) {
                this.productCodeName = productCodeName;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getProductStatus() {
                return productStatus;
            }

            public void setProductStatus(String productStatus) {
                this.productStatus = productStatus;
            }

            public String getIsPureCheck() {
                return isPureCheck;
            }

            public void setIsPureCheck(String isPureCheck) {
                this.isPureCheck = isPureCheck;
            }

            public String getIsArmyCheck() {
                return isArmyCheck;
            }

            public void setIsArmyCheck(String isArmyCheck) {
                this.isArmyCheck = isArmyCheck;
            }

            public String getIsCompleteChoice() {
                return isCompleteChoice;
            }

            public void setIsCompleteChoice(String isCompleteChoice) {
                this.isCompleteChoice = isCompleteChoice;
            }

            public String getIsCompleteRoutine() {
                return isCompleteRoutine;
            }

            public void setIsCompleteRoutine(String isCompleteRoutine) {
                this.isCompleteRoutine = isCompleteRoutine;
            }

            public String getIsSatisfyRequire() {
                return isSatisfyRequire;
            }

            public void setIsSatisfyRequire(String isSatisfyRequire) {
                this.isSatisfyRequire = isSatisfyRequire;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }
        }
    }

    public static class CheckFileSetBean {
        @XStreamImplicit(itemFieldName = "CheckFile")
        private List<CheckFileBean> CheckFile;

        public List<CheckFileBean> getCheckFile() {
            return CheckFile;
        }

        public void setCheckFile(List<CheckFileBean> CheckFile) {
            this.CheckFile = CheckFile;
        }

        public static class CheckFileBean {
            /**
             * -id : 361408581938278400
             * name : Pad导出导入测试齐套性检查
             * code : padImport01-07
             * docType : 齐套性检查
             * conclusion : 检查单结论
             * checkPerson : 检查单检查人
             * CheckGroupSet : {"CheckGroup":[{"-id":"361408582030553088","groupName":"测试导出检查组","checkGroupConclusion":"检查组结论","checkPerson":"检查组检查人","isConclusion":"true","isTable":"true","PropertySet":{"Property":{"name":"扩展属性一","value":"检查组扩展属性值"}},"CheckItemSet":{"CheckItem":{"-id":"361408582131216384","name":"测试检查xiang","options":"是,否,有","selected":"是","PropertySet":{"Property":{"name":"扩展属性一","value":"扩展值"}},"RelatedDocumentIdSet":{"RelatedDocumentId":"361439270231687168"}}}},{"-id":"361425531942789120","groupName":"测试电气产品齐套性检查组","isConclusion":"false","isTable":"false"}]}
             */

            @XStreamAsAttribute()
            @XStreamAlias("id") //属性注解
            private String id;
            private String name;
            private String code;
            private String docType;
            private String productType;
            private String conclusion;
            private String checkPerson;
            private String checkDate;
            private String sortBy;
            private CheckGroupSetBean CheckGroupSet;

            private String checkTime;
            private String sort;
            private String tabsName;
            private String accordFile;
            private String selectEdit;
            private String uniqueValue;
            private String productTypeValue;
            private String description;

            private CheckItemRelateSetBean CheckItemRelateSet;

            public CheckItemRelateSetBean getCheckItemRelateSet() {
                return CheckItemRelateSet;
            }

            public void setCheckItemRelateSet(CheckItemRelateSetBean checkItemRelateSet) {
                CheckItemRelateSet = checkItemRelateSet;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getProductTypeValue() {
                return productTypeValue;
            }

            public void setProductTypeValue(String productTypeValue) {
                this.productTypeValue = productTypeValue;
            }

            public String getUniqueValue() {
                return uniqueValue;
            }

            public void setUniqueValue(String uniqueValue) {
                this.uniqueValue = uniqueValue;
            }

            public String getSelectEdit() {
                return selectEdit;
            }

            public void setSelectEdit(String selectEdit) {
                this.selectEdit = selectEdit;
            }

            public String getAccordFile() {
                return accordFile;
            }

            public void setAccordFile(String accordFile) {
                this.accordFile = accordFile;
            }

            public String getTabsName() {
                return tabsName;
            }

            public void setTabsName(String tabsName) {
                this.tabsName = tabsName;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCheckTime() {
                return checkTime;
            }

            public void setCheckTime(String checkTime) {
                this.checkTime = checkTime;
            }

            public String getSortBy() {
                return sortBy;
            }

            public void setSortBy(String sortBy) {
                this.sortBy = sortBy;
            }

            public String getCheckDate() {
                return checkDate;
            }

            public void setCheckDate(String checkDate) {
                this.checkDate = checkDate;
            }

            private DocumentListSetBean.DocumentBean.FileSetBean FileSet;

            public DocumentListSetBean.DocumentBean.FileSetBean getFileSet() {
                return FileSet;
            }

            public void setFileSet(DocumentListSetBean.DocumentBean.FileSetBean fileSet) {
                FileSet = fileSet;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDocType() {
                return docType;
            }

            public void setDocType(String docType) {
                this.docType = docType;
            }

            public String getConclusion() {
                return conclusion;
            }

            public void setConclusion(String conclusion) {
                this.conclusion = conclusion;
            }

            public String getCheckPerson() {
                return checkPerson;
            }

            public void setCheckPerson(String checkPerson) {
                this.checkPerson = checkPerson;
            }

            public CheckGroupSetBean getCheckGroupSet() {
                return CheckGroupSet;
            }


            public void setCheckGroupSet(CheckGroupSetBean CheckGroupSet) {
                this.CheckGroupSet = CheckGroupSet;
            }


            public static class CheckItemRelateSetBean {

                @XStreamImplicit(itemFieldName = "CheckItemRelate")
                private List<CheckItemRelateBean> CheckItemRelate;

                public List<CheckItemRelateBean> getCheckItemRelate() {
                    return CheckItemRelate;
                }

                public void setCheckItemRelate(List<CheckItemRelateBean> checkItemRelate) {
                    CheckItemRelate = checkItemRelate;
                }

                public static class CheckItemRelateBean {
                    /**
                     * 检查项id
                     */
                    private String targetId;

                    /**
                     * 关联的检查项id
                     */
                    private String relateItemId;

                    /**
                     * 选项
                     */
                    private String selected;


                    public String getTargetId() {
                        return targetId;
                    }

                    public void setTargetId(String targetId) {
                        this.targetId = targetId;
                    }

                    public String getRelateItemId() {
                        return relateItemId;
                    }

                    public void setRelateItemId(String relateItemId) {
                        this.relateItemId = relateItemId;
                    }

                    public String getSelected() {
                        return selected;
                    }

                    public void setSelected(String selected) {
                        this.selected = selected;
                    }
                }

            }


            public static class CheckGroupSetBean {
                @XStreamImplicit(itemFieldName = "CheckGroup")
                private List<CheckGroupBean> CheckGroup;

                public List<CheckGroupBean> getCheckGroup() {
                    return CheckGroup;
                }

                public void setCheckGroup(List<CheckGroupBean> CheckGroup) {
                    this.CheckGroup = CheckGroup;
                }

                public static class CheckGroupBean {
                    /**
                     * -id : 361408582030553088
                     * groupName : 测试导出检查组
                     * checkGroupConclusion : 检查组结论
                     * checkPerson : 检查组检查人
                     * isConclusion : true
                     * isTable : true
                     * PropertySet : {"Property":{"name":"扩展属性一","value":"检查组扩展属性值"}}
                     * CheckItemSet : {"CheckItem":{"-id":"361408582131216384","name":"测试检查xiang","options":"是,否,有","selected":"是","PropertySet":{"Property":{"name":"扩展属性一","value":"扩展值"}},"RelatedDocumentIdSet":{"RelatedDocumentId":"361439270231687168"}}}
                     */

                    @XStreamAsAttribute()
                    @XStreamAlias("id") //属性注解
                    private String id;
                    private String groupName;
                    private String checkGroupConclusion;
                    private String checkPerson;
                    private String isConclusion;
                    private String isTable;
                    private String uniqueValue;
                    private PropertySetBean PropertySet;
                    private CheckItemSetBean CheckItemSet;
                    private AcceptDeviceSet AcceptDeviceSet;
                    private DocumentListSetBean.DocumentBean.FileSetBean FileSet;

                    private String checkTime;
                    private String conclusionF;
                    private String checkPersonF;
                    private String sort;

                    private String checkTimeF;
                    private String testTable;

                    private TestTabSet TestTabSet;

                    public CheckGroupBean.TestTabSet getTestTabSet() {
                        return TestTabSet;
                    }

                    public void setTestTabSet(CheckGroupBean.TestTabSet testTabSet) {
                        TestTabSet = testTabSet;
                    }

                    private CheckItemRelateSetBean CheckItemRelateSet;

                    public CheckItemRelateSetBean getCheckItemRelateSet() {
                        return CheckItemRelateSet;
                    }

                    public void setCheckItemRelateSet(CheckItemRelateSetBean checkItemRelateSet) {
                        CheckItemRelateSet = checkItemRelateSet;
                    }

                    public String getTestTable() {
                        return testTable;
                    }

                    public void setTestTable(String testTable) {
                        this.testTable = testTable;
                    }

                    public String getCheckTimeF() {
                        return checkTimeF;
                    }

                    public void setCheckTimeF(String checkTimeF) {
                        this.checkTimeF = checkTimeF;
                    }

                    public String getSort() {
                        return sort;
                    }

                    public void setSort(String sort) {
                        this.sort = sort;
                    }

                    public String getCheckPersonF() {
                        return checkPersonF;
                    }

                    public void setCheckPersonF(String checkPersonF) {
                        this.checkPersonF = checkPersonF;
                    }

                    public String getConclusionF() {
                        return conclusionF;
                    }

                    public void setConclusionF(String conclusionF) {
                        this.conclusionF = conclusionF;
                    }

                    public String getCheckTime() {
                        return checkTime;
                    }

                    public void setCheckTime(String checkTime) {
                        this.checkTime = checkTime;
                    }

                    public String getUniqueValue() {
                        return uniqueValue;
                    }

                    public void setUniqueValue(String uniqueValue) {
                        this.uniqueValue = uniqueValue;
                    }

                    public DocumentListSetBean.DocumentBean.FileSetBean getFileSet() {
                        return FileSet;
                    }

                    public void setFileSet(DocumentListSetBean.DocumentBean.FileSetBean fileSet) {
                        FileSet = fileSet;
                    }

                    public AcceptDeviceSet getAcceptDeviceSet() {
                        return AcceptDeviceSet;
                    }

                    public void setAcceptDeviceSet(AcceptDeviceSet acceptDeviceSet) {
                        this.AcceptDeviceSet = acceptDeviceSet;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getGroupName() {
                        return groupName;
                    }

                    public void setGroupName(String groupName) {
                        this.groupName = groupName;
                    }

                    public String getCheckGroupConclusion() {
                        return checkGroupConclusion;
                    }

                    public void setCheckGroupConclusion(String checkGroupConclusion) {
                        this.checkGroupConclusion = checkGroupConclusion;
                    }

                    public String getCheckPerson() {
                        return checkPerson;
                    }

                    public void setCheckPerson(String checkPerson) {
                        this.checkPerson = checkPerson;
                    }

                    public String getIsConclusion() {
                        return isConclusion;
                    }

                    public void setIsConclusion(String isConclusion) {
                        this.isConclusion = isConclusion;
                    }

                    public String getIsTable() {
                        return isTable;
                    }

                    public void setIsTable(String isTable) {
                        this.isTable = isTable;
                    }

                    public PropertySetBean getPropertySet() {
                        return PropertySet;
                    }

                    public void setPropertySet(PropertySetBean PropertySet) {
                        this.PropertySet = PropertySet;
                    }

                    public CheckItemSetBean getCheckItemSet() {
                        return CheckItemSet;
                    }

                    public void setCheckItemSet(CheckItemSetBean CheckItemSet) {
                        this.CheckItemSet = CheckItemSet;
                    }

                    public static class TestTabSet{
                        @XStreamImplicit(itemFieldName = "TestTab")
                        private List<TestTab> TestTab;

                        public List<CheckGroupBean.TestTabSet.TestTab> getTestTab() {
                            return TestTab;
                        }

                        public void setTestTab(List<CheckGroupBean.TestTabSet.TestTab> testTab) {
                            TestTab = testTab;
                        }

                        public static class TestTab {
                            @XStreamAsAttribute()
                            @XStreamAlias("id") //属性注解
                            private String id;
                            private String name;
                            private String requiredVal;
                            private String testVal;
                            private String description;
                            private String uniqueValue;

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getRequiredVal() {
                                return requiredVal;
                            }

                            public void setRequiredVal(String requiredVal) {
                                this.requiredVal = requiredVal;
                            }

                            public String getTestVal() {
                                return testVal;
                            }

                            public void setTestVal(String testVal) {
                                this.testVal = testVal;
                            }

                            public String getDescription() {
                                return description;
                            }

                            public void setDescription(String description) {
                                this.description = description;
                            }

                            public String getUniqueValue() {
                                return uniqueValue;
                            }

                            public void setUniqueValue(String uniqueValue) {
                                this.uniqueValue = uniqueValue;
                            }
                        }

                    }


                    public static class CheckItemRelateSetBean {

                        @XStreamImplicit(itemFieldName = "CheckItemRelate")
                        private List<CheckItemRelateBean> CheckItemRelate;

                        public List<CheckItemRelateBean> getCheckItemRelate() {
                            return CheckItemRelate;
                        }

                        public void setCheckItemRelate(List<CheckItemRelateBean> checkItemRelate) {
                            CheckItemRelate = checkItemRelate;
                        }

                        public static class CheckItemRelateBean {
                            /**
                             * 检查项id
                             */
                            private String targetId;

                            /**
                             * 关联的检查项id
                             */
                            private String relateItemId;

                            /**
                             * 选项
                             */
                            private String selected;


                            public String getTargetId() {
                                return targetId;
                            }

                            public void setTargetId(String targetId) {
                                this.targetId = targetId;
                            }

                            public String getRelateItemId() {
                                return relateItemId;
                            }

                            public void setRelateItemId(String relateItemId) {
                                this.relateItemId = relateItemId;
                            }

                            public String getSelected() {
                                return selected;
                            }

                            public void setSelected(String selected) {
                                this.selected = selected;
                            }
                        }

                    }


                    public static class AcceptDeviceSet {
                        @XStreamImplicit(itemFieldName = "AcceptDevice")
                        private List<AcceptDevice> AcceptDevice;

                        public List<AcceptDevice> getAcceptDevice() {
                            return AcceptDevice;
                        }

                        public void setAcceptDevice(List<AcceptDevice> acceptDevice) {
                            AcceptDevice = acceptDevice;
                        }

                        public static class AcceptDevice {
                            @XStreamAsAttribute()
                            @XStreamAlias("id") //属性注解
                            private String id;
                            private String name;
                            private String specification;
                            private String accuracy;
                            private String certificate;
                            private String description;
                            private String uniqueValue;

                            public String getUniqueValue() {
                                return uniqueValue;
                            }

                            public void setUniqueValue(String uniqueValue) {
                                this.uniqueValue = uniqueValue;
                            }

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getSpecification() {
                                return specification;
                            }

                            public void setSpecification(String specification) {
                                this.specification = specification;
                            }

                            public String getAccuracy() {
                                return accuracy;
                            }

                            public void setAccuracy(String accuracy) {
                                this.accuracy = accuracy;
                            }

                            public String getCertificate() {
                                return certificate;
                            }

                            public void setCertificate(String certificate) {
                                this.certificate = certificate;
                            }

                            public String getDescription() {
                                return description;
                            }

                            public void setDescription(String description) {
                                this.description = description;
                            }
                        }

                    }

                    public static class PropertySetBean {
                        /**
                         * Property : {"name":"扩展属性一","value":"检查组扩展属性值"}
                         */
                        @XStreamImplicit(itemFieldName = "Property")
                        private List<PropertyBean> Property;

                        public List<PropertyBean> getProperty() {
                            return Property;
                        }

                        public void setProperty(List<PropertyBean> Property) {
                            this.Property = Property;
                        }

                        public static class PropertyBean {
                            /**
                             * name : 扩展属性一
                             * value : 检查组扩展属性值
                             */

                            private String name;
                            private String value;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getValue() {
                                return value;
                            }

                            public void setValue(String value) {
                                this.value = value;
                            }
                        }
                    }

                    public static class CheckItemSetBean {
                        /**
                         * CheckItem : {"-id":"361408582131216384","name":"测试检查xiang","options":"是,否,有","selected":"是","PropertySet":{"Property":{"name":"扩展属性一","value":"扩展值"}},"RelatedDocumentIdSet":{"RelatedDocumentId":"361439270231687168"}}
                         */
                        @XStreamImplicit(itemFieldName = "CheckItem")
                        private List<CheckItemBean> CheckItem;

                        public List<CheckItemBean> getCheckItem() {
                            return CheckItem;
                        }

                        public void setCheckItem(List<CheckItemBean> CheckItem) {
                            this.CheckItem = CheckItem;
                        }

                        public static class CheckItemBean {
                            /**
                             * -id : 361408582131216384
                             * name : 测试检查xiang
                             * options : 是,否,有
                             * selected : 是
                             * PropertySet : {"Property":{"name":"扩展属性一","value":"扩展值"}}
                             * RelatedDocumentIdSet : {"RelatedDocumentId":"361439270231687168"}
                             */

                            @XStreamAsAttribute()
                            @XStreamAlias("id") //属性注解
                            private String id;
                            private String name;
                            private String options;
                            private String selected;
                            private String uniqueValue;
                            private PropertySetBeanX PropertySet;
                            private RelatedDocumentIdSetBean RelatedDocumentIdSet;

                            private String sort;
                            private String description;
                            private String relate;

                            public String getRelate() {
                                return relate;
                            }

                            public void setRelate(String relate) {
                                this.relate = relate;
                            }

                            public String getDescription() {
                                return description;
                            }

                            public void setDescription(String description) {
                                this.description = description;
                            }

                            public String getSort() {
                                return sort;
                            }

                            public void setSort(String sort) {
                                this.sort = sort;
                            }

                            public String getUniqueValue() {
                                return uniqueValue;
                            }

                            public void setUniqueValue(String uniqueValue) {
                                this.uniqueValue = uniqueValue;
                            }

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getOptions() {
                                return options;
                            }

                            public void setOptions(String options) {
                                this.options = options;
                            }

                            public String getSelected() {
                                return selected;
                            }

                            public void setSelected(String selected) {
                                this.selected = selected;
                            }

                            public PropertySetBeanX getPropertySet() {
                                return PropertySet;
                            }

                            public void setPropertySet(PropertySetBeanX PropertySet) {
                                this.PropertySet = PropertySet;
                            }

                            public RelatedDocumentIdSetBean getRelatedDocumentIdSet() {
                                return RelatedDocumentIdSet;
                            }

                            public void setRelatedDocumentIdSet(RelatedDocumentIdSetBean RelatedDocumentIdSet) {
                                this.RelatedDocumentIdSet = RelatedDocumentIdSet;
                            }

                            public static class PropertySetBeanX {
                                /**
                                 * Property : {"name":"扩展属性一","value":"扩展值"}
                                 */

                                @XStreamImplicit(itemFieldName = "Property")
                                private List<PropertyBeanX> Property;

                                public List<PropertyBeanX> getProperty() {
                                    return Property;
                                }

                                public void setProperty(List<PropertyBeanX> Property) {
                                    this.Property = Property;
                                }

                                public static class PropertyBeanX {
                                    /**
                                     * name : 扩展属性一
                                     * value : 扩展值
                                     */

                                    private String name;
                                    private String value;

                                    public String getName() {
                                        return name;
                                    }

                                    public void setName(String name) {
                                        this.name = name;
                                    }

                                    public String getValue() {
                                        return value;
                                    }

                                    public void setValue(String value) {
                                        this.value = value;
                                    }
                                }
                            }

                            public static class RelatedDocumentIdSetBean {
                                /**
                                 * RelatedDocumentId : 361439270231687168
                                 */
                                @XStreamImplicit(itemFieldName = "RelatedDocumentId")
                                private List<String> RelatedDocumentId;

                                public List<String> getRelatedDocumentId() {
                                    return RelatedDocumentId;
                                }

                                public void setRelatedDocumentId(List<String> RelatedDocumentId) {
                                    this.RelatedDocumentId = RelatedDocumentId;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static class CheckVerdBean {
        /**
         * -id : 361408589152481280
         * name : Pad导出导入测试验收结论
         * code : padImport01-JL
         * qConclusion : 检查单结论
         */

        @XStreamAsAttribute()
        @XStreamAlias("id") //属性注解
        private String id;
        private String name;
        private String code;
        private String qConclusion;
        private String gConclusion;
        private String jConclusion;
        private String conclusion;
        private String checkPerson;
        private String docTypeVal;
        private String checkPersonId;
        private String checkDate;
        private String yConclusion;
        private DocumentListSetBean.DocumentBean.FileSetBean FileSet;

        public String getyConclusion() {
            return yConclusion;
        }

        public void setyConclusion(String yConclusion) {
            this.yConclusion = yConclusion;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getCheckPersonId() {
            return checkPersonId;
        }

        public void setCheckPersonId(String checkPersonId) {
            this.checkPersonId = checkPersonId;
        }

        public DocumentListSetBean.DocumentBean.FileSetBean getFileSet() {
            return FileSet;
        }

        public void setFileSet(DocumentListSetBean.DocumentBean.FileSetBean fileSet) {
            FileSet = fileSet;
        }

        public String getDocTypeVal() {
            return docTypeVal;
        }

        public void setDocTypeVal(String docTypeVal) {
            this.docTypeVal = docTypeVal;
        }

        public String getgConclusion() {
            return gConclusion;
        }

        public void setgConclusion(String gConclusion) {
            this.gConclusion = gConclusion;
        }

        public String getjConclusion() {
            return jConclusion;
        }

        public void setjConclusion(String jConclusion) {
            this.jConclusion = jConclusion;
        }

        public String getConclusion() {
            return conclusion;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        public String getCheckPerson() {
            return checkPerson;
        }

        public void setCheckPerson(String checkPerson) {
            this.checkPerson = checkPerson;
        }

        public String getqConclusion() {
            return qConclusion;
        }

        public void setqConclusion(String qConclusion) {
            this.qConclusion = qConclusion;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getQConclusion() {
            return qConclusion;
        }

        public void setQConclusion(String qConclusion) {
            this.qConclusion = qConclusion;
        }
    }

    public static class CheckUnresolvedBean {
        /**
         * -id : 361408589169258496
         * name : Pad导出导入测试验收遗留问题落实
         * code : padImport01-YL
         */

        @XStreamAsAttribute()
        @XStreamAlias("id") //属性注解
        private String id;
        private String name;
        private String code;
        private String docTypeVal;

        public String getDocTypeVal() {
            return docTypeVal;
        }

        public void setDocTypeVal(String docTypeVal) {
            this.docTypeVal = docTypeVal;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class UnresolvedSetBean {
        /**
         * Unresolved : {"-id":"361408814164307968","productCode":"cpbh","question":"测试遗留问题","confirmer":"张三","confirmTime":"2019-09-27","fileId":"null"}
         */
        @XStreamImplicit(itemFieldName = "Unresolved")
        private List<UnresolvedBean> Unresolved;

        public List<UnresolvedBean> getUnresolved() {
            return Unresolved;
        }

        public void setUnresolved(List<UnresolvedBean> Unresolved) {
            this.Unresolved = Unresolved;
        }

        public static class UnresolvedBean {
            /**
             * -id : 361408814164307968
             * productCode : cpbh
             * question : 测试遗留问题
             * confirmer : 张三
             * confirmTime : 2019-09-27
             * fileId : null
             */

            @XStreamAsAttribute()
            @XStreamAlias("id") //属性注解
            private String id;
            private String productCode;
            private String question;
            private String confirmer;
            private String confirmTime;
            private String fileId;
            private String uniqueValue;
            private DocumentListSetBean.DocumentBean.FileSetBean FileSet;

            private String description;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUniqueValue() {
                return uniqueValue;
            }

            public void setUniqueValue(String uniqueValue) {
                this.uniqueValue = uniqueValue;
            }

            public DocumentListSetBean.DocumentBean.FileSetBean getFileSet() {
                return FileSet;
            }

            public void setFileSet(DocumentListSetBean.DocumentBean.FileSetBean fileSet) {
                FileSet = fileSet;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getConfirmer() {
                return confirmer;
            }

            public void setConfirmer(String confirmer) {
                this.confirmer = confirmer;
            }

            public String getConfirmTime() {
                return confirmTime;
            }

            public void setConfirmTime(String confirmTime) {
                this.confirmTime = confirmTime;
            }

            public String getFileId() {
                return fileId;
            }

            public void setFileId(String fileId) {
                this.fileId = fileId;
            }
        }
    }

    public static class DeliveryListsBean {
        @XStreamImplicit(itemFieldName = "DeliveryList")
        private List<DeliveryListBean> DeliveryList;

        public List<DeliveryListBean> getDeliveryList() {
            return DeliveryList;
        }

        public void setDeliveryList(List<DeliveryListBean> DeliveryList) {
            this.DeliveryList = DeliveryList;
        }

        public static class DeliveryListBean {
            /**
             * -id : 361426058218889216
             * isParent : true
             * project : 依据文件检查
             * parentId : null
             */

            @XStreamAsAttribute()
            @XStreamAlias("id") //属性注解
            private String id;
            private String isParent;
            private String project;
            private String parentId;
            private String uniqueValue;
            private String typeDisplay;
            private String sortBy;

            private String sort;
            private CheckFileSetBean.CheckFileBean.CheckGroupSetBean.CheckGroupBean.CheckItemSetBean.CheckItemBean.RelatedDocumentIdSetBean RelatedDocumentIdSet;

            public CheckFileSetBean.CheckFileBean.CheckGroupSetBean.CheckGroupBean.CheckItemSetBean.CheckItemBean.RelatedDocumentIdSetBean getRelatedDocumentIdSet() {
                return RelatedDocumentIdSet;
            }

            public void setRelatedDocumentIdSet(CheckFileSetBean.CheckFileBean.CheckGroupSetBean.CheckGroupBean.CheckItemSetBean.CheckItemBean.RelatedDocumentIdSetBean relatedDocumentIdSet) {
                RelatedDocumentIdSet = relatedDocumentIdSet;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getTypeDisplay() {
                return typeDisplay;
            }

            public void setTypeDisplay(String typeDisplay) {
                this.typeDisplay = typeDisplay;
            }

            public String getSortBy() {
                return sortBy;
            }

            public void setSortBy(String sortBy) {
                this.sortBy = sortBy;
            }

            public String getUniqueValue() {
                return uniqueValue;
            }

            public void setUniqueValue(String uniqueValue) {
                this.uniqueValue = uniqueValue;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsParent() {
                return isParent;
            }

            public void setIsParent(String isParent) {
                this.isParent = isParent;
            }

            public String getProject() {
                return project;
            }

            public void setProject(String project) {
                this.project = project;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }

    public static class DocumentListSetBean {
        @XStreamImplicit(itemFieldName = "Document")
        private List<DocumentBean> Document;

        public List<DocumentBean> getDocument() {
            return Document;
        }

        public void setDocument(List<DocumentBean> Document) {
            this.Document = Document;
        }

        public static class DocumentBean {
            /**
             * -id : 361438853548556288
             * code : 管理文件编号
             * name : 管理文件名称
             * secret : 非密
             * payClassify : 361426061335257088
             * modalCode : 型号代号
             * productCodeName : 产品编号
             * productCode : 产品编号
             * stage : 初样阶段(C)
             * techStatus : 技术状态
             * approver : 批准人
             * approvalDate : 2019-09-25
             * issl : true
             * conclusion : 文件验收结论
             * description : 文件备注
             * FileSet : {"File":{"name":"工单.xlsx","path":"5d8b2ea15eefa31a2cdad120.xlsx","type":"主内容"}}
             */

            @XStreamAsAttribute()
            @XStreamAlias("id") //属性注解
            private String id;
            private String code;
            private String name;
            private String secret;
            private String payClassify;
            private String payClassifyName;
            private String modelCode;
            private String productCodeName;
            private String productCode;
            private String stage;
            private String techStatus;
            private String approver;
            private String approvalDate;
            private String issl;
            private String conclusion;
            private String description;
            private String onLine;
            private String infoUrl;
            private String uniqueValue;
            private FileSetBean FileSet;

            public String getUniqueValue() {
                return uniqueValue;
            }

            public void setUniqueValue(String uniqueValue) {
                this.uniqueValue = uniqueValue;
            }

            public String getInfoUrl() {
                return infoUrl;
            }

            public void setInfoUrl(String infoUrl) {
                this.infoUrl = infoUrl;
            }

            public String getOnLine() {
                return onLine;
            }

            public void setOnLine(String onLine) {
                this.onLine = onLine;
            }

            public String getModelCode() {
                return modelCode;
            }

            public void setModelCode(String modelCode) {
                this.modelCode = modelCode;
            }

            public String getPayClassifyName() {
                return payClassifyName;
            }

            public void setPayClassifyName(String payClassifyName) {
                this.payClassifyName = payClassifyName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public String getPayClassify() {
                return payClassify;
            }

            public void setPayClassify(String payClassify) {
                this.payClassify = payClassify;
            }

            public String getModalCode() {
                return modelCode;
            }

            public void setModalCode(String modalCode) {
                this.modelCode = modalCode;
            }

            public String getProductCodeName() {
                return productCodeName;
            }

            public void setProductCodeName(String productCodeName) {
                this.productCodeName = productCodeName;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getStage() {
                return stage;
            }

            public void setStage(String stage) {
                this.stage = stage;
            }

            public String getTechStatus() {
                return techStatus;
            }

            public void setTechStatus(String techStatus) {
                this.techStatus = techStatus;
            }

            public String getApprover() {
                return approver;
            }

            public void setApprover(String approver) {
                this.approver = approver;
            }

            public String getApprovalDate() {
                return approvalDate;
            }

            public void setApprovalDate(String approvalDate) {
                this.approvalDate = approvalDate;
            }

            public String getIssl() {
                return issl;
            }

            public void setIssl(String issl) {
                this.issl = issl;
            }

            public String getConclusion() {
                return conclusion;
            }

            public void setConclusion(String conclusion) {
                this.conclusion = conclusion;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public FileSetBean getFileSet() {
                return FileSet;
            }

            public void setFileSet(FileSetBean FileSet) {
                this.FileSet = FileSet;
            }

            public static class FileSetBean {
                /**
                 * File : {"name":"工单.xlsx","path":"5d8b2ea15eefa31a2cdad120.xlsx","type":"主内容"}
                 */


                @XStreamImplicit(itemFieldName = "File")
                private List<FileBean> File;

                public List<FileBean> getFile() {
                    return File;
                }

                public void setFile(List<FileBean> File) {
                    this.File = File;
                }

                public static class FileBean {
                    /**
                     * name : 工单.xlsx
                     * path : 5d8b2ea15eefa31a2cdad120.xlsx
                     * type : 主内容
                     */

                    private String name;
                    private String path;
                    private String type;
                    private String secret;
                    private String disabledSecret;

                    public String getDisabledSecret() {
                        return disabledSecret;
                    }

                    public void setDisabledSecret(String disabledSecret) {
                        this.disabledSecret = disabledSecret;
                    }

                    public String getSecret() {
                        return secret;
                    }

                    public void setSecret(String secret) {
                        this.secret = secret;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }
        }
    }

}
