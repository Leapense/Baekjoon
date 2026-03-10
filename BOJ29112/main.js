'use strict';

const MAX_INPUT_BYTES = 1024 * 1024;
const INTEGER_RE = /^[+-]?\d+$/;
const BASE = 36000n;

function pow6(x) {
    const x2 = x * x;
    const x4 = x2 * x2;
    return x4 * x2;
}

function floorSixthRoot(n) {
    if (n < 0n) return 0n;
    if (n <= 1n) return n;

    let lo = 0n;
    let hi = 1n;

    while (pow6(hi) <= n) {
        hi <<= 1n;
    }

    while (lo + 1n < hi) {
        const mid = (lo + hi) >> 1n;
        if (pow6(mid) <= n) {
            lo = mid;
        } else {
            hi = mid;
        }
    }

    return lo;
}

function countPositiveUpTo(n) {
    if (n < BASE) return 0n;
    return floorSixthRoot(n / BASE);
}

function parseInput(raw) {
    const trimmed = raw.trim();
    if (trimmed.length === 0) {
        throw new Error('Expected two integer inputs.');
    }

    const tokens = trimmed.split(/\s+/);
    if (tokens.length !== 2) {
        throw new Error('Expected exactly two integer tokens.');
    }

    const [leftToken, rightToken] = tokens;
    if (!INTEGER_RE.test(leftToken) || !INTEGER_RE.test(rightToken)) {
        throw new Error('Inputs must be valid integers.');
    }

    const l = BigInt(leftToken);
    const r = BigInt(rightToken);

    if (l > r) {
        throw new Error('Invalid range: l must be less than or equal to r.');
    }

    return { l, r };
}

function solve(l, r) {
    if (l === 0n) {
        return 1n + countPositiveUpTo(r);
    }

    return countPositiveUpTo(r) - countPositiveUpTo(l - 1n);
}

function main() {
    const chunks = [];
    let totalBytes = 0;

    process.stdin.on('data', (chunk) => {
        totalBytes += chunk.length;
        if (totalBytes > MAX_INPUT_BYTES) {
            process.stderr.write('Input too large.\n');
            process.exit(1);
        }

        chunks.push(chunk);
    });

    process.stdin.on('end', () => {
        try {
            const raw = Buffer.concat(chunks).toString('utf8');
            const { l, r } = parseInput(raw);
            const answer = solve(l, r);
            process.stdout.write(String(answer));
        } catch (err) {
            process.stderr.write(`${err.message}\n`);
            process.exit(1);
        }
    });

    process.stdin.on('error', () => {
        process.stderr.write('Failed to read input.\n');
        process.exit(1);
    });
}

main();