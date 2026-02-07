const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const lines = [];
rl.on('line', (line) => {
    lines.push(line.trim());
});

rl.on('close', () => {
    const n = parseInt(lines[0]);
    const colorValues = {
        'red': 1,
        'yellow': 2,
        'green': 3,
        'brown': 4,
        'blue': 5,
        'pink': 6,
        'black': 7
    };

    let redCount = 0;
    let colors = [];

    for (let i = 1; i <= n; i++) {
        const ball = lines[i];
        if (ball === 'red') {
            redCount++;
        } else {
            colors.push(colorValues[ball]);
        }
    }

    let score;

    if (redCount === 0) {
        score = colors.reduce((a, b) => a + b, 0);
    } else if (colors.length === 0) {
        score = 1;
    } else {
        const maxColor = Math.max(...colors);
        const sumColors = colors.reduce((a, b) => a + b, 0);
        score = redCount * (maxColor + 1) + sumColors;
    }

    console.log(score);
});