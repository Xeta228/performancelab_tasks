import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task2 {
    public static void main(String[] args) {
        Path pathToTheCircleFile = Paths.get(args[0]);
        Path pathToThePointsFile = Paths.get(args[1]);


        try (BufferedReader circleReader = Files.newBufferedReader(pathToTheCircleFile);
             BufferedReader pointsReader = Files.newBufferedReader(pathToThePointsFile)){

            String circle = circleReader.readLine();
            String[] circleCenterCoordinates = circle.split(" ");
            double circleCenterX = Double.parseDouble(circleCenterCoordinates[0]);
            double circleCenterY = Double.parseDouble(circleCenterCoordinates[1]);
            double radius = Double.parseDouble(circleReader.readLine());

            String point;
            while ((point = pointsReader.readLine()) != null) {
                String[] pointCoordinates = point.split(" ");
                double pointX = Double.parseDouble(pointCoordinates[0]);
                double pointY = Double.parseDouble(pointCoordinates[1]);
                System.out.println(getResultForPoint(circleCenterX, circleCenterY, radius, pointX, pointY));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static int getResultForPoint(double circleCenterX, double circleCenterY, double radius,
                                         double pointX, double pointY) {
        double distanceSquared = Math.pow(pointX - circleCenterX, 2) + Math.pow(pointY - circleCenterY, 2);

        if (distanceSquared < Math.pow(radius, 2)) {
            return 1;
        } else if (distanceSquared == Math.pow(radius, 2)) {
            return 0;
        } else {
            return 2;
        }
    }
}
