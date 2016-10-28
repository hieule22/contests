import java.util.Arrays;

public class SortingSubsets {
    public int getMinimalSize(int[] a) {
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        Arrays.sort(b);

        int result = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i])
                ++result;
        }
        return result;
    }
}
