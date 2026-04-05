const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);

const N = Number(input[0]);
let maxLimit = 0;
const answer = [];

for (let i = 1; i <= N; i++) {
	const sign = input[i];
	if (sign === "/") {
		const nationalLimit = Math.floor(maxLimit / 10) * 10 + 10;
		answer.push(String(nationalLimit));
	} else {
		const limit = Number(sign);
		answer.push(String(limit));
		if (limit > maxLimit) maxLimit = limit;
	}
}

console.log(answer.join("\n"));
process.exit(0);
