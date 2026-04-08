const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim();
if (!input) process.exit(0);

const data = input.split(/\s+/).map(Number);
let idx = 0;
const T = data[idx++];
const output = [];

function analyze(x) {
    let k = 0;
    let odd = x;

    while (odd % 2 === 0) {
        odd /= 2;
        k++;
    }

    return {
        odd,
        k,
        isPowerOfTwo: odd === 1
    };
}

for (let tc = 0; tc < T; tc++) {
    const arr = [];
    while (idx < data.length) {
        const x = data[idx++];
        if (x === 0) break;
        arr.push(x);
    }

    const memo = new Map();
    function getInfo(x) {
        if (!memo.has(x)) memo.set(x, analyze(x));
        return memo.get(x);
    }

    arr.sort((a, b) => {
        const A = getInfo(a);
        const B = getInfo(b);
        if (A.isPowerOfTwo !== B.isPowerOfTwo) {
            return A.isPowerOfTwo ? 1 : -1;
        }

        if (!A.isPowerOfTwo) {
            if (A.k !== B.k) return A.k - B.k;
            return A.odd - B.odd;
        }

        return B.k - A.k;
    });

    output.push(arr.join(' '));
}

console.log(output.join('\n'));
process.exit(0);