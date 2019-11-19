package com.cbx.springbootcommunity.service;

import com.cbx.springbootcommunity.dto.PaginationDTO;

public interface QuestionService {
    PaginationDTO list(Integer page, Integer size);


//    List<Question> list(Integer page, Integer size);
}
