const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split("\n");

const N = Number(input[0]);
const contestants = [];

for (let i = 1; i <= N; i++) {
	const [name, A, B, C, D] = input[i].trim().split(" ");
	const totalScore = Number(A) + Number(C);
	const totalPenalty = Number(B) + Number(D);

	contestants.push({
		name,
		totalScore,
		totalPenalty,
	});
}

contestants.sort((p1, p2) => {
	if (p1.totalScore !== p2.totalScore) {
		return p2.totalScore - p1.totalScore;
	}

	if (p1.totalPenalty !== p2.totalPenalty) {
		return p1.totalPenalty - p2.totalPenalty;
	}

	return p1.name.localeCompare(p2.name);
});

console.log(contestants.map((person) => person.name).join("\n"));
process.exit(0);
