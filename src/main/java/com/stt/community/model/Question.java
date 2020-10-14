package com.stt.community.model;


import lombok.Data;

/**
 * @create 2020-10-14 9:30
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;


}
