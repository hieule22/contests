public class LastDigit
{
    public long findX(long S) {
        long high = S;
        long low = 1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long sum = transform(mid);
            if (sum == S)
                return mid;
            else if (sum < S)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    private static long transform(long num) {
        long result = 0;
        while (num > 0) {
            result += num;
            num /= 10;
        }
        return result;
    }
}
