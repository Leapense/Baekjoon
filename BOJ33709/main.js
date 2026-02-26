"use strict";
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let lineCount = 0;
let sum = 0n;

rl.on("line", (line) => {
    if (lineCount === 0) {
        lineCount++;
        return;
    }

    if (lineCount === 1) {
        const s = line.trim();
        let cur = 0n;

        for (let i = 0; i < s.length; i++) {
            const ch = s[i];

            if (ch === "." || ch === "|" || ch === ":" || ch === "#") {
                sum += cur;
                cur = 0n;
            } else if (ch >= '0' && ch <= '9') {
                cur = cur * 10n + BigInt(ch);
            } else {
                sum += cur;
                cur = 0n;
            }
        }
        sum += cur;

        console.log(sum.toString());
        rl.close();
        process.exit(0);
    }
});