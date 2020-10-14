package com.stt.community.dto;


import lombok.Data;

/**
 * @create 2020-10-12 9:33
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
