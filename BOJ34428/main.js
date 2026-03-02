"use strict";
const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
const S = input[0] ?? "";
const C = input[1] ?? "";

function nextTerm(str) {
    if (str.length === 0) return "";
    const out = [];
    let i = 0;

    while (i < str.length) {
        const ch = str[i];
        let j = i + 1;
        while (j < str.length && str[j] === ch) j++;

        const count = j - i;
        out.push(String(count), ch);

        i = j;
    }

    return out.join("");
}

let cur = S;
if (cur === C) {
    console.log("0");
} else {
    const MAX_ITERS = 10000;

    for (let m = 1; m <= MAX_ITERS; m++) {
        cur = nextTerm(cur);

        if (cur === C) {
            console.log(String(m));
            process.exit(0);
        }

        if (cur.length > C.length) {
            break;
        }
    }

    console.log("-1");
}