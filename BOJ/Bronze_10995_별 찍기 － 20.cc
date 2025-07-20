#include <iostream>

using namespace std;

int main() {
  int n = 0;

  cin >> n;
//   n = 10;

  for (int i = 1; i <= n; ++i) {
    if (i % 2 != 0) {
      for (int j = 1; j <= n; ++j) {
        if (j < n) {
          cout << "* ";
        } else {
          cout << "*";
        }
      }
    } else {
      for (int j = 1; j <= n; ++j) {
        cout << " *";
      }
    }
    cout << "\n";
  }
}
