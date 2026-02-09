'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\r?\n/);
let idx = 0;

const n = parseInt(input[idx++], 10);

const freqStr = new Array(n);
const rel = new Array(n).fill(null);

let maxDigits = 0;
for (let i = 0; i < n; i++) {
    const line = input[idx++].trim();
    const parts = line.split(/\s+/);
    freqStr[i] = parts[0];

    const dotPos = freqStr[i].indexOf('.');
    if (dotPos !== -1) {
        maxDigits = Math.max(maxDigits, freqStr[i].length - dotPos - 1);
    }

    if (i >= 1) rel[i] = parts[1];
}

function pow10(k) {
    return 10n ** BigInt(k);
}

const P = maxDigits;
const D = 2n * pow10(P);
const Q = P + 1;

function parseScaled(s) {
    let sign = 1n;
    if (s[0] === '-') {
        sign = -1n;
        s = s.slice(1);
    }

    let ip = s, fp = '';
    const dot = s.indexOf('.');
    if (dot !== -1) {
        ip = s.slice(0, dot);
        fp = s.slice(dot + 1);
    }

    if (ip === '') ip = '0';
    const d = fp.length;

    const ipBig = BigInt(ip);
    const fpBig = fp === '' ? 0n : BigInt(fp);

    const base = ipBig * pow10(d) + fpBig;
    const factor = 2n * pow10(P - d);
    return sign * base * factor;
}

function formatScaled(v) {
    let w = v * 5n;
    const sign = w < 0n ? '-' : '';
    if (w < 0n) w = -w;

    let s = w.toString();
    if (Q === 0) return sign + s;
    if (s.length <= Q) s = s.padStart(Q + 1, '0');
    const intPart = s.slice(0, s.length - Q);
    let fracPart = s.slice(s.length - Q);

    fracPart = fracPart.replace(/0+$/, '');
    if (fracPart.length === 0) return sign + intPart + '.0';
    return sign + intPart + '.' + fracPart;
}

const f = freqStr.map(parseScaled);

let lo = parseScaled('30');
let hi = parseScaled('4000');

function maxBig(a, b) { return a > b ? a : b; }
function minBig(a, b) { return a < b ? a : b; }

for (let i = 1; i < n; i++) {
    const a = f[i - 1];
    const b = f[i];
    const word = rel[i];

    if (a === b) continue;
    const m = (a + b) / 2n;
    if (word === 'closer') {
        if (b > a) lo = maxBig(lo, m);
        else hi = minBig(hi, m);
    } else {
        if (b > a) hi = minBig(hi, m);
        else lo = maxBig(lo, m);
    }
}

process.stdout.write(`${formatScaled(lo)} ${formatScaled(hi)}`);
process.exit(0);