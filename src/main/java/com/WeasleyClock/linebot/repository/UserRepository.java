package com.weasleyclock.linebot.repository;

import com.weasleyclock.linebot.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    public int insert(String name);
    public UserEntity selectByName(String name);

}