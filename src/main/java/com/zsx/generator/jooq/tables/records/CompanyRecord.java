/*
 * This file is generated by jOOQ.
 */
package com.zsx.generator.jooq.tables.records;


import com.zsx.generator.jooq.tables.Company;

import java.sql.Date;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CompanyRecord extends UpdatableRecordImpl<CompanyRecord> implements Record6<Integer, String, Integer, String, Float, Date> {

    private static final long serialVersionUID = 1422174313;

    /**
     * Setter for <code>myschema.company.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>myschema.company.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>myschema.company.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>myschema.company.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>myschema.company.age</code>.
     */
    public void setAge(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>myschema.company.age</code>.
     */
    public Integer getAge() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>myschema.company.address</code>.
     */
    public void setAddress(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>myschema.company.address</code>.
     */
    public String getAddress() {
        return (String) get(3);
    }

    /**
     * Setter for <code>myschema.company.salary</code>.
     */
    public void setSalary(Float value) {
        set(4, value);
    }

    /**
     * Getter for <code>myschema.company.salary</code>.
     */
    public Float getSalary() {
        return (Float) get(4);
    }

    /**
     * Setter for <code>myschema.company.join_date</code>.
     */
    public void setJoinDate(Date value) {
        set(5, value);
    }

    /**
     * Getter for <code>myschema.company.join_date</code>.
     */
    public Date getJoinDate() {
        return (Date) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Integer, String, Float, Date> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, Integer, String, Float, Date> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Company.COMPANY.ID;
    }

    @Override
    public Field<String> field2() {
        return Company.COMPANY.NAME;
    }

    @Override
    public Field<Integer> field3() {
        return Company.COMPANY.AGE;
    }

    @Override
    public Field<String> field4() {
        return Company.COMPANY.ADDRESS;
    }

    @Override
    public Field<Float> field5() {
        return Company.COMPANY.SALARY;
    }

    @Override
    public Field<Date> field6() {
        return Company.COMPANY.JOIN_DATE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer component3() {
        return getAge();
    }

    @Override
    public String component4() {
        return getAddress();
    }

    @Override
    public Float component5() {
        return getSalary();
    }

    @Override
    public Date component6() {
        return getJoinDate();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Integer value3() {
        return getAge();
    }

    @Override
    public String value4() {
        return getAddress();
    }

    @Override
    public Float value5() {
        return getSalary();
    }

    @Override
    public Date value6() {
        return getJoinDate();
    }

    @Override
    public CompanyRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public CompanyRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public CompanyRecord value3(Integer value) {
        setAge(value);
        return this;
    }

    @Override
    public CompanyRecord value4(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public CompanyRecord value5(Float value) {
        setSalary(value);
        return this;
    }

    @Override
    public CompanyRecord value6(Date value) {
        setJoinDate(value);
        return this;
    }

    @Override
    public CompanyRecord values(Integer value1, String value2, Integer value3, String value4, Float value5, Date value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CompanyRecord
     */
    public CompanyRecord() {
        super(Company.COMPANY);
    }

    /**
     * Create a detached, initialised CompanyRecord
     */
    public CompanyRecord(Integer id, String name, Integer age, String address, Float salary, Date joinDate) {
        super(Company.COMPANY);

        set(0, id);
        set(1, name);
        set(2, age);
        set(3, address);
        set(4, salary);
        set(5, joinDate);
    }
}
