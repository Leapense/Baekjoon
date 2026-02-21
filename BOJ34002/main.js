'use strict';
const fs = require('fs');
const TARGET_LEVEL = 250;
const EXP_PER_LEVEL = 100;
const MINUTES_PER_TICKET = 30;

function calculateMinimumTime(expEvent, expTraining, expVip, ticketsTraining, ticketsVip, currentLevel) {
    let neededExp = (TARGET_LEVEL - currentLevel) * EXP_PER_LEVEL;
    let totalTime = 0;

    const trainingMethods = [
        { expPerMin: expVip, maxMinutes: ticketsVip * MINUTES_PER_TICKET },
        { expPerMin: expTraining, maxMinutes: ticketsTraining * MINUTES_PER_TICKET },
        { expPerMin: expEvent, maxMinutes: Infinity }
    ];

    for (const method of trainingMethods) {
        if (neededExp <= 0) break;
        const maxExpFromMethod = method.maxMinutes * method.expPerMin;
        if (neededExp <= maxExpFromMethod) {
            totalTime += Math.ceil(neededExp / method.expPerMin);
            neededExp = 0;
        } else {
            totalTime += method.maxMinutes;
            neededExp -= maxExpFromMethod;
        }
    }

    return totalTime;
}

function main() {
    const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
    if (input.length < 6) return;

    const [A, B, C, S, V, L] = input;
    const timeRequired = calculateMinimumTime(A, B, C, S, V, L);
    process.stdout.write(timeRequired.toString());
}

main();
process.exit(0);