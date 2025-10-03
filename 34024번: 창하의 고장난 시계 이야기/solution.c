#include <stdio.h>
#include <stdint.h>

static inline int64_t mod360(int64_t x) {
    x %= 360;
    if (x < 0) x += 360;
    return x;
}

int main(void) {
    int64_t H, M;
    scanf("%lld %lld", &H, &M);
    int64_t N;
    scanf("%lld", &N);

    int64_t h_ang = 30 * H;
    int64_t m_ang = 6 * M;

    int64_t r = mod360(m_ang - h_ang);
    int64_t t1 = (360 - r) / 6;

    if (N < t1) {
        m_ang = mod360(m_ang + 12 * N);
        h_ang = mod360(h_ang + 6 * N);
    } else {
        N -= t1;
        h_ang = mod360(h_ang + 6 * t1);
        m_ang = h_ang;

        int64_t cycles = N / 80;
        int64_t inc = (120 * cycles) % 360;
        h_ang = mod360(h_ang + inc);
        m_ang = h_ang;
        N %= 80;

        int dir = -1;
        while (N > 0) {
            int64_t seg = (dir == -1 ? 20 : 60);
            int64_t step = (N >= seg ? seg : N);
            m_ang = mod360(m_ang + dir * 12 * step);
            h_ang = mod360(h_ang + 6 * step);
            N -= step;
            if (step == seg) dir = -dir;
        }
    }

    int h = (int)(h_ang / 30) % 12;
    int m = (int)(m_ang / 6) % 60;
    printf("%d %d\n", h, m);
    return 0;
}