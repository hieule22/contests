#include <memory>
#include <sstream>
#include <stack>
#include <string>

struct Node {
  int value;
  std::unique_ptr<Node> next;
};

std::string ToString(int value) {
  std::stringstream ss;
  ss << value;
  std::string result;
  ss >> result;
  return result;
}

std::unique_ptr<Node> Add(Node* a, Node* b, int n) {
  int vala = 0;
  int valb = 0;

  Node* aiter = a;
  Node* biter = b;
  while (aiter != nullptr) {
    vala = 10 * vala + aiter->value;
    valb = 10 * valb + biter->value;
    aiter = aiter->next.get();
    biter = biter->next.get();
  }

  int sum = vala + valb;
  std::string s = ToString(sum);
  
}
