const fs = require("fs");

const MAX_INPUT_SIZE = 1024;
const MAX_N = 5000;

try {
    const fd = 0;
    const buffer = Buffer.alloc(MAX_INPUT_SIZE);
    let bytesRead = 0;

    try {
        bytesRead = fs.readSync(fd, buffer, 0, MAX_INPUT_SIZE, null);
    } catch (e) {

    }

    const inputString = buffer.toString("utf8", 0, bytesRead).trim();

    if (!inputString) {
        process.stdout.write("0");
        process.exit(0);
    }

    const n = Number(inputString);

    if (isNaN(n) || !Number.isInteger(n)) {
        console.error("Error: Input must be a valid integer.");
        process.exit(1);
    }

    if (n < 0 || n > MAX_N) {
        console.error(`Error: Input must be between 0 and ${MAX_N}.`);
        process.exit(1);
    }

    let ans = 0n;

    for (let i = 1; i <= n; i++) {
        ans = BigInt(i) * (ans + 1n);
    }

    process.stdout.write(ans.toString());
} catch (error) {
    console.error("An unexpected error occurred.");
    process.exit(1);
}