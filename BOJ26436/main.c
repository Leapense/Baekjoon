#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <stdbool.h>

enum {
    DIR_CLOCKWISE = 'C',
    DIR_ANTICLOCKWISE = 'A',
    DIR_UNKNOWN = '?'
};

typedef struct {
    int64_t position;
    char lastDirection;
    int64_t score;
} SimulationState;

static void initializeState(SimulationState *state) {
    state->position = 0;
    state->lastDirection = DIR_UNKNOWN;
    state->score = 0;
}

static void updateRememberedDirectionBeforeRun(
    SimulationState *state,
    char currentDirection,
    bool isFirstRun
) {
    if (isFirstRun) {
        state->lastDirection = currentDirection;
        return;
    }

    if (state->position == 0 && currentDirection != state->lastDirection) {
        state->lastDirection = currentDirection;
    }
}

static int64_t countStartLineTouches(
    int64_t trackLength,
    int64_t position,
    int64_t distance,
    char direction
) {
    if (direction == DIR_CLOCKWISE) {
        return (position + distance) / trackLength;
    }

    return (distance + ((trackLength - position) % trackLength)) / trackLength;
}

static int64_t computeNextPosition(
    int64_t trackLength,
    int64_t position,
    int64_t distance,
    char direction
) {
    if (direction == DIR_CLOCKWISE) {
        return (position + distance) % trackLength;
    }

    return (position - (distance % trackLength) + trackLength) % trackLength;
}

static void applyScoring(
    SimulationState *state,
    int64_t touches,
    char currentDirection
) {
    if (touches == 0) {
        return;
    }

    if (state->lastDirection == currentDirection) {
        state->score += touches;
        return;
    }

    state->score += touches - 1;
    state->lastDirection = currentDirection;
}

static int64_t solveCase(int64_t trackLength, int runCount) {
    SimulationState state;
    initializeState(&state);

    for (int i = 0; i < runCount; ++i) {
        int64_t distance;
        char direction;

        if (scanf("%" SCNd64 " %c", &distance, &direction) != 2) {
            return state.score;
        }

        updateRememberedDirectionBeforeRun(&state, direction, i == 0);

        int64_t touches = countStartLineTouches(
            trackLength,
            state.position,
            distance,
            direction
        );

        state.position = computeNextPosition(
            trackLength,
            state.position,
            distance,
            direction
        );

        applyScoring(&state, touches, direction);
    }
    return state.score;
}

int main(void) {
    int testCaseCount;
    if (scanf("%d", &testCaseCount) != 1) {
        return 0;
    }

    for (int tc = 1; tc <= testCaseCount; ++tc) {
        int64_t trackLength;
        int runCount;

        if (scanf("%" SCNd64 " %d", &trackLength, &runCount) != 2) {
            return 0;
        }

        int64_t answer = solveCase(trackLength, runCount);
        printf("Case #%d: %" PRId64 "\n", tc, answer);
    }

    return 0;
}