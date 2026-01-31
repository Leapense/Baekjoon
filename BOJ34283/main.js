const fs = require("fs");

let s = fs.readFileSync(0, "utf8").replace(/[\r\n]+$/, "");
const target = "certainly";
let count = 0;
let idx = 0;

while (true) {
    idx = s.indexOf(target, idx);
    if (idx === -1) break;
    count++;
    idx += 1;
}

console.log(count.toString());
process.exit(0);