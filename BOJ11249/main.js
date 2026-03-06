'use strict';

const fs = require('fs');

function fail(message) {
    process.stderr.write(`Input error: ${message}\n`);
    process.exit(1);
}

function isIntegerToken(token) {
    return typeof token === 'string' && /^-?\d+$/.test(token);
}

function safeBigInt(token, name) {
    if (!isIntegerToken(token)) {
        fail(`Invalid ${name}: ${String(token)}`);
    }

    try {
        return BigInt(token);
    } catch {
        fail(`Failed to parse ${name}: ${String(token)}`);
    }
}

function mod(a, m) {
    if (m === 0n) {
        fail('N must not be 0');
    }

    return ((a % m) + m) % m;
}

const raw = fs.readFileSync(0, 'utf8').trim();
if (raw.length === 0) {
    fail('Empty input');
}

const input = raw.split(/\s+/);
let idx = 0;

const kToken = input[idx++];
if (!isIntegerToken(kToken)) {
    fail('K must be an integer');
}

const K = Number(kToken);
if (!Number.isSafeInteger(K) || K < 0) {
    fail('K must be a non-negative safe integer');
}

if (input.length !== 1 + K * 3) {
    fail(`Expected ${1 + K * 3} tokens, received ${input.length}`);
}

const out = [];

for (let tc = 0; tc < K; tc++) {
    const N = safeBigInt(input[idx++], `N at test case ${tc + 1}`);
    const R = safeBigInt(input[idx++], `R at test case ${tc + 1}`);
    const T = safeBigInt(input[idx++], `T at test case ${tc + 1}`);

    if (N === 0n) {
        fail(`N must not be 0 at test case ${tc + 1}`);
    }

    if (R === T) {
        out.push('0');
        continue;
    }

    const diff = T - R;

    let a = mod(diff, N);
    let b = mod(diff + 1n, N);
    let c = mod(diff + 2n, N);

    if (a === 0n) a = N;
    if (b === 0n) b = N;
    if (c === 0n) c = N;

    let ans = a;
    if (b < ans) ans = b;
    if (c < ans) ans = c;

    out.push(String(ans));
}

process.stdout.write(out.join('\n'));
process.exit(0);