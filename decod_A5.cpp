#include<cmath>
#include<map>
#include<fstream>
#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
#include<cstring>

using namespace std;

bool XOR(bool a, bool b)
{
	return (a + b) % 2;
}
int main() {
	wstring password;
	getline(wcin, password);
	vector<wstring> cod;
	wstring z;
	while (wcin >> z) {

		cod.push_back(z);
	}
	
	unsigned long long key = 0;
	string key_str;
	
	for (unsigned long long i = 0; i < password.size(); i++) {
		int x = int(password[i]);
		key += x;
	}
	int x = key;
	while (x > 0) {
		key_str += char(x % 2 + 48);
		x = x / 2;

	}
	reverse(key_str.begin(), key_str.end());
	vector<wstring> decod;
	for (int i = 0; i < cod.size(); i++) {
		wstring s;
		for (int q = 0; q < cod[i].size(); q++) {
			bool a = XOR(int(cod[i][q])-48, int(key_str[q*(i+1)])-48);
			key_str.push_back(char(int(XOR(int(key_str[key_str.size() - 2]) - 48, int(key_str[key_str.size() - 4]) - 48) + 48)));
			s += wchar_t(int(a) + 48);

		}
		reverse(s.begin(), s.end());
		decod.push_back(s);
	}
	wstring text;
	int r = 0;
	for (int i = 0; i < decod.size(); i++) {
		r = 0;
		for (int q = 0; q < decod[i].size(); q++) {
			r +=(int(decod[i][q]) - 48)* pow(2, q);
		}
		text += wchar_t(r);
	}
	wcout << text;
}