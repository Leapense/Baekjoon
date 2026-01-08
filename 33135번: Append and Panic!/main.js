const fs = require("fs");

const S = fs.readFileSync(0, "utf8").trim();
const n = S.length;

const cnt = new Array(26).fill(0);
let unique = 0;

for (let i = 1; i < n; i++) {
  const idx = S.charCodeAt(i - 1) - 65;
  if (cnt[idx] === 0) unique++;
  cnt[idx]++;

  const suffixLen = n - i;
  if (suffixLen !== unique) continue;

  let k = 0;
  let ok = true;

  for (let c = 0; c < 26; c++) {
    if (cnt[c] > 0) {
      if (S.charCodeAt(i + k) !== 65 + c) {
        ok = false;
        break;
      }
      k++;
    }
  }

  if (ok) {
    console.log(i.toString());
    process.exit(0);
  }
}
