/*#include<cmath>
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
		c = c * a;
		c = c % open_n;

	}
	return c % open_n;
}

int main() {
	string open_massage;
	getline(cin, open_massage);
	vector<char> massage;
	for (unsigned long long i = 0; i < open_massage.size(); i++) {
		massage.push_back(open_massage[i]);
	}
	vector<unsigned long long> close_massage;
	unsigned long long open_n = 27221;
	unsigned long long open_e = 11;
	for (unsigned long long i = 0; i < massage.size(); i++) {
		unsigned long long a = unsigned long long(massage[i]);
		a = powwer(a, open_e, open_n);
		close_massage.push_back(a);
	}
	for (unsigned long long i = 0; i < massage.size(); i++) {
		cout << close_massage[i] << '\n';
	}


}*/