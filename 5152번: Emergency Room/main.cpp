#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <tuple>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int K;
    cin >> K;

    for (int ds = 1; ds <= K; ds++) {
        int P, S, V;
        cin >> P >> S >> V;

        vector<pair<double, double>> seats(S);
        for (int i = 0; i < S; i++) {
            cin >> seats[i].first >> seats[i].second;
        }

        auto isClose = [&](int s1, int s2) -> bool {
            double dx = seats[s1].first - seats[s2].first;
            double dy = seats[s1].second - seats[s2].second;
            return dx * dx + dy * dy <= 4.0;
        };

        struct Visit {
            int patient, arrival, departure, seat;
        };

        vector<Visit> visits(V);

        for (int i = 0; i < V; i++) {
            cin >> visits[i].patient >> visits[i].arrival >> visits[i].departure;
            visits[i].patient--;
            visits[i].seat = -1;
        }

        vector<tuple<int, int, int>> events;
        for (int i = 0; i < V; i++) {
            events.emplace_back(visits[i].arrival, 0, i);
            events.emplace_back(visits[i].departure, 1, i);
        }

        sort(events.begin(), events.end(), [](const auto& a, const auto& b) {
            if (get<0>(a) != get<0>(b)) return get<0>(a) < get<0>(b);
            return get<1>(a) > get<1>(b);
        });

        vector<bool> seatOccupied(S, false);
        for (const auto& [time, type, idx] : events) {
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

        constexpr long long INF = LLONG_MAX;
        vector<long long> infectedTime(P, INF);
        infectedTime[0] = -1440;

        bool changed = true;
        while (changed) {
            changed = false;

            for (int i = 0; i < V; i++) {
                int pi = visits[i].patient;
                if (infectedTime[pi] == INF) continue;

                for (int j = 0; j < V; j++) {
                    if (i == j) continue;
                    int pj = visits[j].patient;
                    if (pi == pj) continue;

                    if (!isClose(visits[i].seat, visits[j].seat)) continue;
                    long long start = max(visits[i].arrival, visits[j].arrival);
                    long long end = min(visits[i].departure, visits[j].departure);

                    if (end <= start) continue;

                    long long infectiousFrom = infectedTime[pi] + 1440;
                    long long effectiveStart = max(start, infectiousFrom);

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
            if (infectedTime[i] != INF) count++;
        }

        cout << "Data Set " << ds << ":\n" << count << "\n\n";
    }

    return 0;
}