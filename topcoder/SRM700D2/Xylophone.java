public class Xylophone {
    public int countKeys(int[] keys) {
        boolean[] isHit = new boolean[7];
        for (int key : keys) {
            isHit[key % isHit.length] = true;
        }

        int result = 0;
        for (boolean flag : isHit) {
            if (flag)
                result++;
        }
        return result;
    }

}
