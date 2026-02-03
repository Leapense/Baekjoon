'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trimEnd().split('\n');
const S = (input[0] ?? '').trim();
const answer = S[0] + S;
process.stdout.write(answer);
process.exit(0);