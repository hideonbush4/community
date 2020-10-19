package com.stt.community.dto;

import com.stt.community.model.User;
import lombok.Data;

/**
 * @create 2020-10-15 15:45
 */
@Data
public class QuestionDTO {
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
    private User user;
}
