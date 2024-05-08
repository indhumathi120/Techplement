package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Question;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    public static List<Question> loadQuestion() {
        Path jsonFilePath = Paths.get("src/main/resources/questions.json");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Question> questions = objectMapper.readValue(jsonFilePath.toFile(), new TypeReference<List<Question>>() {
            });
            return questions;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
