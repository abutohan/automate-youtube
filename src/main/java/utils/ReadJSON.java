package utils;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class ReadJSON {

    private final String jsonFilePath;


    public ReadJSON(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public Object[][] getData() throws IOException {
        String json = readFile();
        JSONArray jsonArray = new JSONArray(json);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> new Object[]{jsonArray.getJSONObject(i)})
                .toArray(Object[][]::new);
    }

    private String readFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get(jsonFilePath)));
    }
}
