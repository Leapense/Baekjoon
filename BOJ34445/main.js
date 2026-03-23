const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const N = input[idx++];
const c = new Array(N);
let sumC = 0;

for (let i = 0; i < N; i++) {
	c[i] = input[idx++];
	sumC += c[i];
}

const R = input[idx++];
const total = sumC + R;

if (total % N !== 0) {
	console.log('not possible');
	process.exit(0);
}

const T = total / N;
const result = new Array(N);

for (let i = 0; i < N; i++) {
	const pay = T - c[i];
	if (pay < 0) {
		console.log('not possible');
		process.exit(0);
	}
	result[i] = String(pay);
}

console.log(result.join('\n'));