public class UpDownHiking
{
    public int maxHeight(int N, int A, int B) {
        int high = N * A;
        int low = 0;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (isPossible(mid, N, A, B)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static boolean isPossible(int height, int N, int A, int B) {
        int ascend = height / A;
        if (ascend * A != height)
            ascend++;
        int descend = height / B;
        if (descend * B != height)
            descend++;
        return ascend + descend <= N;
    }
}
