const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split('\n');
let idx = 0;

const N = Number(input[idx++]);
const answerKey = [];
for (let i = 0; i < N; i++) {
    answerKey.push(input[idx++].trim());
}

const M = Number(input[idx++]);
const students = [];

for (let i = 0; i < M; i++) {
    const studentId = Number(input[idx++]);
    let score = 0;

    for (let j = 0; j < N; j++) {
        const ans = input[idx++].trim();
        if (ans === answerKey[j]) score++;
    }

    students.push({ id: studentId, grade: score });
}

const sortType = input[idx++].trim();

students.sort((a, b) => {
    if (sortType === 'STUDENT_ID_ASC') {
        return a.id - b.id;
    }

    if (sortType === 'STUDENT_ID_DESC') {
        return b.id - a.id;
    }

    if (sortType === 'GRADE_ASC') {
        if (a.grade !== b.grade) return a.grade - b.grade;
        return a.id - b.id;
    }

    if (a.grade !== b.grade) return b.grade - a.grade;
    return a.id - b.id;
});

const output = students.map(s => `${s.id} ${s.grade}`).join('\n');
process.stdout.write(output);
process.exit(0);