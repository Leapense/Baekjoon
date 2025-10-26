#include <iostream>
#include <vector>
#include <numeric>
#include <string>

struct Statement {
    std::vector<int> says_true;
    std::vector<int> says_false;
};

int N;
std::vector<Statement> statements;
std::vector<bool> hypothesis;

bool check() {
    for (int i = 1; i <= N; ++i) {
        bool all_claims_are_correct = true;
        for (int person_id : statements[i].says_true) {
            if (!hypothesis[person_id]) {
                all_claims_are_correct = false;
                break;
            }
        }
        if (!all_claims_are_correct) {

        } else {
            for (int person_id : statements[i].says_false) {
                if (hypothesis[person_id]) {
                    all_claims_are_correct = false;
                    break;
                }
            }
        }

        if (hypothesis[i]) {
            if (!all_claims_are_correct) {
                return false;
            }
        } else {
            if (all_claims_are_correct) {
                return false;
            }
        }
    }

    return true;
}

bool solve(int person_idx) {
    if (person_idx > N) {
        if (check()) {
            for (int i = 1; i <= N; ++i) {
                std::cout << (hypothesis[i] ? "true\n" : "false\n");
            }
            return true;
        }
        return false;
    }

    hypothesis[person_idx] = true;
    if (solve(person_idx + 1)) {
        return true;
    }

    hypothesis[person_idx] = false;
    if (solve(person_idx + 1)) {
        return true;
    }
    return false;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::cin >> N;

    statements.resize(N + 1);
    hypothesis.resize(N + 1);

    for (int i = 1; i <= N; ++i) {
        int k;
        std::cin >> k;
        statements[i].says_true.resize(k);
        for (int j = 0; j < k; ++j) {
            std::cin >> statements[i].says_true[j];
        }

        int l;
        std::cin >> l;
        statements[i].says_false.resize(l);
        for (int j = 0; j < l; ++j) {
            std::cin >> statements[i].says_false[j];
        }
    }

    solve(1);

    return 0;
}