package org.example.service.impl;


import org.example.model.Question;
import org.example.model.Quiz;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.QuizRepository;
import org.example.repository.impl.QuizRepositoryImpl;
import org.example.service.QuizService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.util.Utils.loadQuestion;

public class QuizServiceImpl implements QuizService {
    static int quizId = 0;
    private final Map<String, List<Question>> categoryQuestionMap = new ConcurrentHashMap<>();
    QuizRepository quizRepository = new QuizRepositoryImpl();

    public String signUp(Scanner scanner) {
        System.out.println("Sign Up");
        System.out.println("Enter your username:");
        scanner.nextLine();
        String userName = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher;
        String email;
        while (true) {
            System.out.println("Enter email:");
            email = scanner.nextLine();
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println("Ivalid email format.Please enter email that eds with@gmail.com.");
                continue;
            }
            System.out.println("Enter role:\n1.ADMIN\n2.USER");
            int role = scanner.nextInt();
            if (quizRepository.getUser(email) != null && quizRepository.getUser(email).getEmail().equals(email)) {
                return ("User with email" + userName + " already exists. Choose a different name");
            }
            User user = new User(userName, password, email, new ArrayList<>(), role == 1 ? Role.ADMIN : Role.USER);
            quizRepository.saveUser(user);
            return ("SignedUp Successfully");
        }
    }
    public String login(Scanner scanner) {
        System.out.println("Login\nEnter your email:");
        scanner.nextLine();
        String loginEmail = scanner.nextLine();
        System.out.println("Enter your password:");
        String loginPassword = scanner.nextLine();
        User user = quizRepository.getUser(loginEmail);
        if (user != null && user.validatePassword(loginPassword)) {
            System.out.println("Logged Successfully");

            while (true) {
                if (user.getRole().equals(Role.ADMIN)) {
                    System.out.println("Enter choice:");
                    System.out.println("1.CreateQuiz\n2.DeleteQuiz\n3.AttendQuiz\n4.ViewQuiz\n5.UpdateQuiz\n6.LogOut");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            Quiz quiz = new Quiz();
                            quiz.setId(++quizId);
                            System.out.println("Enter Quiz title");
                            String title = scanner.nextLine();
                            quiz.setTitle(title);
                            System.out.println("Enter Quiz Conductor Name");
                            String conductedName = scanner.nextLine();
                            quiz.setConductedBy(conductedName);
                            System.out.println("Enter Quiz Start time as (HH:MM:SS)");
                            String startTime = scanner.nextLine();
                            quiz.setStartTime(startTime);
                            System.out.println("Enter category");
                            String quizCategory = scanner.nextLine();
                            List<Question> questionList = loadQuestion();
                            List<Question> filteredList = questionList.stream().filter(k -> k.getCategory().equals(quizCategory)).toList();
                            quiz.setQuestions(filteredList);
                            System.out.println(quizRepository.createQuiz(quiz));
                            break;
                        case 2:
                            System.out.println("Enter quizId to delete:");
                            int quizId = scanner.nextInt();
                            System.out.println(quizRepository.deleteQuiz(quizId));
                            break;
                        case 3:
                            System.out.println("Available quizzes to attend:");
                            List<Quiz> allQuiz = quizRepository.getAllQuiz();        // print id and title
                            allQuiz.forEach(k -> {
                                System.out.println("Id is:" + k.getId() + " " + "Title: " + k.getTitle());
                            });
                            AtomicInteger marks = new AtomicInteger();
                            System.out.println("Enter Id to attend");
                            int Id = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                AtomicInteger index = new AtomicInteger(1);
                                quizRepository.getQuiz(Id).getQuestions().forEach(k -> {
                                    System.out.println("Question: " + index.get());
                                    System.out.println(k.getCategory());
                                    System.out.println(k.getQuestionDescription());
                                    System.out.println(k.getOptions());
                                    System.out.println("Enter answer:");
                                    String answer = scanner.nextLine();
                                    if (answer.equals(k.getRightAnswer())) {
                                        marks.addAndGet(k.getMark());
                                    }
                                    index.getAndIncrement();
                                });
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            System.out.println("Your total score is " + marks);
                            break;
                        case 4:
                            List<Quiz> viewAllQuiz = quizRepository.getAllQuiz();        // print id and title
                            viewAllQuiz.forEach(k -> {
                                System.out.println("Id is:" + k.getId() + " " + "Title: " + k.getTitle());
                            });
                            break;
                        case 5:
                            System.out.println("Enter the QuizId to Update");
                            int updateQuizId = scanner.nextInt();
                            scanner.nextLine();
                            Quiz updateQuiz = quizRepository.getQuiz(updateQuizId);
                            System.out.println("Enter Quiz title");
                            updateQuiz.setTitle(scanner.nextLine());
                            System.out.println("Enter Quiz Conductor Name");
                            updateQuiz.setConductedBy(scanner.nextLine());
                            System.out.println("Enter Quiz Start time as (HH:MM:SS)");
                            updateQuiz.setStartTime(scanner.nextLine());
                            System.out.println(quizRepository.updateQuiz(updateQuiz));
                            break;
                        case 6:
                            return "Logged out";
                    }
                } else {
                    System.out.println("1.ViewQuiz\n2.AttendQuiz");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            List<Quiz> viewAllQuiz = quizRepository.getAllQuiz();        // print id and title
                            viewAllQuiz.forEach(k -> {
                                System.out.println("Id is:" + k.getId() + " " + "Title: " + k.getTitle());
                            });
                            break;
                        case 2:
                            List<Quiz> allQuiz = quizRepository.getAllQuiz();        // print id and title
                            allQuiz.forEach(k -> {
                                System.out.println("Id is:" + k.getId() + " " + "Title: " + k.getTitle());
                            });
                            AtomicInteger marks = new AtomicInteger();
                            System.out.println("Enter Id to attend");
                            int Id = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                AtomicInteger index = new AtomicInteger(1);
                                quizRepository.getQuiz(Id).getQuestions().forEach(k -> {
                                    System.out.println("Question" + index.get());
                                    System.out.println(k.getCategory());
                                    System.out.println(k.getQuestionDescription());
                                    System.out.println(k.getOptions());
                                    System.out.println("Enter answer");
                                    String answer = scanner.nextLine();
                                    if (answer.equals(k.getRightAnswer())) {
                                        marks.addAndGet(k.getMark());
                                    }
                                    index.getAndIncrement();
                                });
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            System.out.println("Your total score is " + marks);
                            break;
                        case 3:
                            return "Logged out";
                    }
                }
            }
        } else {
            return "UserName or Password is invalid";
        }

    }

    public List<Question> attendQuiz(String quizCategory) {
        return categoryQuestionMap.get(quizCategory);
    }
}

