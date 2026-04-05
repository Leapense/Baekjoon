const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split("\n");
const N = Number(input[0]);
const S = input[1].trim();

let cntG = 0;
let cntH = 0;
let cntS = 0;

for (let i = 0; i < N; i++) {
	const ch = S[i];
	if (ch === "G") cntG++;
	else if (ch === "H") cntH++;
	else if (ch === "S") cntS++;
}

const answer = Math.min(cntG, cntH, Math.floor(cntS / 2));
console.log(answer.toString());
process.exit(0);
