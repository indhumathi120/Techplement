package org.example.repository;


import org.example.model.Question;
import org.example.model.Quiz;
import org.example.model.User;

import java.util.List;

public interface QuizRepository {
    String saveUser(User user);

    User getUser(String email);

    Quiz createQuiz(Quiz quiz);

    String deleteQuiz(Integer Id);

    Quiz updateQuiz(Quiz quiz);

    void viewQuiz();

    Quiz getQuiz(int Id);

    Question createQuestion(Question question);

    List<Quiz> getAllQuiz();
}
