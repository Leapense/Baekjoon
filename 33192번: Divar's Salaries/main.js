"use strict";
const fs = require("fs");
const { format } = require("path/win32");

const input = fs.readFileSync(0, "utf8").trim();
if (input.length === 0) process.exit(0);

const tokens = input.split(/\s+/);
let idx = 0;

const n = parseInt(tokens[idx++], 10);

function formatWithCommas(big) {
  const s = big.toString();
  return s.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

let out = [];
for (let i = 0; i < n; i++) {
  const x = BigInt(tokens[idx++]);
  const k = BigInt(tokens[idx++]);
  const h = BigInt(tokens[idx++]);

  const nonHoliday = k - h;
  const normal = nonHoliday > 140n ? 140n : nonHoliday;
  const overtime = nonHoliday > 140n ? nonHoliday - 140n : 0n;

  const normalPay = normal * x;
  const overtimePay = (overtime * x * 3n) / 2n;
  const holidayPay = h * x * 2n;

  const total = normalPay + overtimePay + holidayPay;
  out.push(formatWithCommas(total));
}
process.stdout.write(out.join("\n"));
