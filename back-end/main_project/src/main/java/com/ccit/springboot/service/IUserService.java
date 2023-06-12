package com.ccit.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccit.springboot.controller.dto.UserDTO;
import com.ccit.springboot.controller.dto.UserPasswordDTO;
import com.ccit.springboot.entity.Category;
import com.ccit.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> objectPage, String username, String email, String address);


}
