package com.cbx.springbootcommunity.service;

import com.cbx.springbootcommunity.domain.Question;
import com.cbx.springbootcommunity.dto.PaginationDTO;
import com.cbx.springbootcommunity.dto.QuestionDto;

public interface QuestionService {
    PaginationDTO list(Integer page, Integer size);

    QuestionDto getById(Integer id);

    void createOrUpdate(Question question);

//    PaginationDTO list(Integer userId, Integer page, Integer size);


//    List<Question> list(Integer page, Integer size);
}
