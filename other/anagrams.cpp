#include <string>
#include <assert.h>

bool AreEqual(const char a[26], const char b[26]) {
  for (int i = 0; i < 26; ++i) {
    if (a[i] != b[i]) {
      return false;
    }
  }
  return true;
}

int CountAnagrams(const std::string& text, const std::string& word) {
  if (text.length() < word.length()) {
    return false;
  }
  
  char expected[26], actual[26];
  for (int i = 0; i < 26; ++i) {
    expected[i] = 0;
    actual[i] = 0;
  }
  
  for (int i = 0; i < word.length(); ++i) {
    expected[word[i] - 'a']++;
    actual[text[i] - 'a']++;
  }

  int result = 0;
  if (AreEqual(actual, expected)) {
    ++result;
  }

  for (int i = word.length(); i < text.length(); ++i) {
    actual[text[i - word.length()] - 'a']--;
    actual[text[i] - 'a']++;
    if (AreEqual(actual, expected)) {
      ++result;
    }
  }

  return result;
}

int main() {
  assert(CountAnagrams("forxxorfxdofr", "for") == 3);
  assert(CountAnagrams("abc", "cba") == 1);
}
