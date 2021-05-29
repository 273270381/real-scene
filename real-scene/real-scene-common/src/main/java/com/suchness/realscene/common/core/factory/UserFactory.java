package com.suchness.realscene.common.core.factory;

import com.suchness.realscene.common.bean.dto.UserDto;
import com.suchness.realscene.common.entity.system.User;
import org.springframework.beans.BeanUtils;

/**
 * @author: rs
 * @date: 2020/6/20 9:03
 * @description:
 * 用户创建工厂
 */
public class UserFactory {

    public static User createUser(UserDto userDto, User user){
        if(userDto == null){
            return null;
        }else{
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
    public static User updateUser(UserDto userDto,User user){
        if(userDto == null){
            return null;
        }else{
            user.setName(userDto.getName());
            user.setDeptid(userDto.getDeptid());
            user.setSex(userDto.getSex());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setBirthday(userDto.getBirthday());
            user.setAddress(userDto.getAddress());
            if(userDto.getStatus()!=null){
                user.setStatus(userDto.getStatus());
            }
            return user;
        }
    }
}
