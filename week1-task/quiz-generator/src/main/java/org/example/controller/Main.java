package org.example.controller;

import org.example.service.QuizService;
import org.example.service.impl.QuizServiceImpl;

import java.util.Scanner;

public class Main {
    QuizService quizService = new QuizServiceImpl();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        Main main = new Main();
        while (flag == 0) {
            System.out.println("Welcome to Quiz Generator\nEnter choice:\n1.Register User\n2.Login User");
            int choice = scanner.nextInt();
            main.registerOrLogin(choice, scanner);
        }
    }

    private void registerOrLogin(Integer choice, Scanner scanner) {
        if (choice == 1) {
            System.out.println(quizService.signUp(scanner));
        } else if (choice == 2) {
            System.out.println(quizService.login(scanner));
        } else {
            System.out.println("Enter right choice as 1 or 2");
        }
    }
}