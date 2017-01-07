import java.io.OutputStream;
import java.io.FilenameFilter;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.math.RoundingMode;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class FightingTheZombie {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "fighting.*the.*zombie.*[.]txt";
            File directory = new File(".");
            File[] candidates = directory.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.matches(regex);
                }
            });
            File toRun = null;
            for (File candidate : candidates) {
                if (toRun == null || candidate.lastModified() > toRun.lastModified())
                    toRun = candidate;
            }
            inputStream = new FileInputStream(toRun);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("fightingthezombie.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        FightingTheZombie solver = new FightingTheZombie();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class FightingTheZombie {
        private static final int MAX_ROLLS = 20;
        private static final int[] DICE_TYPES = {4, 6, 8, 10, 12, 20};
        private BigInteger[][][] ways;

        public FightingTheZombie() {
            ways = new BigInteger[DICE_TYPES.length][][];
            for (int i = 0; i < DICE_TYPES.length; ++i) {
                int nSides = DICE_TYPES[i];
                int maxValue = nSides * MAX_ROLLS;
                ways[i] = new BigInteger[MAX_ROLLS + 1][maxValue + 1];
                for (int j = 0; j <= MAX_ROLLS; ++j)
                    Arrays.fill(ways[i][j], BigInteger.ZERO);

                ways[i][0][0] = BigInteger.ONE;  // Base case.
                for (int j = 1; j <= MAX_ROLLS; ++j) {
                    for (int k = 0; k <= maxValue; ++k) {
                        for (int val = 1; val <= nSides; ++val) {
                            int lastSum = k - val;
                            if (lastSum >= 0) {
                                ways[i][j][k] = ways[i][j][k].add(ways[i][j - 1][lastSum]);
                            }
                        }
                    }
                }
            }
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int minDamage = in.nextInt();
            int nSpells = in.nextInt();
            double maxProbability = 0;
            for (int i = 0; i < nSpells; ++i) {
                Spell spell = new Spell(in.next());
                int diceType = Arrays.binarySearch(DICE_TYPES, spell.nSides);
                int lowerBound = Math.max(0, minDamage - spell.offset);
                BigInteger totalOutcomes = BigInteger.ZERO;
                for (BigInteger val : ways[diceType][spell.nRolls])
                    totalOutcomes = totalOutcomes.add(val);
                BigInteger nOutcomes = BigInteger.ZERO;
                for (int j = lowerBound; j < ways[diceType][spell.nRolls].length; ++j)
                    nOutcomes = nOutcomes.add(ways[diceType][spell.nRolls][j]);

                BigDecimal probability = (new BigDecimal(nOutcomes)).divide(new BigDecimal(totalOutcomes), 7,
                        RoundingMode.HALF_UP);
                maxProbability = Math.max(maxProbability, probability.doubleValue());
            }
            out.printf("Case #%d: %.7f\n", testNumber, maxProbability);
        }

        private class Spell {
            private int nSides;
            private int nRolls;
            private int offset;

            private Spell(String notation) {
                String[] tokens = notation.split("d");
                nRolls = Integer.parseInt(tokens[0]);
                if (tokens[1].contains("+")) {
                    String[] parts = tokens[1].split("\\+");
                    nSides = Integer.parseInt(parts[0]);
                    offset = Integer.parseInt(parts[1]);
                } else if (tokens[1].contains("-")) {
                    String[] parts = tokens[1].split("-");
                    nSides = Integer.parseInt(parts[0]);
                    offset = -Integer.parseInt(parts[1]);
                } else {
                    nSides = Integer.parseInt(tokens[1]);
                }
            }

        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private static final int BUFFER_SIZE = 32768;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(
                    new InputStreamReader(stream), BUFFER_SIZE);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
