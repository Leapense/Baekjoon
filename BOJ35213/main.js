const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim();
const n = Number(input);

const MAXV = 21;
const cubes = Array.from({ length: MAXV + 1 }, (_, i) => i * i * i);

const prev = Array.from({ length: 10 }, () => new Int16Array(n + 1));
const val = Array.from({ length: 10 }, () => new Int8Array(n + 1));
for (let step = 0; step <= 9; step++) prev[step].fill(-1);

let reachable = new Uint8Array(n + 1);
reachable[0] = 1;

let foundStep = -1;

for (let step = 1; step <= 9; step++) {
    const nextReachable = new Uint8Array(n + 1);

    for (let s = 0; s <= n; s++) {
        if (!reachable[s]) continue;

        for (let v = 0; v <= MAXV; v++) {
            const ns = s + cubes[v];
            if (ns > n) continue;
            if (nextReachable[ns]) continue;

            nextReachable[ns] = 1;
            prev[step][ns] = s;
            val[step][ns] = v;
        }
    }

    if (nextReachable[n]) {
        foundStep = step;
        reachable = nextReachable;
        break;
    }

    reachable = nextReachable;
}

let seq = [];
let cur = n;
for (let step = foundStep; step >= 1; step--) {
    const v = val[step][cur];
    seq.push(v);
    cur = prev[step][cur];
}

seq.reverse();
seq = seq.filter(x => x !== 0);

console.log(seq.length);
process.stdout.write(seq.join(" "));
process.exit(0);