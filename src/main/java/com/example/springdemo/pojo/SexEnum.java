package com.example.springdemo.pojo;

/**
 * @author:zhaoxuliang.
 * @date: 2019/4/15.
 * @time: 18:23.
 */
public enum SexEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getSexById(int id){
        for(SexEnum sex:SexEnum.values()){
            if(sex.id==id){
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
