package com.cbx.springbootcommunity.domain;

import lombok.Data;

@Data
public class Question {
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


}
