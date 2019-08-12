#include<cmath>
#include<map>
#include<fstream>
#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
#include<cstring>
using namespace std;
unsigned long long powwer(unsigned long long a, unsigned long long b, unsigned long long open_n) {
	unsigned long long c = a;
	for (unsigned long long i = 1; i < b; i++) {
		c = c * a;c = c % open_n;

	}
	return c%open_n;
}
int main() {
	unsigned long long close_massage,open_massage;
	cin >> close_massage;
	unsigned long long key_p=163, key_q=167;
	unsigned long long open_n = 27221;
	unsigned long long f_n = 26892;
	unsigned long long open_e=11;
	unsigned long long kof_k=4;
	unsigned long long key_d = (kof_k * f_n + 1) / open_e;

	open_massage = powwer(close_massage, key_d, open_n);
	cout << open_massage;
}