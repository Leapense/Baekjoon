'use strict';

const fs = require('fs');
const MAX_PILES = 100000;
const MAX_BOXES = 100000;

function solve() {
    let buffer;
    try {
        buffer = fs.readFileSync(0);
    } catch (e) {
        return;
    }

    let offset = 0;

    function nextInt() {
        while (offset < buffer.length && buffer[offset] <= 32) {
            offset++;
        }
        if (offset >= buffer.length) return null;

        let res = 0;
        let sign = 1;
        if (buffer[offset] === 45) {
            sign = -1;
            offset++;
        }

        while (offset < buffer.length && buffer[offset] > 32) {
            res = res * 10 + (buffer[offset] - 48);
            offset++;
        }

        return res * sign;
    }

    while (true) {
        const N = nextInt();
        if (N === null) break;

        const P = nextInt();
        if (P === null) break;

        if (N === 0 && P === 0) break;

        if (P < 0 || P > MAX_PILES) {
            console.error("Error: Number of piles (P) exceeds allowed limit.");
            process.exit(1);
        }

        const heights = new Array(P);
        let targetPile = -1;
        let targetLevel = -1;

        for (let i = 0; i < P; i++) {
            const q = nextInt();
            if (q === null || q < 0 || q > MAX_BOXES) {
                console.error("Error: Number of boxes (q) exceeds allowed limit or input truncated.");
                process.exit(1);
            }

            heights[i] = q;
            for (let j = 1; j <= q; j++) {
                const boxId = nextInt();
                if (boxId === 1) {
                    targetPile = i;
                    targetLevel = j;
                }
            }
        }

        if (targetPile === -1 || targetLevel === -1) {
            console.error("Error: Target box (1) was not found in the provided layout.");
            process.exit(1);
        }

        const k = targetPile;
        const h = targetLevel;
        const above = heights[k] - h;

        let L = k;
        while (L - 1 >= 0 && heights[L - 1] >= h) L--;

        let R = k;
        while (R + 1 < P && heights[R + 1] >= h) R++;

        let leftCost = 0;
        for (let i = L; i < k; i++) {
            leftCost += heights[i] - h + 1;
        }

        let rightCost = 0;
        for (let i = k + 1; i <= R; i++) {
            rightCost += heights[i] - h + 1;
        }
        const answer = above + Math.min(leftCost, rightCost);
        process.stdout.write(answer + '\n');
    }
}

try {
    solve();
    process.exit(0);
} catch (err) {
    console.error("Unexpected error:", err.message);
    process.exit(1);
}