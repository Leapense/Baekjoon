'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let p = 0;

const N = input[p++];
const K = input[p++];

const maxVal = new Int32Array(K);
const maxCnt = new Int32Array(K);
const owner = new Int32Array(K);

for (let i = 0; i < N; i++) {
	for (let j = 0; j < K; j++) {
		const v = input[p++];

		if (v > maxVal[j]) {
			maxVal[j] = v;
			maxCnt[j] = 1;
			owner[j] = i;
		} else if (v === maxVal[j]) {
			maxCnt[j] += 1;
		}
	}
}

const eligible = new Uint8Array(N);

for (let j = 0; j < K; j++) {
	if (maxCnt[j] === 1) {
		eligible[owner[j]] = 1;
	}
}

let ans = 0;
for (let i = 0; i < N; i++) {
	ans += eligible[i];
}

process.stdout.write(String(ans));
process.exit(0);