package com.codewinter.backgroundmanagement.service;

import com.codewinter.backgroundmanagement.entity.Question;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface QuestionService {
   List<Question> findQuestionsByPaperId(Long paperId);
}
