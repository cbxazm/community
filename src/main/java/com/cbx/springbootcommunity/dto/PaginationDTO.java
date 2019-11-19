package com.cbx.springbootcommunity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDto> questionDtos;
      private boolean showPrevious;
      private boolean showFirstPage;
      private boolean showNext;
      private boolean showEndPage;
      //当前页
      private Integer page;
      private List<Integer> pages=new ArrayList<>();//显示的页码
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        this.page=page;
        pages.add(page);
        for (int i=1;i<=3;i++){
         if(page-i>0){
                     //往头部加 会自动追加，把后来的数放在前面
             pages.add(0,page-i);
         }
         if(page+i<=totalPage){
             //往尾部加
                   pages.add(page+i);
         }
        }
        //是否展示上一页
        if (page==1){
             showPrevious=false;
        }else {
            showPrevious=true;
        }
        //是否展示下一页
        if(page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }
         //是否展示首页
        if(pages!=null){
                 if(!pages.contains(1)){
                showFirstPage=true;
                 }else {
                     showFirstPage=false;
                 }
                 if(!pages.contains(totalPage)){
                           showEndPage=true;
                 }else {
                     showEndPage=false;
                 }

        }
    }

}
