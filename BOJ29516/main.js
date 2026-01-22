const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const a = parseInt(input[idx++], 10);
const b = parseInt(input[idx++], 10);

const grid = [];
for (let i = 0; i < a; i++) grid.push(input[idx++]);

function checkRemoveRow(rm) {
    let base = null;
    for (let i = 0; i < a; i++) {
        if (i === rm) continue;
        for (let j = 0; j < b; j++) {
            const ch = grid[i][j];
            if (base === null) base = ch;
            else if (ch !== base) return false;
        }
    }

    return true;
}

function checkRemoveCol(cm) {
    let base = null;
    for (let i = 0; i < a; i++) {
        for (let j = 0; j < b; j++) {
            if (j === cm) continue;
            const ch = grid[i][j];
            if (base === null) base = ch;
            else if (ch !== base) return false;
        }
    }

    return true;
}

let ok = false;
for (let r = 0; r < a && !ok; r++) {
    if (checkRemoveRow(r)) ok = true;
}

for (let c = 0; c < b && !ok; c++) {
    if (checkRemoveCol(c)) ok = true;
}

process.stdout.write(ok ? 'Yes\n' : 'No\n');