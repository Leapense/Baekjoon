const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
const startStr = input[0];
const endStr = input[1];

function toSeconds(t) {
    const [HH, MM, SS] = t.split(":").map(Number);
    return HH * 3600 + MM * 60 + SS;
}

const start = toSeconds(startStr);
const end = toSeconds(endStr);

function countMultiplesInOpenClosed(start, end, period) {
    return Math.floor(end / period) - Math.floor(start / period);
}

const hour = countMultiplesInOpenClosed(start, end, 43200);
const minute = countMultiplesInOpenClosed(start, end, 3600);
const second = countMultiplesInOpenClosed(start, end, 60);

console.log(`${hour} ${minute} ${second}`);