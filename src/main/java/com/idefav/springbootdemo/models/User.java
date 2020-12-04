package com.idefav.springbootdemo.models;

import lombok.Data;

import java.io.Serializable;

/**
 * The type User.
 */
@Data
public class User implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;
}
