const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
const s = input[0] ?? "";
const t = input[1] ?? "";

function comp(ch) {
    switch (ch) {
        case "A": return "T";
        case "T": return "A";
        case "G": return "C";
        case "C": return "G";
        default: return ch;
    }
}

let other = "";
for (let i = s.length - 1; i >= 0; i--) {
    other += comp(s[i]);
}

const ok = (s.indexOf(t) !== -1) || (other.indexOf(t) !== -1);
process.stdout.write(ok ? "Yes" : "No");
process.exit(0);