public class ThueMorseGame {
    public String get(int n, int m) {
        int lastLoseSpot = 0;
        while (lastLoseSpot < n) {
            lastLoseSpot += m + 1;
            while (countSetBits(lastLoseSpot) % 2 == 1)
                ++lastLoseSpot;
        }

        return lastLoseSpot == n ? "Bob" : "Alice";
    }

    private int countSetBits(int n) {
        int result = 0;
        while (n > 0) {
            result++;
            n -= (n & -n);
        }
        return result;
    }
}
