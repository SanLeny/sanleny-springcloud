package cn.sanleny.study.springcloud.clien.entity;

import java.io.Serializable;

public class Teacher implements Serializable {

    private String  name;
    private int age;
    private String passWord;
    private String header;

    public Teacher() {
    }

    public Teacher(String name, int age, String passWord, String header) {
        this.name = name;
        this.age = age;
        this.passWord = passWord;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
