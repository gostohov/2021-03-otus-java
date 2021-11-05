package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final String outputFilePath;

    public FileSerializer(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        try (FileWriter fw = new FileWriter(outputFilePath)) {
            Gson gson = new Gson();
            gson.toJson(data, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
