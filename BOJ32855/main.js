const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
const A = input[0];
const B = input[1];

function splitDecimal(s) {
    const [ip, fp] = s.split('.');
    return { ip, fp };
}

function cmpBigIntStr(a, b) {
    const x = BigInt(a);
    const y = BigInt(b);
    if (x > y) return 1;
    if (x < y) return -1;
    return 0;
}

function compareDecimal(sa, sb) {
    const a = splitDecimal(sa);
    const b = splitDecimal(sb);

    let c = cmpBigIntStr(a.ip, b.ip);
    if (c !== 0) return c;

    const la = a.fp.length;
    const lb = b.fp.length;
    const L = Math.max(la, lb);

    const fa = a.fp + "0".repeat(L - la);
    const fb = b.fp + "0".repeat(L - lb);

    if (fa > fb) return 1;
    if (fa < fb) return -1;
    return 0;
}

function toTuple(s) {
    const { ip, fp } = splitDecimal(s);
    const x = BigInt(ip);

    const trimmed = fp.replace(/^0+/, "");
    const y = trimmed === "" ? 0n : BigInt(trimmed);
    
    return { x, y };
}

function compareTuple(sa, sb) {
    const a = toTuple(sa);
    const b = toTuple(sb);

    if (a.x > b.x) return 1;
    if (a.x < b.x) return -1;

    if (a.y > b.y) return 1;
    if (a.y < b.y) return -1;
    return 0;
}

const dec = compareDecimal(A, B);
const tup = compareTuple(A, B);

if (tup === 0 || tup !== dec) {
    console.log("-1");
} else {
    console.log(dec === 1 ? A : B);
}