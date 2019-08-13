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
	wstring open_massage;
	vector<unsigned long long> close_massage;
	unsigned long long x;
	while (cin >> x) {
		close_massage.push_back(x);
	}
	unsigned long long key_p=163, key_q=167;
	unsigned long long open_n = 27221;
	unsigned long long f_n = 26892;
	unsigned long long open_e=11;
	unsigned long long kof_k=4;
	unsigned long long key_d = (kof_k * f_n + 1) / open_e;
	for (unsigned long long i = 0; i < close_massage.size(); i++) {
		unsigned long long a = close_massage[i];
		a = powwer(a, key_d, open_n);
		open_massage.push_back(wchar_t(a));
	}
	wcout << open_massage;
}