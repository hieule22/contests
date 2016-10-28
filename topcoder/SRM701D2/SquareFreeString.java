public class SquareFreeString {
    private static final String YES = "square-free";
    private static final String NO = "not square-free";

    public String isSquareFree(String s) {
        for (int start = 0; start < s.length() - 1; ++start) {
            int length = 2;
            for (int end = start + 1; end < s.length(); end += 2) {
                boolean ok = false;
                int mid = start + length / 2;
                for (int i = 0; i < length / 2; ++i) {
                    if (s.charAt(start + i) != s.charAt(mid + i)) {
                        ok = true;
                        break;
                    }
                }

                if (!ok)
                    return NO;

                length += 2;
            }
        }
        return YES;
    }
}
