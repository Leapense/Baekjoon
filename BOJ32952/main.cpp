#include <cstdio>

const int BUF_SIZE = 1 << 20;
static char in_buf[BUF_SIZE];
static int in_idx = 0;
static int in_len = 0;

static char out_buf[BUF_SIZE];
static int out_idx = 0;

inline char readChar() {
    if (in_idx == in_len) {
        in_len = fread(in_buf, 1, BUF_SIZE, stdin);
        in_idx = 0;
        if (in_len == 0) return EOF;
    }
    return in_buf[in_idx++];
}

inline void writeChar(char c) {
    if (out_idx == BUF_SIZE) {
        fwrite(out_buf, 1, BUF_SIZE, stdout);
        out_idx = 0;
    }
    out_buf[out_idx++] = c;
}

inline unsigned long long readULL() {
    unsigned long long sum = 0;
    char c = readChar();
    while (c != EOF && (c < '0' || c > '9')) c = readChar();
    while (c >= '0' && c <= '9') {
        sum = sum * 10 + (c - '0');
        c = readChar();
    }

    return sum;
}

inline void writeULL(unsigned long long n) {
    if (n == 0) {
        writeChar('0');
        return;
    }
    char temp[21];
    int t_idx = 0;
    while (n > 0) {
        temp[t_idx++] = (n % 10) + '0';
        n /= 10;
    }
    while (t_idx--) writeChar(temp[t_idx]);
}

inline void flushOut() {
    if (out_idx > 0) {
        fwrite(out_buf, 1, out_idx, stdout);
        out_idx = 0;
    }
}

int main() {
    unsigned long long R = readULL();
    unsigned long long K = readULL();
    unsigned long long M = readULL();
    
    unsigned long long h = M / K;
    unsigned long long ans = (h >= 64) ? 0 : (R >> h);

    writeULL(ans);
    writeChar('\n');
    flushOut();
    return 0;
}