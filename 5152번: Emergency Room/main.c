#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

typedef struct {
    int patient, arrival, departure, seat;
} Visit;

typedef struct {
    int time, type, idx;
} Event;

int compare_events(const void* a, const void* b) {
    const Event* ea = (const Event*)a;
    const Event* eb = (const Event*)b;
    if (ea->time != eb->time) return ea->time - eb->time;
    return eb->type - ea->type;
}

#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define MIN(a, b) ((a) < (b) ? (a) : (b))

int main(void) {
    int K;
    scanf("%d", &K);

    for (int ds = 1; ds <= K; ds++) {
        int P, S, V;
        scanf("%d %d %d", &P, &S, &V);

        double* seatX = malloc(S * sizeof(double));
        double* seatY = malloc(S * sizeof(double));
        for (int i = 0; i < S; i++) {
            scanf("%lf %lf", &seatX[i], &seatY[i]);
        }

        Visit* visits = malloc(V * sizeof(Visit));
        for (int i = 0; i < V; i++) {
            scanf("%d %d %d", &visits[i].patient, &visits[i].arrival, &visits[i].departure);
            visits[i].patient--;
            visits[i].seat = -1;
        }

        Event* events = malloc(2 * V * sizeof(Event));
        for (int i = 0; i < V; i++) {
            events[2 * i] = (Event){visits[i].arrival, 0, i};
            events[2 * i + 1] = (Event){visits[i].departure, 1, i};
        }
        qsort(events, 2 * V, sizeof(Event), compare_events);

        bool* seatOccupied = calloc(S, sizeof(bool));

        for (int e = 0; e < 2 * V; e++) {
            int type = events[e].type;
            int idx = events[e].idx;

            if (type == 1) {
                seatOccupied[visits[idx].seat] = false;
            } else {
                for (int s = 0; s < S; s++) {
                    if (!seatOccupied[s]) {
                        visits[idx].seat = s;
                        seatOccupied[s] = true;
                        break;
                    }
                }
            }
        }

        long long* infectedTime = malloc(P * sizeof(long long));
        for (int i = 0; i < P; i++) {
            infectedTime[i] = LLONG_MAX;
        }
        infectedTime[0] = -1440;

        bool changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < V; i++) {
                int pi = visits[i].patient;
                if (infectedTime[pi] == LLONG_MAX) continue;

                for (int j = 0; j < V; j++) {
                    if (i == j) continue;
                    int pj = visits[j].patient;
                    if (pi == pj) continue;

                    int si = visits[i].seat;
                    int sj = visits[j].seat;
                    double dx = seatX[si] - seatX[sj];
                    double dy = seatY[si] - seatY[sj];
                    if (dx * dx + dy * dy > 4.0) continue;

                    long long start = MAX(visits[i].arrival, visits[j].arrival);
                    long long end = MIN(visits[i].departure, visits[j].departure);

                    if (end <= start) continue;

                    long long infectiousFrom = infectedTime[pi] + 1440;
                    long long effectiveStart = MAX(start, infectiousFrom);

                    if (end - effectiveStart >= 20) {
                        long long newInfectionTime = effectiveStart + 20;
                        if (newInfectionTime < infectedTime[pj]) {
                            infectedTime[pj] = newInfectionTime;
                            changed = true;
                        }
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < P; i++) {
            if (infectedTime[i] != LLONG_MAX) count++;
        }

        printf("Data Set %d:\n%d\n\n", ds, count);
        free(seatX);
        free(seatY);
        free(visits);
        free(events);
        free(seatOccupied);
        free(infectedTime);
    }
    return 0;
}