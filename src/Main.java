import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/brick_layout");
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }


        System.out.println(partOne(bricks));
        System.out.println(partTwo(bricks));
        System.out.println(part2dArray(bricks));
    }

    private static int partOne(ArrayList<Brick> bricks) {
        int totalLength = 0;
        for (int i = 0; i < bricks.size(); i++) {
            totalLength += bricks.get(i).getLength();
        }
        return totalLength;
    }

    private static int partTwo(ArrayList<Brick> bricks) {
        int max = 0;
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getEnd() > max){
                max = bricks.get(i).getEnd();
            }
        }

        ArrayList<Integer> counter = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            counter.add(0);
        }

        int height = 1;
        for (int i = 0; i < bricks.size() - 1; i++) {
            for (int j = bricks.get(i).getStart(); j <= bricks.get(i).getEnd(); j++) {
                counter.set(j - 1, height);
            }
            Brick next = bricks.get(i + 1);
            if (next.overlap(next, bricks.get(i))) {
                height++;
            } else {

            }
        }
        System.out.println(counter);
        return height;
    }

    private static int part2dArray(ArrayList<Brick> bricks) {
        int maxX = 0;
        for (int i = 0; i < bricks.size(); i++) {
            int currentX = bricks.get(i).getEnd();
            if (currentX > maxX){
                maxX = currentX;
            }
        }
        int maxY = bricks.size();
        int[][] array = new int[maxY][maxX];

        for (int i = 0; i < bricks.size(); i++) {
            ArrayList<Integer> counter = new ArrayList<>();
            for (int col = bricks.get(i).getStart(); col <= bricks.get(i).getEnd(); col++) {
                int count = 0;
                for (int row = 0; row < maxY; row++) {
                    if (array[row][col - 1] == 1){
                        count = row;
                    }
                }
                counter.add(count);
//                System.out.println("counter: " + counter);
            }
            int nextLayer = 0;
            for (int j = 0; j < counter.size(); j++) {
                if (counter.get(j) > nextLayer){
                    nextLayer = counter.get(j);
                }
            }
//            System.out.println("Next layer: " + nextLayer);

            for (int col = bricks.get(i).getStart(); col <= bricks.get(i).getEnd(); col++) {
                for (int row = nextLayer + 1; row > 0; row--)
                array[row][col - 1] = 1;
            }
//            System.out.println("Array: " + Arrays.deepToString(array));
        }


        ArrayList<Integer> heights =new ArrayList<>();
        for (int col = 0; col < maxX; col++) {
            int height = 0;
            for (int row = 0; row < maxY; row++) {
                if(array[row][col] == 1){
                    height++;
                }
            }
            heights.add(height);
        }

        int maxHeight = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (heights.get(i) > maxHeight){
                maxHeight = heights.get(i);
            }
        }
        return maxHeight;
    }

    public static ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }
}
