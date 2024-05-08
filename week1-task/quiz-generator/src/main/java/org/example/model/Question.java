package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String questionDescription;
    private List<String> options = new ArrayList<>();
    private String rightAnswer;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private int mark;

    public Question(int id, String questionDescription, List<String> options, String rightAnswer, int mark, String category) {
        this.id = id;
        this.questionDescription = questionDescription;
        this.options = options;
        this.rightAnswer = rightAnswer;
        this.mark = mark;
        this.category = category;
    }
    public Question(){}
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionDescription='" + questionDescription + '\'' +
                ", options=" + options +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", mark=" + mark +
                '}';
    }
}
