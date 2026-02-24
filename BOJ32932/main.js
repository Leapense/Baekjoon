'use strict';

const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

const MAX_OBSTACLES = 1_000_000;
const MAX_COORD = 1_000_000;

let state = 'HEADER';
let N = 0;
let obstaclesRead = 0;
const obstacles = new Set();

let tokenBuffer = [];

rl.on('line', (line) => {
    const tokens = line.trim().split(/\s+/);
    for (const token of tokens) {
        if (!token) continue;
        processToken(token);
    }
});

function processToken(token) {
    if (state === 'HEADER') {
        if (tokenBuffer.length < 2) {
            tokenBuffer.push(token);
        }

        if (tokenBuffer.length === 2) {
            const nVal = Number(tokenBuffer[0]);
            const kVal = Number(tokenBuffer[1]);

            if (!Number.isInteger(nVal) || nVal < 0) {
                process.exit(1);
            }
            if (nVal > MAX_OBSTACLES) {
                process.exit(1);
            }

            N = nVal;
            tokenBuffer = [];

            if (N === 0) {
                state = 'COMMANDS';
            } else {
                state = 'OBSTACLES';
            }
        }
    } else if (state === 'OBSTACLES') {
        tokenBuffer.push(token);

        if (tokenBuffer.length === 2) {
            const x = Number(tokenBuffer[0]);
            const y = Number(tokenBuffer[1]);

            if (!Number.isFinite(x) || !Number.isFinite(y)) {
                process.exit(1);
            }

            if (Math.abs(x) > MAX_COORD || Math.abs(y) > MAX_COORD) {
                process.exit(1);
            }

            obstacles.add(`${x},${y}`);
            obstaclesRead++;
            tokenBuffer = [];

            if (obstaclesRead === N) {
                state = 'COMMANDS';
            }
        }
    } else if (state === 'COMMANDS') {
        runSimulation(token);
    }
}

let droneX = 0;
let droneY = 0;

function runSimulation(cmdString) {
    for (let i = 0; i < cmdString.length; i++) {
        const c = cmdString[i];
        let nx = droneX;
        let ny = droneY;

        if (c === 'U') ny++;
        else if (c === 'D') ny--;
        else if (c === 'R') nx++;
        else if (c === 'L') nx--;

        if (!obstacles.has(`${nx},${ny}`)) {
            droneX = nx;
            droneY = ny;
        }
    }
}

rl.on('close', () => {
    if (state !== 'COMMANDS' && (state !== 'OBSTACLES' || obstaclesRead !== N)) {

    }
    process.stdout.write(`${droneX} ${droneY}\n`);
});