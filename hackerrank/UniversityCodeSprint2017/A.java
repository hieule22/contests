import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] score = new int[n];
        for(int score_i=0; score_i < n; score_i++){
            score[score_i] = in.nextInt();
        }
        // your code goes here
        int maximum = score[0];
        int minimum = score[0];
        int nMinUpdates = 0, nMaxUpdates = 0;
        for (int i = 1; i < n; ++i) {
            if (score[i] > maximum) {
                maximum = score[i];
                ++nMaxUpdates;
            }
            if (score[i] < minimum) {
                minimum = score[i];
                ++nMinUpdates;
            }
        }
        
        System.out.printf("%d %d\n", nMaxUpdates, nMinUpdates);
    }
}
