'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let ptr = 0;

function nextToken() {
    return ptr < input.length ? input[ptr++] : null;
}

const nTok = nextToken();
const kTok = nextToken();

if (nTok === null || kTok === null) {
    process.exit(0);
}

const n = parseInt(nTok, 10);
const k = parseInt(kTok, 10);
const target = k / 2;

let movedSize = 0;
let movedDown = 0;

function writeLine(s) {
    process.stdout.write(s + '\n');
}

while(true) {
    if (movedSize > 0 && movedSize < n && movedDown === target) {
        writeLine('Finish');
        process.exit(0);
    }

    writeLine('Move');
    movedSize++;

    const respTok = nextToken();
    if (respTok === null) {
        process.exit(0);
    }

    const newMovedDown = parseInt(respTok, 10);
    movedDown = newMovedDown;
}