const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n');

const [N, M] = input[0].split(' ').map(Number);
const grid = input.slice(1, N + 1);

for (let i = 0; i < N - 1; i++) {
    for (let j = 0; j < M - 1; j++) {
        if (
            grid[i][j] === '1' &&
            grid[i + 1][j] === '1' &&
            grid[i][j + 1] === '1' &&
            grid[i + 1][j + 1] === '1'
        ) {
            console.log(1);
            process.exit(0);
        }
    }
}

console.log(0);
process.exit(0);