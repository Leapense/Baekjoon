'use strict';

const fs = require('fs');

function solve() {
    const input = fs.readFileSync(0, 'utf8');
    let offset = 0;
    const len = input.length;

    function nextNumber() {
        while (offset < len && input.charCodeAt(offset) <= 32) {
            offset++;
        }

        if (offset >= len) return null;

        const start = offset;
        while (offset < len && input.charCodeAt(offset) > 32) {
            offset++;
        }

        return Number(input.slice(start, offset));
    }

    const n = nextNumber();
    if (n === null) return;

    let ans = 0;
    for (let i = 0; i < n; i++) {
        const x = nextNumber();
        ans += (x + 1) / 2;
    }

    process.stdout.write(ans.toFixed(10));
}
solve();
process.exit(0);