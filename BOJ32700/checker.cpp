#include "testlib.h"
#include <cassert>

using std::vector;
using std::pair;
using std::string;
using std::set;
using std::map;

enum SquareState {
  EMPTY = 0,
  WHITE = 1,
  RED = 2,
  BLUE = 3
};

SquareState to_square_state (char c) {
  switch (c) {
  case '.':
  case 'S':
    return EMPTY;
  case '#':
    return WHITE;
  case 'x':
    return RED;
  case 'o':
    return BLUE;
  default:
    assert(false);
  }
}

class Game {
  const int dx [4] = {1, 0, -1, 0};
  const int dy [4] = {0, 1, 0, -1};

  const int ddx [8] = {1, 1, 1, 0, 0, -1, -1, -1};
  const int ddy [8] = {-1, 0, 1, -1, 1, -1, 0, 1};
  
  int x, y;
  vector<vector<SquareState>> state;
  int n, m;
  int moves_made;
  vector<pair<int, int>> initial_blues;
  
  void dfs (int i, int j, int c, map<pair<int, int>, int> &component_id) {
    component_id[{i, j}] = c;

    for (int k = 0; k < 4; k++) {
      int ni = i + dx[k];
      int nj = j + dy[k];

      if (ni < 0 || ni >= n || nj < 0 || nj >= m)
        continue;

      if (state[ni][nj] == BLUE && component_id.count({ni, nj}) == 0) {
        dfs(ni, nj, c, component_id);
      }
    }
  }

  void remove_components () {
    map<pair<int, int>, int> component_id;
    
    int cur = 0;
    set<int> removable_components;
    for (auto pr : initial_blues) {
      int i = pr.first;
      int j = pr.second;

      if (state[i][j] == BLUE && component_id.count({i, j}) == 0) {
        removable_components.insert(cur);
        dfs(i, j, cur, component_id);
        cur++;
      }
    }
    
    // a component should be removed if and only if
    // for each of its balls, all of their 8 neighbors are also balls,
    // either white/red, or blue but only in the same component
    // eliminate all components that do not satisfy this
    for (auto pr : initial_blues) {
      int i = pr.first;
      int j = pr.second;

      if (state[i][j] != BLUE)
        continue;

      if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
        removable_components.erase(component_id[{i, j}]);
        continue;
      }

      for (int k = 0; k < 8; k++) {
        int ni = i + ddx[k];
        int nj = j + ddy[k];
        if (state[ni][nj] == EMPTY)
          removable_components.erase(component_id[{i, j}]);
        if (state[ni][nj] == BLUE && component_id[{ni, nj}] != component_id[{i, j}])
          removable_components.erase(component_id[{i, j}]);
      }
    }
    
    // now build the list of actual balls to remove
    vector<pair<int, int>> balls_to_remove;
    for (auto pr : initial_blues) {
      int i = pr.first;
      int j = pr.second;

      if (state[i][j] != BLUE)
        continue;

      if (!removable_components.count(component_id[{i, j}]))
        continue;

      balls_to_remove.emplace_back(i, j);
      for (int k = 0; k < 8; k++) {
        int ni = i + ddx[k];
        int nj = j + ddy[k];
        if (state[ni][nj] == RED)
          balls_to_remove.emplace_back(ni, nj);
      }
    }
    
    for (auto pr : balls_to_remove)
      state[pr.first][pr.second] = EMPTY;
  }

  void check_done () {
    for (auto pr : initial_blues) {
      int i = pr.first;
      int j = pr.second;

      if (state[i][j] == BLUE) {
        // not done yet
        return;
      }
    }

    // done!
    // the game ends immediately if we are done
    quitf(_ok, "OK, won after %d moves", moves_made);
  }

public:
  Game (int _x, int _y, vector<vector<SquareState>> _state) :
    x(_x), y(_y), state(_state),
    n(_state.size()),
    m(state[0].size()),
    moves_made(0),
    initial_blues(0) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (_state[i][j] == BLUE) {
          initial_blues.emplace_back(i, j);
        }
      }
    }
  }

  void move (int dir_x, int dir_y) {
    moves_made++;
    
    // first, leave the old square
    if (state[x][y] == BLUE) {
      state[x][y] = RED;

      // if we left an empty square, nothng changes; the game can't be complete as a result of that
      // thus it is sufficient to execute these (expensive) functions only when we leave a blue square
      // this also reduces the complexity to O(blue squares * blue squares * log)
      remove_components();
      check_done();
    }

    x += dir_x;
    y += dir_y;

    if (x >= n || x < 0)
      quitf(_wa, "stepped out of bounds");

    if (y >= m || y < 0)
      quitf(_wa, "stepped out of bounds");

    if (state[x][y] == RED || state[x][y] == WHITE)
      quitf(_wa, "stepped on a red or white ball");
  }
};

int main (int argc, char **argv) {
  if (argc < 3) {
    std::cerr << "usage: <input-file> <output-file>" << std::endl;
    return 1;
  } else if (argc == 3) {
    // this checker doesn't use the answer file
    // shortcut
    char *nargv [4] = {argv[0], argv[1], argv[2], argv[2]};
    registerTestlibCmd(4, nargv);
  } else {
    registerTestlibCmd(argc, argv);
  }

  int n = inf.readInt();
  int m = inf.readInt();

  int sx = -1, sy = -1;
  vector<vector<SquareState>> state (0);
  for (int i = 0; i < n; i++) {
    string row = inf.readToken();
    assert((int) row.size() == m);

    vector<SquareState> state_row (m);
    for (int j = 0; j < m; j++) {
      char c = row[j];
      state_row[j] = to_square_state(c);

      if (c == 'S') {
        assert(sx == -1 && sy == -1);
        sx = i;
        sy = j;
      }
    }

    state.push_back(state_row);
  }
  assert(sx != -1 && sy != -1);

  string sol = ouf.readToken();
  if ((int) sol.size() > 1'000'000) {
    quitf(_pe, "The output string should not be longer than 10^6 characters");
  }
  
  for (char c : sol) {
    if (c != 'L' && c != 'U' && c != 'D' && c != 'R') {
      quitf(_pe, "The output should not contain any characters other than LRUD");
    }
  }

  for (int i = 0; i < (int) sol.size() - 1; i++) {
    if (sol[i] == 'L' && sol[i + 1] == 'R')
      quitf(_wa, "Can't step L and then immediately R");
    if (sol[i] == 'R' && sol[i + 1] == 'L')
      quitf(_wa, "Can't step R and then immediately L");
    if (sol[i] == 'U' && sol[i + 1] == 'D')
      quitf(_wa, "Can't step U and then immediately D");
    if (sol[i] == 'D' && sol[i + 1] == 'U')
      quitf(_wa, "Can't step D and then immediately U");
  }

  Game game (sx, sy, state);
  for (char c : sol) {
    switch (c) {
    case 'L':
      game.move(0, -1);
      break;
    case 'R':
      game.move(0, 1);
      break;
    case 'U':
      game.move(-1, 0);
      break;
    case 'D':
      game.move(1, 0);
      break;
    default:
      assert(false);
    }
  }
  
  quitf(_wa, "Some blue balls remain");
}
