import java.io.*;
import java.util.*;

public class BingItOn {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    PrintWriter output = new PrintWriter(System.out);
    BingItOn mn = new BingItOn();
    mn.solve(input, output);
    output.close();
  }

  public void solve(Scanner input, PrintWriter output) {
    int n = input.nextInt();
    Trie trie = new Trie();
    for (int i = 0; i < n; i++) {
      String text = input.next();
      output.println(trie.get(text));
      trie.add(text);
    }
  }

  private class Trie {
    TrieNode root;
    
    private Trie() {
      root = new TrieNode();
    }

    private void add(String s) {
      TrieNode current = root;
      for (int i = 0; i < s.length(); i++) {
        char letter = s.charAt(i);
        int idx = letter - 'a';
        if (current.next[idx] == null) {
          TrieNode newNode = new TrieNode();
          current.next[idx] = newNode;
        }
        current.next[idx].count++;
        current = current.next[idx];
      }
    }

    private int get(String s) {
      TrieNode current = root;
      for (int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        if (current.next[idx] == null) {
          return 0;
        }
        current = current.next[idx];
      }
      return current.count;
    }

    private class TrieNode {
      private int count;
      private TrieNode[] next;

      private TrieNode() {
        count = 0;
        next = new TrieNode[26];
      }
    }
  } 
}
