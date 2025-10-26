#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct {
    int* says_true;
    int count_true;
    int* says_false;
    int count_false;
} Statement;

int N;
Statement* statements;
bool* hypothesis;

void cleanup() {
    for (int i = 1; i <= N; ++i) {
        free(statements[i].says_true);
        free(statements[i].says_false);
    }
    free(statements);
    free(hypothesis);
}

bool check() {
    for (int i = 1; i <= N; ++i) {
        bool all_claims_are_correct = true;

        for (int j = 0; j < statements[i].count_true; ++j) {
            int person_id = statements[i].says_true[j];
            if (!hypothesis[person_id]) {
                all_claims_are_correct = false;
                break;
            }
        }

        if (all_claims_are_correct) {
            for (int j = 0; j < statements[i].count_false; ++j) {
                int person_id = statements[i].says_false[j];
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
                printf("%s\n", hypothesis[i] ? "true" : "false");
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
    scanf("%d", &N);
    statements = (Statement*)malloc((N + 1) * sizeof(Statement));
    hypothesis = (bool*)malloc((N + 1) * sizeof(bool));

    for (int i = 1; i <= N; ++i) {
        scanf("%d", &statements[i].count_true);
        statements[i].says_true = (int*)malloc(statements[i].count_true * sizeof(int));
        for (int j = 0; j < statements[i].count_true; ++j) {
            scanf("%d", &statements[i].says_true[j]);
        }

        scanf("%d", &statements[i].count_false);
        statements[i].says_false = (int*)malloc(statements[i].count_false * sizeof(int));
        for (int j = 0; j < statements[i].count_false; ++j) {
            scanf("%d", &statements[i].says_false[j]);
        }
    }

    if (solve(1)) {
        cleanup();
    }

    return 0;
}