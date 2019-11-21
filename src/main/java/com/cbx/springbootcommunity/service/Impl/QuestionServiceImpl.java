package com.cbx.springbootcommunity.service.Impl;

import com.cbx.springbootcommunity.domain.Question;
import com.cbx.springbootcommunity.domain.User;
import com.cbx.springbootcommunity.dto.PaginationDTO;
import com.cbx.springbootcommunity.dto.QuestionDto;
import com.cbx.springbootcommunity.mapper.QuestionMapper;
import com.cbx.springbootcommunity.mapper.UserMapper;
import com.cbx.springbootcommunity.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
       private UserMapper userMapper;
    @Override
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        //size*(page-1)
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for (Question question:questions){
           User user=userMapper.findById(question.getCreatorId());
            QuestionDto questionDto = new QuestionDto();
            //将对象传入该对象 question-->questionDto
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDTO.setQuestionDtos(questionDtoList);
        return paginationDTO;
    }

    @Override
    public QuestionDto getById(Integer id) {
        Question question=questionMapper.getById(id);
        User user=userMapper.findById(question.getCreatorId());
        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
         questionDto.setUser(user);
        return questionDto;
    }




    //    @Override
//    public PaginationDTO list(Integer userId, Integer page, Integer size) {
//        PaginationDTO paginationDTO = new PaginationDTO();
//        Integer totalCount = questionMapper.countByUserId(userId);
//        paginationDTO.setPagination(totalCount,page,size);
//        if(page<1){
//            page=1;
//        }
//        if(page>paginationDTO.getTotalPage()){
//            page=paginationDTO.getTotalPage();
//        }
//        //size*(page-1)
//        Integer offset=size*(page-1);
//        List<Question> questions = questionMapper.l;
//        List<QuestionDto> questionDtoList=new ArrayList<>();
//        for (Question question:questions){
//            User user=userMapper.findById(question.getCreatorId());
//            QuestionDto questionDto = new QuestionDto();
//            //将对象传入该对象 question-->questionDto
//            BeanUtils.copyProperties(question,questionDto);
//            questionDto.setUser(user);
//            questionDtoList.add(questionDto);
//        }
//        paginationDTO.setQuestionDtos(questionDtoList);
//        return paginationDTO;
//    }



}
