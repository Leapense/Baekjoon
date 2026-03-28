const fs = require('fs');
const N = Number(fs.readFileSync(0, 'utf8').trim());
const ans = N * N * (9 - 5 * Math.sqrt(3)) / 3;
console.log(ans.toFixed(10));