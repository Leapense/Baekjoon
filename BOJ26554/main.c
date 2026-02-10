#include <stdio.h>
#include <string.h>
#include <stdlib.h>

static void print_chars(char ch, int cnt) {
    for (int i = 0; i < cnt; i++) putchar(ch);
}

static void draw_rectangle(int r, int c, char fill) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (fill == 'y') {
                putchar('#');
            } else {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1) putchar('#');
                else putchar(' ');
            }
        }

        putchar('\n');
    }
}

static void draw_left_triangle(int s, char fill) {
    for (int i = 1; i <= s; i++) {
        if (fill == 'y') {
            print_chars('#', 1);
        } else {
            if (i == 1) {
                putchar('#');
            } else if (i == s) {
                print_chars('#', s);
            } else {
                putchar('#');
                print_chars(' ', i - 2);
                putchar('#');
            }
        }

        putchar('\n');
    }
}

static void draw_right_triangle(int s, char fill) {
    for (int i = 1; i <= s; i++) {
        int lead = s - i;
        print_chars(' ', lead);

        if (fill == 'y') {
            print_chars('#', i);
        } else {
            if (i == 1) {
                putchar('#');
            } else if (i == s) {
                print_chars('#', s);
            } else {
                putchar('#');
                print_chars(' ', i - 2);
                putchar('#');
            }
        }

        putchar('\n');
    }
}

static void draw_diamond(int s, char fill) {
    int mid = s / 2;
    for (int r = 0; r < s; r++) {
        int dist = mid - r;
        if (dist < 0) dist = -dist;
        int w = s - 2 * dist;

        print_chars(' ', dist);
        if (fill == 'y') {
            print_chars('#', w);
        } else {
            if (w == 1) {
                putchar('#');
            } else {
                putchar('#');
                print_chars(' ', w - 2);
                putchar('#');
            }
        }

        putchar('\n');
    }
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;
    
    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) {}

    char line[256];
    for (int t = 0; t < n; t++) {
        if (!fgets(line, sizeof(line), stdin)) break;
        size_t L = strlen(line);
        if (L && line[L - 1] == '\n') line[L - 1] = '\0';

        int r, c, s;
        char fill;

        if (strncmp(line, "rectangle", 9) == 0) {
            if (sscanf(line, "rectangle %d %d %c", &r, &c, &fill) == 3) {
                draw_rectangle(r, c, fill);
            }
        } else if (strncmp(line, "left triangle", 13) == 0) {
            if (sscanf(line, "left triangle %d %c", &s, &fill) == 2) {
                draw_left_triangle(s, fill);
            }
        } else if (strncmp(line, "right triangle", 14) == 0) {
            if (sscanf(line, "right triangle %d %c", &s, &fill) == 2) {
                draw_right_triangle(s, fill);
            }
        } else if (strncmp(line, "diamond", 7) == 0) {
            if (sscanf(line, "diamond %d %c", &s, &fill) == 2) {
                draw_diamond(s, fill);
            }
        }
    }

    return 0;
}