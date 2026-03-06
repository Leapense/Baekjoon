#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>

static int64_t max64(int64_t a, int64_t b) {
    return (a > b) ? a : b;
}

static int64_t next_even(int64_t value) {
    return (value % 2 == 0) ? value : value + 1;
}

static int64_t prev_even(int64_t value) {
    return (value % 2 == 0) ? value : (value - 1);
}

static int64_t sum_even_from_4_to_bounded_range(int64_t a, int64_t b) {
    int64_t lower_bound = max64(a, 4);
    int64_t first_even = next_even(lower_bound);
    int64_t last_even = prev_even(b);

    if (first_even > last_even) {
        return 0;
    }

    int64_t count = (last_even - first_even) / 2 + 1;
    int64_t pair_sum = first_even + last_even;

    if (count % 2 == 0) {
        return (count / 2) * pair_sum;
    }

    return count * (pair_sum / 2);
}

int main(void) {
    int64_t a, b;
    if (scanf("%" SCNd64 " %" SCNd64, &a, &b) != 2) {
        return 0;
    }

    printf("%" PRId64 "\n", sum_even_from_4_to_bounded_range(a, b));
    return 0;
}