#include <iostream>
#include <array>
#include <vector>
#include <algorithm>

using namespace std;

enum Direction { TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3 };

static inline int get_opposite(int d) {
    return (d + 2) & 3;
}

array<int, 4> rotate_tile(const array<int, 4>& original, int k) {
    array<int, 4> rotated{};
    for (int i = 0; i < 4; i++) {
        int src_idx = (i - k) & 3;
        rotated[i] = original[src_idx];
    }
    return rotated;
}

bool is_valid_configuration(const array<array<int, 4>, 3>& current_tiles) {
    static const array<pair<int, int>, 6> connection_points = {{
        {0,1},{1,2},{2,3},{3,0},{0,2},{1,3}
    }};

    for (int center_idx = 0; center_idx < 3; center_idx++) {
        array<int, 2> neighbors{};
        int t = 0;
        for (int i = 0; i < 3; i++) {
            if (i != center_idx) neighbors[t++] = i;
        }

        const auto& center_tile = current_tiles[center_idx];
        const auto& n1_tile = current_tiles[neighbors[0]];
        const auto& n2_tile = current_tiles[neighbors[1]];
        
        for (auto [edge1, edge2] : connection_points) {
            bool match1 = (center_tile[edge1] == n1_tile[get_opposite(edge1)]) &&
                          (center_tile[edge2] == n2_tile[get_opposite(edge2)]);

            bool match2 = (center_tile[edge1] == n2_tile[get_opposite(edge1)]) &&
                          (center_tile[edge2] == n1_tile[get_opposite(edge2)]);

            if (match1 || match2) return true;
        }
    }

    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    array<array<int, 4>, 3> base_tiles;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) cin >> base_tiles[i][j];
    }

    array<array<array<int, 4>, 4>, 3> all_rotations{};
    for (int tile = 0; tile < 3; tile++) {
        for (int rot = 0; rot < 4; rot++) {
            all_rotations[tile][rot] = rotate_tile(base_tiles[tile], rot);
        }
    }

    for (int r0 = 0; r0 < 4; r0++) {
        for (int r1 = 0; r1 < 4; r1++) {
            for (int r2 = 0; r2 < 4; r2++) {
                array<array<int, 4>, 3> current_config = {
                    all_rotations[0][r0],
                    all_rotations[1][r1],
                    all_rotations[2][r2]
                };

                if (is_valid_configuration(current_config)) {
                    cout << "Yes\n";
                    return 0;
                }
            }
        }
    }

    cout << "No\n";
    return 0;
}