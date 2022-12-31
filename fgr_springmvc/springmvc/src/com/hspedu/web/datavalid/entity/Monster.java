package com.hspedu.web.datavalid.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Monster {
    private Integer id;

    @NotEmpty//不能为空
    private String name;

    @NotNull(message = "age不能为空")
    @Range(min = 0,max = 100)//接收的age值范围在0-100
    private Integer age;

    @NotEmpty
    @Email
    private String email;

    @NotNull(message = "birthday不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "salary不能为空")
    @NumberFormat(pattern = "###,###.##")
    private Float salary;


    public Monster() {
    }

    public Monster(Integer id, String name, Integer age, String email, Date birthday, Float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.birthday = birthday;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", salary='" + salary + '\'' +
                '}';
    }
}
