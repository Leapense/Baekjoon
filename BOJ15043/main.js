const fs = require('fs');
const names = [
    'do', 'do#', 're', 're#', 'mi', 'fa', 'fa#', 'sol', 'sol#', 'la', 'la#', 'si'
];

const majorOffsets = [0, 2, 4, 5, 7, 9, 11];

const scaleMasks = new Int32Array(12);
for (let root = 0; root < 12; root++) {
    let mask = 0;
    for (let i = 0; i < 7; i++) {
        mask |= (1 << ((root + majorOffsets[i]) % 12));
    }

    scaleMasks[root] = mask;
}

const buffer = fs.readFileSync(0);
let offset = 0;

function nextInt() {
    while (offset < buffer.length && buffer[offset] <= 32) {
        offset++;
    }

    if (offset >= buffer.length) return null;

    let res = 0;
    while (offset < buffer.length && buffer[offset] > 32) {
        res = res * 10 + (buffer[offset] - 48);
        offset++;
    }

    return res;
}

const N = nextInt();
if (N === null) process.exit(0);

let playedMask = 0;
for (let i = 0; i < N; i++) {
    const note = nextInt();
    if (note !== null) {
        playedMask |= (1 << ((note - 1) % 12));
    }
}

for (let root = 0; root < 12; root++) {
    if ((playedMask & ~scaleMasks[root]) === 0) {
        process.stdout.write(names[root]);
        process.exit(0);
    }
}

process.stdout.write('desafinado');
process.exit(0);