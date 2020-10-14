package com.stt.community.model;


import lombok.Data;

/**
 * @create 2020-10-13 11:29
 */
@Data
public class User {
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;


}
