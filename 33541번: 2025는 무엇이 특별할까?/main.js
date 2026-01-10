const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim();
const X = parseInt(input, 10);

for (let Y = X + 1; Y <= 9999; Y++) {
  const A = Math.floor(Y / 100);
  const B = Y % 100;
  const S = A + B;

  if (S * S === Y) {
    console.log(Y);
    process.exit(0);
  }
}

console.log(-1);
