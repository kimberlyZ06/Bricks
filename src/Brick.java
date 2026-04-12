import java.util.ArrayList;

public class Brick {
    private int start;
    private int end;

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    private int layer;

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
        layer = 1;
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

    public boolean overlap (Brick current, Brick previous){
        for (int i = current.getStart(); i <= current.getEnd(); i++) {
            for (int j = previous.getStart(); j <= previous.getEnd(); j++) {
                if (i == j) {
                    return true;
                }
            }
        }
        return false;
    }
}
