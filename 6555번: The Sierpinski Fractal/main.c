#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static void put_char(char *grid, int W, int r, int c, char ch) {
    grid[r * W + c] = ch;
}

static void draw(int r, int c, int h, char* grid, int W) {
    if (h == 2) {
        put_char(grid, W, r, c + 1, '/');
        put_char(grid, W, r, c + 2, '\\');
        put_char(grid, W, r + 1, c, '/');
        put_char(grid, W, r + 1, c + 1, '_');
        put_char(grid, W, r + 1, c + 2, '_');
        put_char(grid, W, r + 1, c + 3, '\\');
        return;
    }
    
    int half = h / 2;
    draw(r, c + half, h / 2, grid, W);
    draw(r + half, c, h / 2, grid, W);
    draw(r + half, c + h, h / 2, grid, W);
}

int main(void) {
    int n;
    while (scanf("%d", &n) == 1) {
        if (n == 0) break;
        int H = 1 << n;
        int W = 2 * H;
        
        char *grid = (char *)malloc((size_t)H * (size_t)W);
        if (!grid) return 1;
        memset(grid, ' ', (size_t)H * (size_t)W);
        draw(0, 0, H, grid, W);
        
        for (int r = 0; r < H; r++) {
            int last = W - 1;
            while (last >= 0 && grid[r * W + last] == ' ') last--;
            if (last >= 0) {
                fwrite(&grid[r * W], 1, (size_t)last + 1, stdout);
            }
            fputc('\n', stdout);
        }
        fputc('\n', stdout);
        free(grid);
    }
    
    return 0;
}