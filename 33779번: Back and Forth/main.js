"use strict";

const fs = require("fs");
let s = fs.readFileSync(0, "utf8");
s = s.replace(/[\r\n]+$/, "");

let l = 0;
let r = s.length - 1;

while (l < r) {
  if (s.charCodeAt(l) !== s.charCodeAt(r)) {
    process.stdout.write("boop\n");
    process.exit(0);
  }
  l++;
  r--;
}

process.stdout.write("beep\n");
