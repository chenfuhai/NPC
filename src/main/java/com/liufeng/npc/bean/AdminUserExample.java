package com.liufeng.npc.bean;

import java.util.ArrayList;
import java.util.List;

public class AdminUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAdIdIsNull() {
            addCriterion("Ad_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdIdIsNotNull() {
            addCriterion("Ad_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdIdEqualTo(Integer value) {
            addCriterion("Ad_ID =", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotEqualTo(Integer value) {
            addCriterion("Ad_ID <>", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThan(Integer value) {
            addCriterion("Ad_ID >", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ad_ID >=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThan(Integer value) {
            addCriterion("Ad_ID <", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThanOrEqualTo(Integer value) {
            addCriterion("Ad_ID <=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdIn(List<Integer> values) {
            addCriterion("Ad_ID in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotIn(List<Integer> values) {
            addCriterion("Ad_ID not in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdBetween(Integer value1, Integer value2) {
            addCriterion("Ad_ID between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Ad_ID not between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andAdNameIsNull() {
            addCriterion("Ad_Name is null");
            return (Criteria) this;
        }

        public Criteria andAdNameIsNotNull() {
            addCriterion("Ad_Name is not null");
            return (Criteria) this;
        }

        public Criteria andAdNameEqualTo(String value) {
            addCriterion("Ad_Name =", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameNotEqualTo(String value) {
            addCriterion("Ad_Name <>", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameGreaterThan(String value) {
            addCriterion("Ad_Name >", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameGreaterThanOrEqualTo(String value) {
            addCriterion("Ad_Name >=", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameLessThan(String value) {
            addCriterion("Ad_Name <", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameLessThanOrEqualTo(String value) {
            addCriterion("Ad_Name <=", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameLike(String value) {
            addCriterion("Ad_Name like", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameNotLike(String value) {
            addCriterion("Ad_Name not like", value, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameIn(List<String> values) {
            addCriterion("Ad_Name in", values, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameNotIn(List<String> values) {
            addCriterion("Ad_Name not in", values, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameBetween(String value1, String value2) {
            addCriterion("Ad_Name between", value1, value2, "adName");
            return (Criteria) this;
        }

        public Criteria andAdNameNotBetween(String value1, String value2) {
            addCriterion("Ad_Name not between", value1, value2, "adName");
            return (Criteria) this;
        }

        public Criteria andAdPwdIsNull() {
            addCriterion("Ad_Pwd is null");
            return (Criteria) this;
        }

        public Criteria andAdPwdIsNotNull() {
            addCriterion("Ad_Pwd is not null");
            return (Criteria) this;
        }

        public Criteria andAdPwdEqualTo(String value) {
            addCriterion("Ad_Pwd =", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdNotEqualTo(String value) {
            addCriterion("Ad_Pwd <>", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdGreaterThan(String value) {
            addCriterion("Ad_Pwd >", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdGreaterThanOrEqualTo(String value) {
            addCriterion("Ad_Pwd >=", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdLessThan(String value) {
            addCriterion("Ad_Pwd <", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdLessThanOrEqualTo(String value) {
            addCriterion("Ad_Pwd <=", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdLike(String value) {
            addCriterion("Ad_Pwd like", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdNotLike(String value) {
            addCriterion("Ad_Pwd not like", value, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdIn(List<String> values) {
            addCriterion("Ad_Pwd in", values, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdNotIn(List<String> values) {
            addCriterion("Ad_Pwd not in", values, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdBetween(String value1, String value2) {
            addCriterion("Ad_Pwd between", value1, value2, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdPwdNotBetween(String value1, String value2) {
            addCriterion("Ad_Pwd not between", value1, value2, "adPwd");
            return (Criteria) this;
        }

        public Criteria andAdInfoIsNull() {
            addCriterion("Ad_Info is null");
            return (Criteria) this;
        }

        public Criteria andAdInfoIsNotNull() {
            addCriterion("Ad_Info is not null");
            return (Criteria) this;
        }

        public Criteria andAdInfoEqualTo(String value) {
            addCriterion("Ad_Info =", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoNotEqualTo(String value) {
            addCriterion("Ad_Info <>", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoGreaterThan(String value) {
            addCriterion("Ad_Info >", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoGreaterThanOrEqualTo(String value) {
            addCriterion("Ad_Info >=", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoLessThan(String value) {
            addCriterion("Ad_Info <", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoLessThanOrEqualTo(String value) {
            addCriterion("Ad_Info <=", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoLike(String value) {
            addCriterion("Ad_Info like", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoNotLike(String value) {
            addCriterion("Ad_Info not like", value, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoIn(List<String> values) {
            addCriterion("Ad_Info in", values, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoNotIn(List<String> values) {
            addCriterion("Ad_Info not in", values, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoBetween(String value1, String value2) {
            addCriterion("Ad_Info between", value1, value2, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdInfoNotBetween(String value1, String value2) {
            addCriterion("Ad_Info not between", value1, value2, "adInfo");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeIsNull() {
            addCriterion("Ad_PowerCode is null");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeIsNotNull() {
            addCriterion("Ad_PowerCode is not null");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeEqualTo(Integer value) {
            addCriterion("Ad_PowerCode =", value, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeNotEqualTo(Integer value) {
            addCriterion("Ad_PowerCode <>", value, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeGreaterThan(Integer value) {
            addCriterion("Ad_PowerCode >", value, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ad_PowerCode >=", value, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeLessThan(Integer value) {
            addCriterion("Ad_PowerCode <", value, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeLessThanOrEqualTo(Integer value) {
            addCriterion("Ad_PowerCode <=", value, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeIn(List<Integer> values) {
            addCriterion("Ad_PowerCode in", values, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeNotIn(List<Integer> values) {
            addCriterion("Ad_PowerCode not in", values, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeBetween(Integer value1, Integer value2) {
            addCriterion("Ad_PowerCode between", value1, value2, "adPowercode");
            return (Criteria) this;
        }

        public Criteria andAdPowercodeNotBetween(Integer value1, Integer value2) {
            addCriterion("Ad_PowerCode not between", value1, value2, "adPowercode");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}