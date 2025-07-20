#include <iostream>

using namespace std;

int main()
{
	int n = 0, ans = 0;

	cin >> n;
	// n = 2;

	for (int i = 0; i < n + 1; ++i)
	{
		for (int j = i; j < n + 1; ++j)
		{
			ans += i + j;
		}
	}

	cout << ans;
}