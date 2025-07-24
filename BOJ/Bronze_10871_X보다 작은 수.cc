#include <iostream>
#include <math.h>

using namespace std;

int main() {
    int n, x;
    
    cin >> n >> x;

    int arr[n] = {0};
    bool needSpace = false;

    for (int i = 0 ; i < n ; ++i) {
        cin >> arr[i];
    }

    for (int i = 0 ; i < n ; ++i) {
        if (arr[i] < x) {
            cout << (needSpace ? " " : "") << arr[i];
            needSpace = true;
        }
    }

}
