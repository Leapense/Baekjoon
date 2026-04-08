const fs = require('fs');
try {
    const MAX_BUFFER = 5 * 1024 * 1024;
    const buffer = Buffer.alloc(MAX_BUFFER);

    let bytesRead = 0;
    try {
        bytesRead = fs.readSync(0, buffer, 0, MAX_BUFFER, null);
    } catch (e) {
        console.error("Failed to read input.");
        process.exit(1);
    }

    const text = buffer.toString('utf8', 0, bytesRead).trim();
    if (!text) process.exit(0);

    const input = text.split(/\s+/);

    let idx = 0;
    if (input.length < 3) {
        process.exit(0);
    }

    const X = Number(input[idx++]);
    const Y = Number(input[idx++]);
    const N = Number(input[idx++]);

    const MAX_N = 100_000;

    if (!Number.isInteger(N) || N < 0 || N > MAX_N) {
        console.error("Error: N is invalid or exceeds maximum limits.");
        process.exit(1);
    }

    let out = [];

    for (let i = 0; i < N; i++) {
        if (idx >= input.length) {
            break;
        }

        const A = Number(input[idx++]);

        if (!Number.isInteger(A) || A === 0) {
            out.push('NO');
            continue;
        }

        let ok = false;

        for (let mask = 0; mask < 16; mask++) {
            const c0 = (mask >> 0) & 1;
            const c1 = (mask >> 1) & 1;
            const c2 = (mask >> 2) & 1;
            const c3 = (mask >> 3) & 1;

            const top = Y - 2 + c0 + c1;
            const bottom = Y - 2 + c2 + c3;
            const left = X - c0 - c2;
            const right = X - c1 - c3;

            if (top % A === 0 && bottom % A === 0 && left % A === 0 && right % A === 0) {
                ok = true;
                break;
            }
        }

        out.push(ok ? 'YES' : 'NO');
    }

    console.log(out.join('\n'));
    process.exit(0);
} catch (err) {
    console.error("An unexpected error occurred.");
    process.exit(1);
}