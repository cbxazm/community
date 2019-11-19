package com.cbx.springbootcommunity.dto;

import com.cbx.springbootcommunity.domain.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer ViewCount;
    private Integer likeCount;
    private String tag;
    private Integer creatorId;
    private User user;
}
