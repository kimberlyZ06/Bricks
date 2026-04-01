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
    }

    private static int partOne(ArrayList<Brick> bricks) {
        int totalLength = 0;
        for (int i = 0; i < bricks.size(); i++) {
            totalLength += bricks.get(i).getLength();
        }
        return totalLength;
    }

    private static int partTwo(ArrayList<Brick> bricks) {
//        ArrayList<Integer> positions = new ArrayList<Integer>();
        // first brick
        ArrayList<Integer> previous = new ArrayList<>();
        for (int i = bricks.get(0).getStart(); i <= bricks.get(0).getEnd(); i++) {
            previous.add(i);
        }
        int height = 0;
        for (int i = 0; i < bricks.size(); i++) {
            ArrayList<Integer> currentbrick = new ArrayList<>();
            for (int j = bricks.get(i).getStart(); j <= bricks.get(i).getEnd(); j++) {
                // check for overlap
                currentbrick.add(j);

//                positions.add(j);
//                System.out.println(positions);
            }
            Brick current = bricks.get(i);
            System.out.println(current);
            if(current.overlap(currentbrick,previous)){
                height++;
                previous = currentbrick;
            }
        }

//        int highest = 0;
//        for (int i = 0; i < positions.size() - 1; i++) {
//            if (highest < positions.get(i)){
//                highest = positions.get(i);
//            }
//        }
//
//        for (int index = 0; index <= highest; index++) {
//            int counter = 0;
//            for (int i = 0; i < positions.size(); i++) {
//                if (positions.get(i) == index){
//                    counter++;
//                }
//            }
//            System.out.println("counter for " + index + ": " + counter);
//            if (counter > height){
//                height = counter;
//            }
//        }
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
