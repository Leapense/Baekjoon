const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split('\n');
const N = Number(input[0]);
const M = Number(input[1]);
const grid = input.slice(2, 2 + N).map(line => line.trim());

const dirs = [
	[-1, -1], [-1, 0], [-1, 1],
	[0, -1],           [0, 1],
	[1, -1], [1, 0], [1, 1]
];

const unpowered = [];

for (let i = 0; i < N; i++) {
	for (let j = 0; j < M; j++) {
		if (grid[i][j] !== 'b') continue;
		let powered = false;
		for (const [dx, dy] of dirs) {
			const ni = i + dx;
			const nj = j + dy;
			if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
			if (grid[ni][nj] === 'T') {
				powered = true;
				break;
			}
		}
		if (!powered) {
			unpowered.push(`${i} ${j}`);
		}
	}
}

if (unpowered.length === 0) {
	console.log('True');
} else {
	console.log('False');
	console.log(unpowered.join('\n'));
}
process.exit(0);
