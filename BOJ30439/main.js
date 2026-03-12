'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n').map(line => line.trim());

const basePower = {
    Shadow: 6,
    Gale: 5,
    Ranger: 4,
    Anvil: 7,
    Vexia: 3,
    Guardian: 8,
    Thunderheart: 6,
    Frostwhisper: 2,
    Voidclaw: 3,
    Ironwood: 3,
    Zenith: 4,
    Seraphina: 1,
};

function parseLine(line) {
    const parts = line.split(/\s+/);
    const k = Number(parts[0]);
    return parts.slice(1, 1 + k);
}

function evaluate(cards, isCenter) {
    const n = cards.length;
    
    let total = 0;
    let seraphinaCount = 0;
    let thunderheartCount = 0;
    let zenithCount = 0;

    for (const card of cards) {
        total += basePower[card];
        if (card === 'Seraphina') seraphinaCount++;
        if (card === 'Thunderheart') thunderheartCount++;
        if (card === 'Zenith') zenithCount++;
    }

    if (isCenter) {
        total += 5 * zenithCount;
    }

    if (n === 4) {
        total += 6 * thunderheartCount;
    }

    total += seraphinaCount * (n - 1);
    return total;
}

let player1LocationWins = 0;
let player2LocationWins = 0;
let player1TotalPower = 0;
let player2TotalPower = 0;

for (let loc = 0; loc < 3; loc++) {
    const p1Cards = parseLine(input[2 * loc]);
    const p2Cards = parseLine(input[2 * loc + 1]);

    const p1Power = evaluate(p1Cards, loc === 1);
    const p2Power = evaluate(p2Cards, loc === 1);

    player1TotalPower += p1Power;
    player2TotalPower += p2Power;

    if (p1Power > p2Power) player1LocationWins++;
    else if (p2Power > p1Power) player2LocationWins++;
}

let answer;
if (player1LocationWins > player2LocationWins) {
    answer = 'Player 1';
} else if (player2LocationWins > player1LocationWins) {
    answer = 'Player 2';
} else if (player1TotalPower > player2TotalPower) {
    answer = 'Player 1';
} else if (player2TotalPower > player1TotalPower) {
    answer = 'Player 2';
} else {
    answer = 'Tie';
}

process.stdout.write(answer);
process.exit(0);