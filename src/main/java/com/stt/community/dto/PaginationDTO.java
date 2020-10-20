package com.stt.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2020-10-16 14:48
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;//当前选中页
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    //totalCount总记录数
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //totalpage总页数
        Integer totalpage=1;
        if(totalCount==0){
            this.totalPage=1;
        }else {
            if (totalCount % size == 0) {
                totalpage = totalCount / size;
            } else {
                totalpage = totalCount / size + 1;
            }
        }

        this.totalPage=totalpage;

        this.page=page;

        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalpage){
                pages.add(page+i);
            }
        }

//        是否展示上一页
        if(page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
//        是否展示下一页
        if(page==totalpage){
            showNext=false;
        }else {
            showNext=true;
        }
//        是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
//        是否展示最后一页
        if(pages.contains(totalpage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
    }
}
