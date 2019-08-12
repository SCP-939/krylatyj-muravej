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
		c = c * a;
		c = c % open_n;
		
	}
	return c % open_n;
}

int main() {
	unsigned long long open_massage, close_massage;
	cin >> open_massage;
	unsigned long long open_n = 27221;
	unsigned long long open_e = 11;
	
	close_massage = powwer(open_massage,open_e, open_n);
	cout << close_massage;
	

}