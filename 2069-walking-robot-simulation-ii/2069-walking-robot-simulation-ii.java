import java.util.ArrayList;

class Robot {
    private ArrayList<int[]> pos;
    private String[] dir;
    private int i;
    private boolean isOrigin;

    public Robot(int width, int height) {
        pos = new ArrayList<>();
        dir = new String[width * 2 + height * 2 - 4];

        pos.add(new int[]{0, 0});
        dir[0] = "East";

        int k = 1;

        for (int x = 1; x < width; x++) {
            pos.add(new int[]{x, 0});
            dir[k++] = "East";
        }

        for (int y = 1; y < height; y++) {
            pos.add(new int[]{width - 1, y});
            dir[k++] = "North";
        }

        for (int x = width - 2; x >= 0; x--) {
            pos.add(new int[]{x, height - 1});
            dir[k++] = "West";
        }

        for (int y = height - 2; y > 0; y--) {
            pos.add(new int[]{0, y});
            dir[k++] = "South";
        }

        i = 0;
        isOrigin = true;
    }

    public void step(int num) {
        int n = pos.size();
        isOrigin = false;
        i = (i + num) % n;
    }

    public int[] getPos() {
        return pos.get(i);
    }

    public String getDir() {
        if (isOrigin) return "East";
        if (i == 0) return "South";
        return dir[i];
    }
}