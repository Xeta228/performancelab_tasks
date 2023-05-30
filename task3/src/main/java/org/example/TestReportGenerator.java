package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestReportGenerator {
    public static void main(String[] args) {

        Path testsFilePath = Paths.get(args[0]);
        Path valuesFilePath = Paths.get(args[1]);

        try {

            JsonElement tests = new Gson().fromJson(Files.newBufferedReader(testsFilePath), JsonElement.class);
            JsonObject testsJsonObject = tests.getAsJsonObject();


            JsonElement values = new Gson().fromJson(Files.newBufferedReader(valuesFilePath), JsonElement.class);
            JsonObject valuesJsonObject = values.getAsJsonObject();


            JsonArray testsArray;
            if (testsJsonObject.has("tests") && testsJsonObject.get("tests").isJsonArray()) {
                testsArray = testsJsonObject.getAsJsonArray("tests");
            } else {
                testsArray = new JsonArray();
                testsArray.add(testsJsonObject);
            }

            JsonArray valuesArray = valuesJsonObject.getAsJsonArray("values");
            processTests(testsArray, valuesArray);


            writeJsonToFile(testsJsonObject);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static void processTests(JsonArray testsArray, JsonArray valuesArray) {
        for (JsonElement testElement : testsArray) {
            JsonObject testJsonObject = testElement.getAsJsonObject();
            int id = testJsonObject.get("id").getAsInt();
            String value = getValueForTestId(valuesArray, id);
            if (!value.isEmpty()) {
                updateTestValue(testJsonObject, value);
            }

            if (testJsonObject.has("values") && testJsonObject.get("values").isJsonArray()) {
                JsonArray nestedValuesArray = testJsonObject.getAsJsonArray("values");
                processTests(nestedValuesArray, valuesArray);
            }
        }
    }

    private static String getValueForTestId(JsonArray valuesArray, int id) {
        for (JsonElement valueElement : valuesArray) {
            JsonObject valueJsonObject = valueElement.getAsJsonObject();
            if (valueJsonObject.get("id").getAsInt() == id) {
                return valueJsonObject.get("value").getAsString();
            }
        }
        return "";
    }

    private static void updateTestValue(JsonObject testJsonObject, String value) {
        testJsonObject.addProperty("value", value);
    }

    private static void writeJsonToFile(JsonObject jsonObject) throws IOException {
        try (FileWriter writer = new FileWriter("report.json")) {
            new Gson().toJson(jsonObject, writer);
        }
    }
}