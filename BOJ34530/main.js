const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim();
const d = parseInt(input, 10);

function gcd(a, b) {
    while (b !== 0) {
        const t = a % b;
        a = b;
        b = t;
    }

    return a;
}

const g = gcd(360, d);
const ans = 360 / g;

process.stdout.write(String(ans));
process.exit(0);