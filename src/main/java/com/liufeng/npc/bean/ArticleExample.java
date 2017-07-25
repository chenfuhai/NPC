package com.liufeng.npc.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
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

        public Criteria andArIdIsNull() {
            addCriterion("Ar_ID is null");
            return (Criteria) this;
        }

        public Criteria andArIdIsNotNull() {
            addCriterion("Ar_ID is not null");
            return (Criteria) this;
        }

        public Criteria andArIdEqualTo(Integer value) {
            addCriterion("Ar_ID =", value, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdNotEqualTo(Integer value) {
            addCriterion("Ar_ID <>", value, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdGreaterThan(Integer value) {
            addCriterion("Ar_ID >", value, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ar_ID >=", value, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdLessThan(Integer value) {
            addCriterion("Ar_ID <", value, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdLessThanOrEqualTo(Integer value) {
            addCriterion("Ar_ID <=", value, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdIn(List<Integer> values) {
            addCriterion("Ar_ID in", values, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdNotIn(List<Integer> values) {
            addCriterion("Ar_ID not in", values, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdBetween(Integer value1, Integer value2) {
            addCriterion("Ar_ID between", value1, value2, "arId");
            return (Criteria) this;
        }

        public Criteria andArIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Ar_ID not between", value1, value2, "arId");
            return (Criteria) this;
        }

        public Criteria andArTitleIsNull() {
            addCriterion("Ar_Title is null");
            return (Criteria) this;
        }

        public Criteria andArTitleIsNotNull() {
            addCriterion("Ar_Title is not null");
            return (Criteria) this;
        }

        public Criteria andArTitleEqualTo(String value) {
            addCriterion("Ar_Title =", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleNotEqualTo(String value) {
            addCriterion("Ar_Title <>", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleGreaterThan(String value) {
            addCriterion("Ar_Title >", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleGreaterThanOrEqualTo(String value) {
            addCriterion("Ar_Title >=", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleLessThan(String value) {
            addCriterion("Ar_Title <", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleLessThanOrEqualTo(String value) {
            addCriterion("Ar_Title <=", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleLike(String value) {
            addCriterion("Ar_Title like", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleNotLike(String value) {
            addCriterion("Ar_Title not like", value, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleIn(List<String> values) {
            addCriterion("Ar_Title in", values, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleNotIn(List<String> values) {
            addCriterion("Ar_Title not in", values, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleBetween(String value1, String value2) {
            addCriterion("Ar_Title between", value1, value2, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArTitleNotBetween(String value1, String value2) {
            addCriterion("Ar_Title not between", value1, value2, "arTitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleIsNull() {
            addCriterion("Ar_SubTitle is null");
            return (Criteria) this;
        }

        public Criteria andArSubtitleIsNotNull() {
            addCriterion("Ar_SubTitle is not null");
            return (Criteria) this;
        }

        public Criteria andArSubtitleEqualTo(String value) {
            addCriterion("Ar_SubTitle =", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleNotEqualTo(String value) {
            addCriterion("Ar_SubTitle <>", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleGreaterThan(String value) {
            addCriterion("Ar_SubTitle >", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleGreaterThanOrEqualTo(String value) {
            addCriterion("Ar_SubTitle >=", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleLessThan(String value) {
            addCriterion("Ar_SubTitle <", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleLessThanOrEqualTo(String value) {
            addCriterion("Ar_SubTitle <=", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleLike(String value) {
            addCriterion("Ar_SubTitle like", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleNotLike(String value) {
            addCriterion("Ar_SubTitle not like", value, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleIn(List<String> values) {
            addCriterion("Ar_SubTitle in", values, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleNotIn(List<String> values) {
            addCriterion("Ar_SubTitle not in", values, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleBetween(String value1, String value2) {
            addCriterion("Ar_SubTitle between", value1, value2, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArSubtitleNotBetween(String value1, String value2) {
            addCriterion("Ar_SubTitle not between", value1, value2, "arSubtitle");
            return (Criteria) this;
        }

        public Criteria andArPublictimeIsNull() {
            addCriterion("Ar_PublicTime is null");
            return (Criteria) this;
        }

        public Criteria andArPublictimeIsNotNull() {
            addCriterion("Ar_PublicTime is not null");
            return (Criteria) this;
        }

        public Criteria andArPublictimeEqualTo(Date value) {
            addCriterion("Ar_PublicTime =", value, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeNotEqualTo(Date value) {
            addCriterion("Ar_PublicTime <>", value, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeGreaterThan(Date value) {
            addCriterion("Ar_PublicTime >", value, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Ar_PublicTime >=", value, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeLessThan(Date value) {
            addCriterion("Ar_PublicTime <", value, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeLessThanOrEqualTo(Date value) {
            addCriterion("Ar_PublicTime <=", value, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeIn(List<Date> values) {
            addCriterion("Ar_PublicTime in", values, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeNotIn(List<Date> values) {
            addCriterion("Ar_PublicTime not in", values, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeBetween(Date value1, Date value2) {
            addCriterion("Ar_PublicTime between", value1, value2, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArPublictimeNotBetween(Date value1, Date value2) {
            addCriterion("Ar_PublicTime not between", value1, value2, "arPublictime");
            return (Criteria) this;
        }

        public Criteria andArIshotIsNull() {
            addCriterion("Ar_IsHot is null");
            return (Criteria) this;
        }

        public Criteria andArIshotIsNotNull() {
            addCriterion("Ar_IsHot is not null");
            return (Criteria) this;
        }

        public Criteria andArIshotEqualTo(String value) {
            addCriterion("Ar_IsHot =", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotNotEqualTo(String value) {
            addCriterion("Ar_IsHot <>", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotGreaterThan(String value) {
            addCriterion("Ar_IsHot >", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotGreaterThanOrEqualTo(String value) {
            addCriterion("Ar_IsHot >=", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotLessThan(String value) {
            addCriterion("Ar_IsHot <", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotLessThanOrEqualTo(String value) {
            addCriterion("Ar_IsHot <=", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotLike(String value) {
            addCriterion("Ar_IsHot like", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotNotLike(String value) {
            addCriterion("Ar_IsHot not like", value, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotIn(List<String> values) {
            addCriterion("Ar_IsHot in", values, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotNotIn(List<String> values) {
            addCriterion("Ar_IsHot not in", values, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotBetween(String value1, String value2) {
            addCriterion("Ar_IsHot between", value1, value2, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIshotNotBetween(String value1, String value2) {
            addCriterion("Ar_IsHot not between", value1, value2, "arIshot");
            return (Criteria) this;
        }

        public Criteria andArIsnewIsNull() {
            addCriterion("Ar_IsNew is null");
            return (Criteria) this;
        }

        public Criteria andArIsnewIsNotNull() {
            addCriterion("Ar_IsNew is not null");
            return (Criteria) this;
        }

        public Criteria andArIsnewEqualTo(String value) {
            addCriterion("Ar_IsNew =", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewNotEqualTo(String value) {
            addCriterion("Ar_IsNew <>", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewGreaterThan(String value) {
            addCriterion("Ar_IsNew >", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewGreaterThanOrEqualTo(String value) {
            addCriterion("Ar_IsNew >=", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewLessThan(String value) {
            addCriterion("Ar_IsNew <", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewLessThanOrEqualTo(String value) {
            addCriterion("Ar_IsNew <=", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewLike(String value) {
            addCriterion("Ar_IsNew like", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewNotLike(String value) {
            addCriterion("Ar_IsNew not like", value, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewIn(List<String> values) {
            addCriterion("Ar_IsNew in", values, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewNotIn(List<String> values) {
            addCriterion("Ar_IsNew not in", values, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewBetween(String value1, String value2) {
            addCriterion("Ar_IsNew between", value1, value2, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArIsnewNotBetween(String value1, String value2) {
            addCriterion("Ar_IsNew not between", value1, value2, "arIsnew");
            return (Criteria) this;
        }

        public Criteria andArFromIsNull() {
            addCriterion("Ar_From is null");
            return (Criteria) this;
        }

        public Criteria andArFromIsNotNull() {
            addCriterion("Ar_From is not null");
            return (Criteria) this;
        }

        public Criteria andArFromEqualTo(String value) {
            addCriterion("Ar_From =", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromNotEqualTo(String value) {
            addCriterion("Ar_From <>", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromGreaterThan(String value) {
            addCriterion("Ar_From >", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromGreaterThanOrEqualTo(String value) {
            addCriterion("Ar_From >=", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromLessThan(String value) {
            addCriterion("Ar_From <", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromLessThanOrEqualTo(String value) {
            addCriterion("Ar_From <=", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromLike(String value) {
            addCriterion("Ar_From like", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromNotLike(String value) {
            addCriterion("Ar_From not like", value, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromIn(List<String> values) {
            addCriterion("Ar_From in", values, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromNotIn(List<String> values) {
            addCriterion("Ar_From not in", values, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromBetween(String value1, String value2) {
            addCriterion("Ar_From between", value1, value2, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArFromNotBetween(String value1, String value2) {
            addCriterion("Ar_From not between", value1, value2, "arFrom");
            return (Criteria) this;
        }

        public Criteria andArClickaruntIsNull() {
            addCriterion("Ar_ClickArunt is null");
            return (Criteria) this;
        }

        public Criteria andArClickaruntIsNotNull() {
            addCriterion("Ar_ClickArunt is not null");
            return (Criteria) this;
        }

        public Criteria andArClickaruntEqualTo(Integer value) {
            addCriterion("Ar_ClickArunt =", value, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntNotEqualTo(Integer value) {
            addCriterion("Ar_ClickArunt <>", value, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntGreaterThan(Integer value) {
            addCriterion("Ar_ClickArunt >", value, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ar_ClickArunt >=", value, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntLessThan(Integer value) {
            addCriterion("Ar_ClickArunt <", value, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntLessThanOrEqualTo(Integer value) {
            addCriterion("Ar_ClickArunt <=", value, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntIn(List<Integer> values) {
            addCriterion("Ar_ClickArunt in", values, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntNotIn(List<Integer> values) {
            addCriterion("Ar_ClickArunt not in", values, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntBetween(Integer value1, Integer value2) {
            addCriterion("Ar_ClickArunt between", value1, value2, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArClickaruntNotBetween(Integer value1, Integer value2) {
            addCriterion("Ar_ClickArunt not between", value1, value2, "arClickarunt");
            return (Criteria) this;
        }

        public Criteria andArColumnidIsNull() {
            addCriterion("Ar_ColumnID is null");
            return (Criteria) this;
        }

        public Criteria andArColumnidIsNotNull() {
            addCriterion("Ar_ColumnID is not null");
            return (Criteria) this;
        }

        public Criteria andArColumnidEqualTo(Integer value) {
            addCriterion("Ar_ColumnID =", value, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidNotEqualTo(Integer value) {
            addCriterion("Ar_ColumnID <>", value, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidGreaterThan(Integer value) {
            addCriterion("Ar_ColumnID >", value, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ar_ColumnID >=", value, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidLessThan(Integer value) {
            addCriterion("Ar_ColumnID <", value, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidLessThanOrEqualTo(Integer value) {
            addCriterion("Ar_ColumnID <=", value, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidIn(List<Integer> values) {
            addCriterion("Ar_ColumnID in", values, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidNotIn(List<Integer> values) {
            addCriterion("Ar_ColumnID not in", values, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidBetween(Integer value1, Integer value2) {
            addCriterion("Ar_ColumnID between", value1, value2, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArColumnidNotBetween(Integer value1, Integer value2) {
            addCriterion("Ar_ColumnID not between", value1, value2, "arColumnid");
            return (Criteria) this;
        }

        public Criteria andArStatusIsNull() {
            addCriterion("Ar_Status is null");
            return (Criteria) this;
        }

        public Criteria andArStatusIsNotNull() {
            addCriterion("Ar_Status is not null");
            return (Criteria) this;
        }

        public Criteria andArStatusEqualTo(Integer value) {
            addCriterion("Ar_Status =", value, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusNotEqualTo(Integer value) {
            addCriterion("Ar_Status <>", value, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusGreaterThan(Integer value) {
            addCriterion("Ar_Status >", value, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ar_Status >=", value, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusLessThan(Integer value) {
            addCriterion("Ar_Status <", value, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusLessThanOrEqualTo(Integer value) {
            addCriterion("Ar_Status <=", value, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusIn(List<Integer> values) {
            addCriterion("Ar_Status in", values, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusNotIn(List<Integer> values) {
            addCriterion("Ar_Status not in", values, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusBetween(Integer value1, Integer value2) {
            addCriterion("Ar_Status between", value1, value2, "arStatus");
            return (Criteria) this;
        }

        public Criteria andArStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("Ar_Status not between", value1, value2, "arStatus");
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