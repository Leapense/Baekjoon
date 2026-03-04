'use strict';
const fs = require('fs');

const data = fs.readFileSync(0, 'utf8').trim();
if (data.length === 0) process.exit(0);
const arr = data.split(/\s+/).map(Number);

let p = 0;
const N = arr[p++], M = arr[p++], K = arr[p++];

class MinHeap {
    constructor() {
        this.a = [];
    }
    size() { return this.a.length; }
    peek() { return this.a[0]; }

    push(x) {
        const a = this.a;
        a.push(x);
        let i = a.length - 1;
        while (i > 0) {
            const parent = (i - 1) >> 1;
            if (a[parent] <= a[i]) break;
            [a[parent], a[i]] = [a[i], a[parent]];
            i = parent;
        }
    }

    pop() {
        const a = this.a;
        const n = a.length;
        if (n === 1) return a.pop();

        const root = a[0];
        a[0] = a.pop();

        let i = 0;
        while (true) {
            const left = i * 2 + 1;
            const right = left + 1;
            let smallest = i;

            if (left < a.length && a[left] < a[smallest]) smallest = left;
            if (right < a.length && a[right] < a[smallest]) smallest = right;
            if (smallest === i) break;
            [a[i], a[smallest]] = [a[smallest], a[i]];
            i = smallest;
        }

        return root;
    }
}

const heap = new MinHeap();
for (let i = 0; i < M; i++) heap.push(0);

for (let i = 0; i < N; i++) {
    const t = arr[p++];
    const curMin = heap.pop();
    heap.push(curMin + t);
}

const minWait = heap.peek();
process.stdout.write(minWait <= K ? 'WAIT' : 'GO');
process.exit(0);