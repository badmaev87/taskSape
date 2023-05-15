package com.example.ClientTaskSape.Entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    public Client(String name, String age, String groupId, String phone, String date) {
        this.name = name;
        this.age = age;
        this.groupId = groupId;
        this.phone = phone;
        this.date = date;
    }

    public Client() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
            return date;
        } catch (ParseException ex) {
            return null;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    private long id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "age")
    private String age;
    @CsvBindByName(column = "group_id")
    private String groupId;
    @CsvBindByName(column = "phone")
    private String phone;
    @CsvBindByName(column="date")
   // @CsvDate(value="yyyy-MM-dd")
    private String date;
}
