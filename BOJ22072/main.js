'use strict';

const fs = require('fs');
const raw = fs.readFileSync(0, 'utf8');
const input = raw.trim().length === 0 ? [] : raw.trim().split(/\s+/);

let idx = 0;
const out = [];

const MAX_TESTS = 100000;
const MAX_DIGITS_PER_OPERAND = 200000;
const MAX_PAIRWISE_OPERATIONS = 5_000_000;

function fail(message) {
    process.stderr.write(`${message}\n`);
    process.exit(1);
}

function readToken() {
    if (idx >= input.length) {
        fail('Malformed input: missing token.');
    }
    return input[idx++];
}

function isDecimalString(value) {
    return typeof value === 'string' && /^[0-9]+$/.test(value);
}

function toReversedDigits(s) {
    const a = new Array(s.length);
    for (let i = 0; i < s.length; i++) {
        a[s.length - 1 - i] = s.charCodeAt(i) - 48;
    }

    return a;
}

if (input.length === 0) {
    fail('Malformed input: empty input.');
}

const tToken = readToken();
if (!/^(0|[1-9][0-9]*)$/.test(tToken)) {
    fail('Malformed input: invalid test count.');
}

const t = Number(tToken);
if (!Number.isSafeInteger(t) || t < 0 || t > MAX_TESTS) {
    fail('Malformed input: test count out of bounds.');
}

for (let tc = 0; tc < t; tc++) {
    const x = readToken();
    const y = readToken();
    const z = readToken();

    if (!isDecimalString(x) || !isDecimalString(y) || !isDecimalString(z)) {
        fail(`Malformed input in test case ${tc + 1}: operands must be decimal strings.`);
    }

    if (x.length > MAX_DIGITS_PER_OPERAND || y.length > MAX_DIGITS_PER_OPERAND || z.length > MAX_DIGITS_PER_OPERAND * 2) {
        fail(`Input too large in test case ${tc + 1}.`);
    }

    if (x.length * y.length > MAX_PAIRWISE_OPERATIONS) {
        fail(`Computation too expensive in test case ${tc + 1}.`);
    }

    const a = toReversedDigits(x);
    const b = toReversedDigits(y);
    const c = toReversedDigits(z);

    const expectedLength = a.length + b.length - 1;
    let ok = expectedLength === c.length;

    if (ok) {
        const prod = new Array(expectedLength).fill(0);

        for (let i = 0; i < a.length; i++) {
            for (let j = 0; j < b.length; j++) {
                prod[i + j] += a[i] * b[j];
            }
        }

        for (let i = 0; i < prod.length; i++) {
            if (prod[i] !== c[i]) {
                ok = false;
                break;
            }
        }
    }

    out.push(ok ? 'Infinity' : 'Finite');
}

if (idx !== input.length) {
    fail('Malformed input: extra trailing tokens detected.');
}

process.stdout.write(`${out.join('\n')}`);
process.exit(0);