import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        Path pathToTheFile = Paths.get(args[0]);
        int[] nums = parseTheArrayFromFile(pathToTheFile);
        int minMoves = minMovesToEqualizeArray(nums);
        System.out.print(minMoves);
    }

    private static int[] parseTheArrayFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            int[] nums = new int[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                nums[i] = Integer.parseInt(lines.get(i));
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
