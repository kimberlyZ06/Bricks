import java.util.ArrayList;

public class Brick {
    private int start;
    private int end;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private int length;

    public Brick(int start, int end) {
        this.start = start;
        this.end = end;
        length = end - start;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String toString() {
        return "Start: " + start + " --- End: " + end;
    }

    public boolean overlap (ArrayList<Integer> current, ArrayList<Integer> previous){
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i) <= previous.get(previous.size() - 1) && current.get(i) >= previous.get(0)){
                return true;
            }
        }
        return false;
    }
}
