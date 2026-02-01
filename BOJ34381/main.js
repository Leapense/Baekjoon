const fs = require("fs");
const input = fs.readFileSync(0, 'utf8').trim();
const fileChar = input[0];
const rankChar = input[1];

const x = fileChar.charCodeAt(0) - 97;
const y = (rankChar.charCodeAt(0) - 48) - 1;

const deltas = [
    [1, 2], [2, 1], [2, -1], [1, -2],
    [-1, -2], [-2, -1], [-2, 1], [-1, 2],
];

const moves = [];

for (const [dx, dy] of deltas) {
    const nx = x + dx;
    const ny = y + dy;
    if (0 <= nx && nx < 8 && 0 <= ny && ny < 8) {
        moves.push([nx, ny]);
    }
}

moves.sort((a, b) => (a[0] !== b[0] ? a[0] - b[0] : a[1] - b[1]));

let out = [];
for (const [mx, my] of moves) {
    const f = String.fromCharCode(97 + mx);
    const r = String(my + 1);
    out.push(f + r);
}

process.stdout.write(out.join('\n'));
process.exit(0);