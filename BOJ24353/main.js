const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim();
const parts = input.split(/\s+/).map(Number);
const N = parts[0];
const s = parts[1];
const M = parts[2];

const results = [];
let currentMonthN3 = -1;

const possibleN3 = [];
if (N >= 10) {
    possibleN3.push(10);
}

for (let i = 23; i <= Math.min(N, 99); i++) {
    possibleN3.push(i);
}

for (let month = 1; month <= 12; month++) {
    const N1 = month + 10;
    const N2 = s;
    let count = 0;
    let smallestN3 = -1;

    for (let idx = 0; idx < possibleN3.length; idx++) {
        const N3 = possibleN3[idx];
        const fiveDigit = N1 * 1000 + N2 * 100 + N3;

        const sum12 = N1 + N2;
        const sum13 = N1 + N3;
        const sum23 = N2 + N3;

        if (fiveDigit % sum12 === 0 || fiveDigit % sum13 === 0 || fiveDigit % sum23 === 0) {
            count++;
            if (smallestN3 === -1) {
                smallestN3 = N3;
            }
        }
    }

    results.push(count);
    if (month === M) {
        currentMonthN3 = smallestN3;
    }
}

for (let i = 0; i < 12; i++) {
    console.log(results[i]);
}

if (currentMonthN3 === -1) {
    console.log("0 0 0");
} else {
    console.log((M + 10) + " " + s + " " + currentMonthN3);
}