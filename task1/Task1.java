import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        calculatePath(n, m).forEach(System.out::print);
    }

    public static List<Integer> calculatePath(int n, int m) {
        List<Integer> path = new ArrayList<>();
        int currentPosition = 0;
        do {
            path.add(currentPosition + 1);
            currentPosition = (currentPosition + m - 1) % n;
        } while (currentPosition != 0);
        return path;
    }

}
