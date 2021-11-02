package ru.otus.dataprocessor;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileLoader implements Loader {

    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try {
            Type listType = new TypeToken<ArrayList<Measurement>>(){}.getType();
            URL url = Resources.getResource(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url.getPath()));
            return new Gson().fromJson(bufferedReader, listType);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
