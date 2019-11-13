package com.zsx.demo;

import com.zsx.annotation.CompanyDeal;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @CompanyDeal("test1")
    private String id;
    @CompanyDeal("test1")
    private String name;
    @CompanyDeal
    private String email;

    @CompanyDeal("test2")
    public Integer number;
    @CompanyDeal("test2")
    public Date createDate;
    public Integer age;

    @CompanyDeal("test3")
    protected String depart;
    @CompanyDeal("test3")
    protected Date updateDate;
    protected Integer salary;

    @CompanyDeal("test4")
    String lastName;
    @CompanyDeal("test4")
    String firstName;
    String unit;

    public Customer() {
    }

    public Customer(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                ", createDate=" + createDate +
                ", age=" + age +
                ", depart='" + depart + '\'' +
                ", updateDate=" + updateDate +
                ", salary=" + salary +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
