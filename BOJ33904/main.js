const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trimEnd().split('\n');

const expr = input[input.length - 1];
const forLines = input.slice(0, -1);

const vars = forLines.map(line => {
    const match = line.match(/for \(int ([A-Za-z]) = 1; \1 < 9; \1\+\+\)/);
    return match[1];
});

const used = new Set();
for (const ch of expr) {
    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
        used.add(ch);
    }
}

const seenInner = new Set();
const keep = Array(forLines.length).fill(false);

for (let i = forLines.length - 1; i >= 0; i--) {
    const v = vars[i];
    const redeclaredInside = seenInner.has(v);
    const usedInExpr = used.has(v);

    if (!redeclaredInside && usedInExpr) {
        keep[i] = true;
    }

    seenInner.add(v);
}

const result = [];
for (let i = 0; i < forLines.length; i++) {
    if (keep[i]) result.push(forLines[i]);
}

result.push(expr);

const output = result.map((line, idx) => ' '.repeat(idx) + line.trimStart()).join('\n');
process.stdout.write(output);
process.exit(0);