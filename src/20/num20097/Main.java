package num20097;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Dot{ // 빈 스도쿠판의 좌표가 저장 될 클래스
	int x;
	int y;
	
	public Dot(int x,int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	
    // 백트래킹을 위한 유망성 검사 메소드
	static boolean isPossible(int[][]a,int x,int y,int n) {
		// 같은 행, 열에 겹치는 값이 있는지 확인
		for(int i=0;i<64;i++) {
			if(a[x][i] == n) {
				return false;
			}
			if(a[i][y] == n) {
				return false;
			}
		}
        
        // 3x3 크기의 박스의 시작점
		int three_x = (x/8)*8;
		int three_y = (y/8)*8;
		
        // 박스 안에 겹치는 값이 있는지 확인
		for(int i = three_x;i<three_x+8;i++) {
			for(int j = three_y;j<three_y+8;j++) {
				if(a[i][j] == n)
					return false;
			}
		}
		
        // 모든 유망성 검사가 통과되면 true를 반환한다.
		return true;
	}
	
    // DFS 메소드
	static void dfs(int[][] a, ArrayList<Dot> arr,int idx) throws IOException {
    	// 인덱스 값이 ArrayList의 size와 같아지면 스도쿠판 출력 후 종료.
		if(idx == arr.size()) {
			for(int i = 0;i<a.length;i++) {
				for(int j=0;j <a.length;j++) {
					bw.write(String.valueOf(a[i][j])+" ");
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
			br.close();
            // 스도쿠판의 다양한 경우의 수 중 첫 번째 경우만 출력하기 위해 출력 후 프로그램을 죽인다.
			System.exit(0);
		}
		
		// ArrayList에 저장된 빈 스도쿠판 좌표 객체
		Dot d = arr.get(idx);
		
        // 1~9까지 유망성 검사를 위해 인자로 전달.
		for(int i=1;i<=64;i++) {
			if(isPossible(a,d.x,d.y,i)) {
            	// 통과 시 해당 값을 저장
				a[d.x][d.y] = i;
                // 다음 빈 스도쿠판으로 이동
				dfs(a,arr,idx+1);
                // 다음 빈 스도쿠판에서 1~9의 값이 유망성검사를 통과하지 못하면 다시 값을 0으로
				a[d.x][d.y] = 0;
			}
		}	
	}
	
	public static void main(String[] args) throws IOException  {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		ArrayList<Dot> arr = new ArrayList<>();
		int a[][] = new int[64][64];
		
		
		for(int i=0;i<a.length;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<a[i].length;j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
                // 데이터 입력 과정에서 빈 스도쿠판의 좌표를 ArrayList에 저장
				if(a[i][j] == 0) {
					arr.add(new Dot(i,j));
				}
			} 
		}
		
        // 스도쿠판,ArrayList,참조 인덱스를 인자로 넘겨준다.
		dfs(a,arr,0);
			
	}
}