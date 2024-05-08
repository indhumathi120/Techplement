package org.example.repository.impl;


import org.example.model.Question;
import org.example.model.Quiz;
import org.example.model.User;
import org.example.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuizRepositoryImpl implements QuizRepository {
    private final Map<String, User> userCredentials = new ConcurrentHashMap<>();
    private final Map<Integer, Quiz> quizMap = new ConcurrentHashMap<>();
    private final Map<Integer, Question> questionMap = new ConcurrentHashMap<>();

    @Override
    public String saveUser(User user) {
        userCredentials.put(user.getEmail(), user);
        return "SignedUp succesfully";
    }


    @Override
    public User getUser(String email) {
        return userCredentials.get(email);
    }


    @Override
    public Quiz createQuiz(Quiz quiz) {
        quizMap.put(quiz.getId(), quiz);

        System.out.println("Quiz Created Successfully");
        return quiz;
    }

    @Override
    public String deleteQuiz(Integer Id) {
        if (quizMap.get(Id) != null) {
            quizMap.remove(Id);
            return "Removed Quiz";
        } else {
            return "Quiz not found";
        }
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        quizMap.put(quiz.getId(), quiz);
        System.out.println("Quiz Updated Successfully");
        return quiz;
    }

    @Override
    public void viewQuiz() {
        if (quizMap.isEmpty()) {
            System.out.println("No Quizes Found");
            return;
        }
        System.out.println("These are the available Quizes");
        quizMap.forEach((key, value) -> {
            System.out.println(value.toString());
        });
    }

    @Override
    public Quiz getQuiz(int Id) {
        if (!quizMap.isEmpty()) {
            return quizMap.get(Id);
        } else {
            throw new RuntimeException("Id " + Id + "not found");
        }
    }

    @Override
    public Question createQuestion(Question question) {
        questionMap.put(question.getId(), question);
        return question;
    }

    @Override
    public List<Quiz> getAllQuiz() {
        List<Quiz> quizzes = new ArrayList<>();
        quizMap.forEach((k, v) -> {
            quizzes.add(v);
        });
        if (!quizzes.isEmpty()) {
            return quizzes;
        }
        System.out.println("o Quizzes found");
        return null;
    }
}
