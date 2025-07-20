#include <iostream>

using namespace std;

int main() {
    int N;

    cin >> N;
    // N = 10;

    for (int i = 1 ; i <= N ; ++i) {
        for (int j = 0 ; j < N - i ; ++j) {
            cout << " ";
        }

        for (int j = 0 ; j < i ; ++j) {
            if (j == 0) {
                cout << "*";
            } else {
                cout << " *";
            }
        }

        if (i < N) {
            cout << "\n";
        }
    }
}
