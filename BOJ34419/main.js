const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
const board = input.map((row) => row.split(""));

let xBits = 0;
let oBits = 0;
for (let r = 0; r < 3; r++) {
    for (let c = 0; c < 3; c++) {
        const bitPos = r * 3 + c;
        if (board[r][c] === "X") {
            xBits |= (1 << bitPos);
        } else if (board[r][c] === "O") {
            oBits |= (1 << bitPos);
        }
    }
}

const wins = [
    7,   // 000000111 (row 0)
    56,  // 000011100 (row 1)
    448, // 111000000 (row 2)
    73,  // 001001001 (col 0)
    146, // 010010010 (col 1)
    292, // 100100100 (col 2)
    273, // 100010001 (diag \)
    84,  // 010101000 (diag /)
];

const hasWin = (bits) => wins.some(w => (bits & w) === w);

let ans = "N";
if (hasWin(xBits)) ans = "X";
else if (hasWin(oBits)) ans = "O";

process.stdout.write(ans);
process.exit(0);