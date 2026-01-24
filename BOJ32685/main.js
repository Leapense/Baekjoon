const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const [a, b, c] = input;

function low4BitsBinary(x) {
    const v = x & 15;
    return v.toString(2).padStart(4, '0');
}

const bin = low4BitsBinary(a) + low4BitsBinary(b) + low4BitsBinary(c);
const passwordValue = parseInt(bin, 2);

const result = String(passwordValue).padStart(4, '0');
process.stdout.write(result + '\n');