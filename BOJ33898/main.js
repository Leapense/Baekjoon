const fs = require("fs");

const lines = fs.readFileSync(0, "utf8").trim().split(/\s+/);
const row0 = lines[0] || "";
const row1 = lines[1] || "";

const cells = [
    row0[0],
    row0[1],
    row1[0],
    row1[1],
];

const target = "HEPC";

const CW = [0, 1, 3, 2];
const CCW = [0, 2, 3, 1];

function hasTarget(order) {
    for (let start = 0; start < 4; start++) {
        let s = "";
        for (let k = 0; k < 4; k++) {
            s += cells[order[(start + k) % 4]];
        }

        if (s === target) return true;
    }

    return false;
}

const ok = hasTarget(CW) || hasTarget(CCW);
process.stdout.write(ok ? "YES" : "NO");