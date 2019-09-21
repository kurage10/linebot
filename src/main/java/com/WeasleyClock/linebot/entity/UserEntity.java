package com.weasleyclock.linebot.entity;
public class UserEntity{
    private String id;
    private String name;


    public UserEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId () {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}