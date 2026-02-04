const fs = require("fs");
const s  = parseInt(fs.readFileSync(0, "utf8").trim(), 10);

let a, b;
if (s === 0) {
    a = -999;
    b = 999;
} else if (s === 1) {
    a = 2;
    b = -1;
} else if (s === -999) {
    a = -998;
    b = -1;
} else {
    a = 1;
    b = s - 1;
}

process.stdout.write(a + " " + b);
process.exit(0);