import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
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
        ArrayList<Brick> checked = new ArrayList<>();
        checked.add(bricks.get(0));
        for (int i = 1; i < bricks.size(); i++) {
            Brick current = bricks.get(i);
//            System.out.println("Current brick: " + current);
            for (int j = 0; j < checked.size(); j++) {
//                System.out.println("Current Check: " + checked.get(j));
                if(current.overlap(current,checked.get(j))){
                    bricks.get(i).setLayer(checked.get(j).getLayer() + 1);
//                    System.out.println("Current brick layer: " + bricks.get(i).getLayer());
                }
            }
            checked.add(bricks.get(i));
//            System.out.println(checked);
        }

        int height = 0;
        for (int i = 0; i < checked.size(); i++) {
            int currentHeight = checked.get(i).getLayer();
            if (currentHeight > height){
                height = currentHeight;
            }
        }
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

        int layer = 0;
        for (int i = 0; i < bricks.size(); i++) {
            bricks.get(i).setLayer(layer);
            for (int j = bricks.get(i).getStart(); j <= bricks.get(i).getEnd(); j++) {
                array[bricks.get(i).getLayer()][j - 1] = 1;
            }
            Brick current = bricks.get(i);
            if(i != 0) {
                System.out.println("Current brick: " + current);
                System.out.println("Previous brick: " + bricks.get(i - 1));
            }
            if(i > 0) {
                if (current.overlap(current, bricks.get(i - 1))) {
                    System.out.println(current + " overlaps w/ " + bricks.get(i-1));
                    layer++;
                }
            }
        }
        System.out.println(Arrays.deepToString(array));

        int height = array.length;
        for (int i = array.length - 1; i >= 0 ; i--) {
            boolean empty = true;
            if (array[i].toString().contains("1")){
                empty = false;
            }

            if (empty){
                height--;
            }
        }
        return height;
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
