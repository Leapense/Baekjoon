#include <iostream>
using namespace std;

using int64 = long long;

namespace {
    constexpr char DIR_CLOCKWISE = 'C';
    constexpr char DIR_ANTICLOCKWISE = 'A';
    constexpr char DIR_UNKNOWN = '?';

    struct SimulationState {
        int64 position = 0;
        char lastDirection = DIR_UNKNOWN;
        int64 score = 0;
    };

    void updateRememberedDirectionBeforeRun(
        SimulationState& state,
        char currentDirection,
        bool isFirstRun
    ) {
        if (isFirstRun) {
            state.lastDirection = currentDirection;
            return;
        }

        if (state.position == 0 && currentDirection != state.lastDirection) {
            state.lastDirection = currentDirection;
        }
    }

    int64 countTouches(
        int64 trackLength,
        int64 position,
        int64 distance,
        char direction
    ) {
        if (direction == DIR_CLOCKWISE) {
            return (position + distance) / trackLength;
        }

        return (distance + ((trackLength - position) % trackLength)) / trackLength;
    }

    int64 nextPosition(
        int64 trackLength,
        int64 position,
        int64 distance,
        char direction
    ) {
        if (direction == DIR_CLOCKWISE) {
            return (position + distance) % trackLength;
        }

        return (position - (distance % trackLength) + trackLength) % trackLength;
    }

    void applyScore(
        SimulationState& state,
        int64 touches,
        char currentDirection
    ) {
        if (touches <= 0) {
            return;
        }

        if (state.lastDirection == currentDirection) {
            state.score += touches;
        } else {
            state.score += touches - 1;
            state.lastDirection = currentDirection;
        }
    }

    int64 solveCase(int64 trackLength, int runCount) {
        SimulationState state;
        for (int i = 0; i < runCount; ++i) {
            int64 distance;
            char direction;
            cin >> distance >> direction;

            updateRememberedDirectionBeforeRun(state, direction, i == 0);

            const int64 touches = countTouches(
                trackLength,
                state.position,
                distance,
                direction
            );

            state.position = nextPosition(
                trackLength,
                state.position,
                distance,
                direction
            );

            applyScore(state, touches, direction);
        }

        return state.score;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int testCaseCount;
    cin >> testCaseCount;

    for (int tc = 1; tc <= testCaseCount; ++tc) {
        int64 trackLength;
        int runCount;
        cin >> trackLength >> runCount;

        const int64 answer = solveCase(trackLength, runCount);
        cout << "Case #" << tc << ": " << answer << '\n';
    }

    return 0;
}