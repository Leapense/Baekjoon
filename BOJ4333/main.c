#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <string.h>

static const char *NAMES[12] = {"C",  "C#", "D",  "D#", "E",  "F",
                                "F#", "G",  "G#", "A",  "A#", "B"};

static int note_index(const char *s) {
  for (int i = 0; i < 12; i++) {
    if (strcmp(s, NAMES[i]) == 0)
      return i;
  }
  return -1;
}

int main(void) {
  unsigned short scale_mask[12];

  const int steps[7] = {0, 2, 4, 5, 7, 9, 11};
  for (int r = 0; r < 12; r++) {
    unsigned short m = 0;
    for (int k = 0; k < 7; k++) {
      int p = (r + steps[k]) % 12;
      m |= (unsigned short)(1u << p);
    }
    scale_mask[r] = m;
  }

  char line[2048];
  while (fgets(line, (int)sizeof(line), stdin)) {
    size_t n = strlen(line);
    while (n > 0 && (line[n - 1] == '\n' || line[n - 1] == '\r')) {
      line[--n] = '\0';
    }

    if (strcmp(line, "END") == 0)
      break;

    unsigned short used = 0;
    char *save = NULL;
    char *tok = strtok_r(line, " \t", &save);
    while (tok) {
      int idx = note_index(tok);
      if (idx >= 0)
        used |= (unsigned short)(1u << idx);
      tok = strtok_r(NULL, " \t", &save);
    }

    int first = 1;
    for (int r = 0; r < 12; r++) {
      if ((used & (unsigned short)~scale_mask[r]) == 0) {
        if (!first)
          putchar(' ');
        fputs(NAMES[r], stdout);
        first = 0;
      }
    }
    putchar('\n');
  }

  return 0;
}