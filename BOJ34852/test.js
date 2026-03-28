'use strict';

const fs = require('fs');
const path = require('path');

const MAX_N = 1_000_000;
const DEFAULT_CASE_COUNT = 30;
const DEFAULT_SEED = 123456789;
const DEFAULT_DIR = './tests';

const caseCount = parseInt(process.argv[2] || DEFAULT_CASE_COUNT, 10);
const seedInput = parseInt(process.argv[3] || DEFAULT_SEED, 10);
const outDir = process.argv[4] || DEFAULT_DIR;

class RNG {
    constructor(seed) {
        this.x = seed >>> 0;
        if (this.x === 0) this.x = 123456789;
    }

    nextU32() {
        let x = this.x;
        x ^= x << 13;
        x ^= x >>> 17;
        x ^= x << 5;
        this.x = x >>> 0;
        return this.x;
    }

    nextFloat() {
        return this.nextU32() / 0x100000000;
    }

    nextInt(l, r) {
        return l + (this.nextU32() % (r - l + 1));
    }
}

function solve(N) {
    const c = 3 - (5 * Math.sqrt(3)) / 3;
    return N * N * c;
}

function formatOutput(value) {
    return value.toFixed(12) + '\n';
}

function formatInput(N) {
    return `${N}\n`;
}

function addCase(set, arr, N) {
    if (N < 1 || N > MAX_N) return;
    if (set.has(N)) return;
    set.add(N);
    arr.push(N);
}

function generateCases(targetCount, seed) {
    const rng = new RNG(seed);
    const used = new Set();
    const cases = [];

    const fixed = [
        1, 2, 3, 4, 5,
        6, 7, 8, 9, 10,
        11, 12, 16, 25, 32,
        50, 64, 99, 100, 101,
        127, 128, 255, 256, 257,
        511, 512, 513, 999, 1000, 1001,
        4095, 4096, 4097,
        9999, 10000, 10001,
        65535, 65536, 65537,
        99999, 100000, 100001,
        500000,
        999999, 1000000
    ];

    for (const n of fixed) {
        if (cases.length >= targetCount) break;
        addCase(used, cases, n);
    }

    while (cases.length < Math.min(targetCount, fixed.length + 8)) {
        const chooseHigh = rng.nextInt(0, 1) === 0;
        let n;
        if (chooseHigh) {
            n = MAX_N - rng.nextInt(0, 5000);
        } else {
            n = 1 + rng.nextInt(0, 5000);
        }

        addCase(used, cases, n);
    }

    while (cases.length < Math.min(targetCount, fixed.length + 16)) {
        addCase(used, cases, rng.nextInt(1, MAX_N));
    }

    while (cases.length < targetCount) {
        const x = rng.nextFloat();
        const val = Math.floor(Math.exp(Math.log(MAX_N) * x));
        addCase(used, cases, Math.max(1, Math.min(MAX_N, val)));
    }

    return cases.slice(0, targetCount);
}

function ensureDir(dir) {
    fs.mkdirSync(dir, { recursive: true });
}

function writeCases(dir, cases) {
    ensureDir(dir);

    for (let i = 0; i < cases.length; i++) {
        const idx = String(i + 1).padStart(2, '0');
        const N = cases[i];
        const inputPath = path.join(dir, `${idx}.in`);
        const outputPath = path.join(dir, `${idx}.out`);

        fs.writeFileSync(inputPath, formatInput(N), 'utf8');
        fs.writeFileSync(outputPath, formatOutput(solve(N)), 'utf8');
    }

    const summary = cases
        .map((n, i) => `${String(i + 1).padStart(2, '0')}: N=${n}`)
        .join('\n') + '\n';

    fs.writeFileSync(path.join(dir, `summary.txt`), summary, 'utf8');
}

(function main() {
    if (!Number.isInteger(caseCount) || caseCount <= 0) {
        console.error('caseCount must be a positive integer.');
        process.exit(1);
    }

    const cases = generateCases(caseCount, seedInput);
    writeCases(outDir, cases);

    console.log(`Generated ${cases.length} testcases in: ${outDir}`);
})();