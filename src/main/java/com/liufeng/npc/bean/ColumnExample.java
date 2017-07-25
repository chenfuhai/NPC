package com.liufeng.npc.bean;

import java.util.ArrayList;
import java.util.List;

public class ColumnExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ColumnExample() {
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

        public Criteria andCoIdIsNull() {
            addCriterion("Co_ID is null");
            return (Criteria) this;
        }

        public Criteria andCoIdIsNotNull() {
            addCriterion("Co_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCoIdEqualTo(Integer value) {
            addCriterion("Co_ID =", value, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdNotEqualTo(Integer value) {
            addCriterion("Co_ID <>", value, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdGreaterThan(Integer value) {
            addCriterion("Co_ID >", value, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Co_ID >=", value, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdLessThan(Integer value) {
            addCriterion("Co_ID <", value, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdLessThanOrEqualTo(Integer value) {
            addCriterion("Co_ID <=", value, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdIn(List<Integer> values) {
            addCriterion("Co_ID in", values, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdNotIn(List<Integer> values) {
            addCriterion("Co_ID not in", values, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdBetween(Integer value1, Integer value2) {
            addCriterion("Co_ID between", value1, value2, "coId");
            return (Criteria) this;
        }

        public Criteria andCoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Co_ID not between", value1, value2, "coId");
            return (Criteria) this;
        }

        public Criteria andCoNameIsNull() {
            addCriterion("Co_Name is null");
            return (Criteria) this;
        }

        public Criteria andCoNameIsNotNull() {
            addCriterion("Co_Name is not null");
            return (Criteria) this;
        }

        public Criteria andCoNameEqualTo(String value) {
            addCriterion("Co_Name =", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameNotEqualTo(String value) {
            addCriterion("Co_Name <>", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameGreaterThan(String value) {
            addCriterion("Co_Name >", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameGreaterThanOrEqualTo(String value) {
            addCriterion("Co_Name >=", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameLessThan(String value) {
            addCriterion("Co_Name <", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameLessThanOrEqualTo(String value) {
            addCriterion("Co_Name <=", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameLike(String value) {
            addCriterion("Co_Name like", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameNotLike(String value) {
            addCriterion("Co_Name not like", value, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameIn(List<String> values) {
            addCriterion("Co_Name in", values, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameNotIn(List<String> values) {
            addCriterion("Co_Name not in", values, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameBetween(String value1, String value2) {
            addCriterion("Co_Name between", value1, value2, "coName");
            return (Criteria) this;
        }

        public Criteria andCoNameNotBetween(String value1, String value2) {
            addCriterion("Co_Name not between", value1, value2, "coName");
            return (Criteria) this;
        }

        public Criteria andCoInfoIsNull() {
            addCriterion("Co_Info is null");
            return (Criteria) this;
        }

        public Criteria andCoInfoIsNotNull() {
            addCriterion("Co_Info is not null");
            return (Criteria) this;
        }

        public Criteria andCoInfoEqualTo(String value) {
            addCriterion("Co_Info =", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoNotEqualTo(String value) {
            addCriterion("Co_Info <>", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoGreaterThan(String value) {
            addCriterion("Co_Info >", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoGreaterThanOrEqualTo(String value) {
            addCriterion("Co_Info >=", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoLessThan(String value) {
            addCriterion("Co_Info <", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoLessThanOrEqualTo(String value) {
            addCriterion("Co_Info <=", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoLike(String value) {
            addCriterion("Co_Info like", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoNotLike(String value) {
            addCriterion("Co_Info not like", value, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoIn(List<String> values) {
            addCriterion("Co_Info in", values, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoNotIn(List<String> values) {
            addCriterion("Co_Info not in", values, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoBetween(String value1, String value2) {
            addCriterion("Co_Info between", value1, value2, "coInfo");
            return (Criteria) this;
        }

        public Criteria andCoInfoNotBetween(String value1, String value2) {
            addCriterion("Co_Info not between", value1, value2, "coInfo");
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