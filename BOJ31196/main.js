const fs = require('fs');
const MAX_INPUT_SIZE = 50 * 1024 * 1024;

try {
    const stats = fs.fstatSync(0);
    let buffer;
    try {
        buffer = fs.readFileSync(0);
    } catch (e) {
        console.error("Error reading input:", e.message);
        process.exit(1);
    }

    if (buffer.length > MAX_INPUT_SIZE) {
        console.error(`Error: Input exceeds maximum allowed size of ${MAX_INPUT_SIZE} bytes.`);
        process.exit(1);
    }

    let start = 0;
    let end = buffer.length;

    while (start < end && buffer[start] <= 32) start++;
    while (end > start && buffer[end - 1] <= 32) end--;

    const n = end - start;
    if (n === 0) {
        process.exit(0);
    }

    let r = 1;
    for (let i = 1; i * i <= n; i++) {
        if (n % i === 0) r = i;
    }
    const s = n / r;

    const outputBuffer = Buffer.allocUnsafe(n);
    for (let i = 0; i < n; i++) {
        const row = Math.floor(i / s);
        const col = i % s;

        const srcIndex = start + (col * r) + row;
        outputBuffer[i] = buffer[srcIndex];
    }

    process.stdout.write(outputBuffer);
} catch (err) {
    console.error("An unexpected error occurred:", err.message);
    process.exit(1);
}