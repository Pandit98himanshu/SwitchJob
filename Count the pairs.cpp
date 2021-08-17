/*
Question:
You are given 2 arrays A & B. ∀ i ∈ [1, N] find the number of pairs (i, j) such that GCD(A[i], B[j]) != 1
sizeof(A) = sizeof(B) = N

Constraints:
1 ≤ N ≤ 10^5
1 ≤ A[i], B[i] ≤ 10^6

Output:
Total number of pairs satisfying the given condition
*/

                    //////////////////
                    // WRONG ANSWER //
                    //////////////////

// Copied from https://stackoverflow.com/a/53962974
// Approach: Find all pairs which are co-prime then,
// answer will be (total number of pairs) - (total co-prime pairs)

#include <bits/stdc++.h>

using namespace std;

#define MAXSIZE 100001

long long ans;
int N;
int A[MAXSIZE];
int B[MAXSIZE];

// get prime factors of a using pre-generated sieve
vector<int> getPrimeFactors(int a, const vector<int> & primes) {
    vector<int> f;
    for (auto p : primes) {
        if (p > a) break;
        if (a % p == 0) {
            f.push_back(p);
            do {
                a /= p;
            } while (a % p == 0);
        }
    }
    if (a > 1) f.push_back(a);

    return f;
}

// find co-prime pairs A_i and B_j
// A_i and B_i <= 1e6
void solve(){

    // generate prime sieve
    vector<int> primes;
    primes.push_back(2);

    for (int i = 3; i*i <= 1e6; ++i) {
        bool isPrime = true;
        for (auto p : primes) {
            if (i % p == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            primes.push_back(i);
        }
    }

    struct Entry {
        int n = 0;
        int p = 0;
    };

    // cntp[X] - number of times the product X can be expressed
    // with prime factors of A_i
    map<int, int> cntp;

    for (int i = 0; i < N; i++) {
        auto f = getPrimeFactors(A[i], primes);

        // count possible products using non-repeating prime factors of A_i
        vector<Entry> x;
        x.push_back({ 0, 1 });

        for (auto p : f) {
            int k = x.size();
            for (int i = 0; i < k; ++i) {
                int nn = x[i].n + 1;
                int pp = x[i].p*p;

                ++cntp[pp];
                x.push_back({ nn, pp });
            }
        }
    }

    // use Inclusion–exclusion principle to count non-coprime pairs
    // and subtract them from the total number of pairs N*N

    int cnt = N; cnt *= N;

    for (int i = 0; i < N; i++) {
        auto f = getPrimeFactors(B[i], primes);

        vector<Entry> x;
        x.push_back({ 0, 1 });

        for (auto p : f) {
            int k = x.size();
            for (int i = 0; i < k; ++i) {
                int nn = x[i].n + 1;
                int pp = x[i].p*p;

                x.push_back({ nn, pp });

                if (nn % 2 == 1) {
                    cnt -= cntp[pp];
                } else {
                    cnt += cntp[pp];
                }
            }
        }
    }

    ans = N * N - cnt;
}

int main() {
    cin >> N;
    for (int i = 0; i < N; i++)
        cin >> A[i];
    for (int i = 0; i < N; i++)
        cin >> B[i];

    solve();
    cout<<ans<<endl;
}