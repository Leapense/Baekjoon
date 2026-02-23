const fs = require('fs');

function getF(T) {
    let n = T.length;
    let dp = new Int32Array(n * n);
    
    // 길이가 1인 구간의 초기화
    for (let i = 0; i < n; i++) {
        dp[i * n + i] = 1;
    }
    
    // 구간 DP
    for (let len = 2; len <= n; len++) {
        for (let i = 0; i <= n - len; i++) {
            let j = i + len - 1;
            // 아무 문자와도 같이 칠하지 않고 i번째 단일 문자만 새로 칠하는 경우
            let res = 1 + dp[(i + 1) * n + j];
            let Ti = T.charCodeAt(i);
            
            // i번째 문자와 동일한 문자를 찾아 병합하여 색칠하는 경우
            for (let k = i + 1; k <= j; k++) {
                if (Ti === T.charCodeAt(k)) {
                    let left = (k - 1 >= i + 1) ? dp[(i + 1) * n + k - 1] : 0;
                    let cost = left + dp[k * n + j];
                    if (cost < res) res = cost;
                }
            }
            dp[i * n + j] = res;
        }
    }
    return dp[n - 1]; // dp[0][n - 1]
}

function solve() {
    const input = fs.readFileSync('/dev/stdin').toString().trim().split(/\s+/);
    if (input.length === 0 || input[0] === '') return;
    
    let ptr = 0;
    let T_cases = parseInt(input[ptr++]);
    
    for (let t = 0; t < T_cases; t++) {
        let N = parseInt(input[ptr++]);
        let X = parseInt(input[ptr++]);
        let Y = parseInt(input[ptr++]);
        let S = input[ptr++];
        
        let f_S = getF(S);
        // 문자를 수정하는 비용이 점수보다 크거나 같다면 수정할 이유가 없습니다.
        if (Y <= X) {
            console.log(f_S * Y);
            continue;
        }
        
        // Beam Search 매개변수
        let BEAM_WIDTH = N <= 20 ? 100 : 30;
        let maxScore = f_S * Y;
        let beam = [ { str: S, changes: 0, score: maxScore } ];
        let visited = new Set();
        visited.add(S);
        
        let noImprovementSteps = 0;
        let MAX_EVALS = 30000;
        let evals = 0;
        
        for (let step = 1; step <= N; step++) {
            let nextBeam = [];
            let improved = false;
            
            for (let state of beam) {
                let arr = state.str.split('');
                for (let i = 0; i < N; i++) {
                    let orig = arr[i];
                    for (let c of ['R', 'G', 'B']) {
                        if (c === orig) continue;
                        arr[i] = c;
                        let newStr = arr.join('');
                        
                        if (!visited.has(newStr)) {
                            visited.add(newStr);
                            let newF = getF(newStr);
                            evals++;
                            let newChanges = state.changes + 1;
                            let newScore = newF * Y - newChanges * X;
                            
                            if (newScore > maxScore) {
                                maxScore = newScore;
                                improved = true;
                            }
                            
                            nextBeam.push({ str: newStr, changes: newChanges, score: newScore });
                        }
                    }
                    arr[i] = orig; // 원상복구
                    if (evals >= MAX_EVALS) break;
                }
                if (evals >= MAX_EVALS) break;
            }
            
            // 점수가 높은 순으로 정렬하여 다음 Depth로 이동
            nextBeam.sort((a, b) => b.score - a.score);
            beam = nextBeam.slice(0, BEAM_WIDTH);
            
            if (improved) {
                noImprovementSteps = 0;
            } else {
                noImprovementSteps++;
                // 휴리스틱: 3단계 깊이 이상 탐색해도 최적해 갱신이 없다면 극댓값으로 간주하고 조기 종료
                if (noImprovementSteps >= 3) break; 
            }
            
            if (beam.length === 0 || evals >= MAX_EVALS) break;
        }
        console.log(maxScore);
    }
}

solve();