package com.cbx.springbootcommunity.dto;

import lombok.Data;

/**
 * dto: data transfer dao  数据传输模型
 */
@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String  redirect_uri;
    private String state;


}
