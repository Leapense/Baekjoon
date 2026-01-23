const fs = require('fs');
const data = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const [a, b, x, y] = data;

let ans;
if (a === 0) {
    if (x === 0 && y > 0 && y < b) ans = 3;
    else ans = 1;
} else if (b === 0) {
    if (y === 0 && x > 0 && x < a) ans = 3;
    else ans = 1;
} else {
    ans = 2;
}

console.log(ans);