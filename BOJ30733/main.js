'use strict';
const fs = require('fs');

const BUF_SIZE = 65536;
const buffer = Buffer.alloc(BUF_SIZE);
let bufLen = 0;
let bufPtr = 0;
const fd = 0;

function readByte() {
    if (bufPtr >= bufLen) {
        bufPtr = 0;
        try {
            bufLen = fs.readSync(fd, buffer, 0, BUF_SIZE, null);
        } catch (e) {
            bufLen = 0;
        }

        if (bufLen === 0) return null;
    }

    return buffer[bufPtr++];
}

function readToken() {
    let byte = readByte();
    while (byte !== null && byte <= 32) {
        byte = readByte();
    }
    if (byte === null) return null;

    const res = [];
    while (byte !== null && byte > 32) {
        res.push(byte);
        byte = readByte();
    }

    return Buffer.from(res).toString('utf8');
}

function main() {
    const tokenN = readToken();
    const tokenK = readToken();
    if (!tokenN || !tokenK) return;

    const n = Number(tokenN);
    const k = BigInt(tokenK);

    let prev = 0n;
    let first = true;

    let outBuffer = '';
    const FLUSH_THRESHOLD = 4096;

    for (let i = 0; i < n; i++) {
        const tokenT = readToken();
        if (!tokenT) break;
        const t = BigInt(tokenT);

        if (i === 0) {
            prev = t;
        } else {
            const need = prev + k;
            prev = (t > need) ? t : need;
        }

        if (!first) {
            outBuffer += ' ';
        }
        outBuffer += prev.toString();
        first = false;

        if (outBuffer.length > FLUSH_THRESHOLD) {
            process.stdout.write(outBuffer);
            outBuffer = '';
        }
    }

    if (outBuffer.length > 0) {
        process.stdout.write(outBuffer);
    }
}

try {
    main();
} catch (error) {
    console.error('Processing error:', error.message);
    process.exit(1);
}