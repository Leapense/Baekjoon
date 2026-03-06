#include <iostream>
#include <algorithm>

using namespace std;

static long long nextEven(long long value) {
    return (value % 2 == 0) ? value : value + 1;
}

static long long previousEven(long long value) {
    return (value % 2 == 0) ? value : value - 1;
}

static long long sumEvenFromAtLeastFour(long long a, long long b) {
    const long long lowerBound = max(a, 4LL);
    const long long firstEven = nextEven(lowerBound);
    const long long lastEven = previousEven(b);

    if (firstEven > lastEven) {
        return 0;
    }

    const long long count = (lastEven - firstEven) / 2 + 1;
    const long long pairSum = firstEven + lastEven;

    if (count % 2 == 0) {
        return (count / 2) * pairSum;
    }

    return count * (pairSum / 2);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long a, b;
    cin >> a >> b;

    cout << sumEvenFromAtLeastFour(a, b);
    return 0;
}