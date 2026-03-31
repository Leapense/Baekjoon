const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim();
const n = Number(input);
const m = n / 10;

let out = [];

for (let x = 0; x <= n; x++) {
	const L = Math.max(0, 9 * m - x, x - 9 * m);
	const R = Math.min(n, 9 * m + x, 11 * m - x);
	const parity = (((9 * m - x) % 2) + 2) % 2;

	let minS = L;
	if (minS % 2 !== parity) minS++;

	let maxS = R;
	if (maxS % 2 !== parity) maxS--;

	out.push(`${minS} ${maxS}`);
}

console.log(out.join("\n"));
process.exit(0);
