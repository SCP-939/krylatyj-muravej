/*#include<cmath>
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
	unsigned long long key=0;
	string key_str;

	for (unsigned long long i = 0; i < open_massage.size(); i++) {
		int x = int(open_massage[i]);
		key += x;
	}
	int x = key;
	while (x>0) {
		key_str += char(x % 2 + 48);
		x = x / 2;

	}
	reverse(key_str.begin(), key_str.end());
	wstring text;
	getline(wcin, text);
	vector<wstring> text_char;
	for (int i = 0; i < text.size(); i++) {
		wstring s;
		x = int(text[i]);
		while (x > 0) {
			s += char(x % 2 + 48);
			x = x / 2;

		}
		reverse(s.begin(), s.end());
		text_char.push_back(s);
	}
	vector<wstring> cod;
	for (int i = 0; i < text.size(); i++) {
		wstring s;
		for (int q = 0; q < text_char[i].size(); q++) {
			bool a = XOR(int(text_char[i][q]) - 48, int(key_str[q*(i+1)])-48);
			key_str.push_back( char  (int  (XOR  (int(key_str[key_str.size() - 2]) - 48,   int(key_str[key_str.size() - 4])   - 48)   + 48)  )  );
			s += wchar_t(int(a)+48);
			
		}
		cod.push_back(s);
	}
	for (int i = 0; i < cod.size(); i++) {
		wcout << cod[i]<<endl;
	}
}*/