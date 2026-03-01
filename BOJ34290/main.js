"use strict";

const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trimEnd().split("\n");
const [p, t] = input[0].trim().split(/\s+/).map(Number);
const s = input[1].trim();
const n = s.length;

const INF = 1e9;
const dist = new Array(n).fill(INF);

for (let i = 0; i < n; i++) {
    if (s[i] === "I") dist[i] = 0;
    else if (s[i] === "W") dist[i] = -1;
}

let last = -1;

for (let i = 0; i < n; i++) {
    if (s[i] === "W") {
        last = -1;
        continue;
    }

    if (s[i] === "I") last = i;
    if (last !== -1) dist[i] = Math.min(dist[i], i - last);
}

last = -1;
for (let i = n - 1; i >= 0; i--) {
    if (s[i] === "W") {
        last = -1;
        continue;
    }

    if (s[i] === "I") last = i;
    if (last !== -1) dist[i] = Math.min(dist[i], last - i);
}

let D = 0;
for (let i = 0; i < n; i++) {
    if (s[i] === "W") continue;
    if (dist[i] >= INF / 2) {
        process.stdout.write("CURED");
        process.exit(0);
    }

    if (dist[i] > D) D = dist[i];
}

const k = Math.floor((t - 1) / p);
process.stdout.write(D <= k ? "ALL INFECTED" : "CURED");
process.exit(0);