'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const Z = input[idx++];
let out = [];

for (let tc = 0; tc < Z; tc++) {
	const N = input[idx++];
	const exams = [];

	for (let i = 0; i < N; i++) {
		const day = input[idx++];
		const prep = input[idx++];
		exams.push([day, prep]);
	}
	exams.sort((a, b) => a[0] - b[0]);

	const firstDay = exams[0][0];
	let sumPrep = 0;
	let bestStart = Infinity;

	for (let i = 0; i < N; i++) {
		const [day, prep] = exams[i];
		sumPrep += prep;

		const candidateStart = day - sumPrep + 1;
		if (candidateStart < bestStart) bestStart = candidateStart;
	}
	out.push(String(firstDay - bestStart));
}

console.log(out.join('\n'));
process.exit(0);
