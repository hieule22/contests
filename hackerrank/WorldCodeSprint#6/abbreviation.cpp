#include <iostream>
#include <string.h>

using namespace std;

const int N = 1000;
char a[N], b[N];
// convert[i][j] == true iff a[1..i] can be converted to b[1..j].
bool convert[N + 1][N + 1];

int main() {
  ios_base::sync_with_stdio(0);
  int q;
  cin >> q;
  for (int qq = 0; qq < q; ++qq) {
    cin >> a >> b;
    int la = strlen(a), lb = strlen(b);

    convert[0][0] = true; // Empty string is convertible to empty string.
    // Empty string cannot be converted to non-empty string.
    for (int i = 1; i <= lb; ++i)
      convert[0][i] = false;
    
    for (int i = 1; i <= la; ++i) {
      char aa = a[i - 1];  // Offset 0-based index.
      // Only prefix consisting of only lowercase letters is convertible.
      convert[i][0] = convert[i - 1][0] ? islower(aa) : false;
      for (int j = 1; j <= lb; ++j) {
        char bb = b[j - 1];  // Offset 0-based index.
        if (i < j) { // Cannot convert to a longer string.
          convert[i][j] = false;
          continue;
        }

        if (isupper(aa)) {  // aa must be used in the conversion.
          convert[i][j] = (convert[i - 1][j - 1] && (aa == bb));
        } else {  // aa might or might not be used in the conversion.
          convert[i][j] = convert[i - 1][j]
              || (convert[i - 1][j - 1] && (aa == tolower(bb)));
        }
      }
    }

    cout << (convert[la][lb] ? "YES" : "NO") << endl;
  }
}
