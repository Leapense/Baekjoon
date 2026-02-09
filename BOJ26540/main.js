const fs = require("fs");

const data = fs.readFileSync(0, "utf8").trimEnd().split("\n");
let idx = 0;

if (data.length === 0 || data[0].trim() === "") process.exit(0);

const t = parseInt(data[idx++].trim(), 10);
const out = [];

for (let tc = 0; tc < t; tc++) {
    const x = parseInt(data[idx++].trim(), 10);
    const plants = [];

    for (let i = 0; i < x; i++) {
        const parts = data[idx++].trim().split(/\s+/);
        const nums = parts.map(s => BigInt(s));
        const back = nums[nums.length - 1];
        nums.pop();

        const m = nums.length;

        let total = 0n;
        if (back === -1n) {
            for (let j = 0; j < m; j++) total += nums[j];
            plants.push([total, null]);
        } else {
            const r = Number(back);
            let prefix = 0n;
            for (let j = 0; j < m; j++) {
                const v = nums[j];
                total += v;
                if (j < r) prefix += v;
            }

            const period = total - prefix;
            plants.push([total, period]);
        }
    }

    const D = BigInt(data[idx++].trim());
    let count = 0;
    for (const [S, P] of plants) {
        if (D === S) {
            count++;
        } else if (P !== null && D > S && (D - S) % P === 0n) {
            count++;
        }
    }

    out.push(String(count));
}

console.log(out.join("\n"));