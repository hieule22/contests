import java.util.Arrays;

public class MiniatureDachshund {
    private static final int LIMIT = 5000;

    public int maxMikan(int[] mikan, int weight) {
        Arrays.sort(mikan);
        int count = 0;
        for (int w : mikan) {
            weight += w;
            if (weight > LIMIT)
                break;
            ++count;
        }
        return count;
    }

}
