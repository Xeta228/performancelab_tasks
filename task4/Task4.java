import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        String pathToTheFile = args[0];
        int[] nums = parseTheArrayFromFile(pathToTheFile);
        int minMoves = minMovesToEqualizeArray(nums);
        System.out.print(minMoves);
    }

    private static int[] parseTheArrayFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            String[] values = content.split("\n");
            int[] nums = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                nums[i] = Integer.parseInt(values[i]);
            }
            return nums;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static int minMovesToEqualizeArray(int[] nums) {
        Arrays.sort(nums);
        int medianElement = nums[nums.length / 2];
        int minMoves = 0;
        for (int num : nums) {
            minMoves += Math.abs(num - medianElement);
        }
        return minMoves;
    }
}
