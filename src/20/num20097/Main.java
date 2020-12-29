package num20097;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Dot{ // �� ���������� ��ǥ�� ���� �� Ŭ����
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
	
    // ��Ʈ��ŷ�� ���� ������ �˻� �޼ҵ�
	static boolean isPossible(int[][]a,int x,int y,int n) {
		// ���� ��, ���� ��ġ�� ���� �ִ��� Ȯ��
		for(int i=0;i<64;i++) {
			if(a[x][i] == n) {
				return false;
			}
			if(a[i][y] == n) {
				return false;
			}
		}
        
        // 3x3 ũ���� �ڽ��� ������
		int three_x = (x/8)*8;
		int three_y = (y/8)*8;
		
        // �ڽ� �ȿ� ��ġ�� ���� �ִ��� Ȯ��
		for(int i = three_x;i<three_x+8;i++) {
			for(int j = three_y;j<three_y+8;j++) {
				if(a[i][j] == n)
					return false;
			}
		}
		
        // ��� ������ �˻簡 ����Ǹ� true�� ��ȯ�Ѵ�.
		return true;
	}
	
    // DFS �޼ҵ�
	static void dfs(int[][] a, ArrayList<Dot> arr,int idx) throws IOException {
    	// �ε��� ���� ArrayList�� size�� �������� �������� ��� �� ����.
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
            // ���������� �پ��� ����� �� �� ù ��° ��츸 ����ϱ� ���� ��� �� ���α׷��� ���δ�.
			System.exit(0);
		}
		
		// ArrayList�� ����� �� �������� ��ǥ ��ü
		Dot d = arr.get(idx);
		
        // 1~9���� ������ �˻縦 ���� ���ڷ� ����.
		for(int i=1;i<=64;i++) {
			if(isPossible(a,d.x,d.y,i)) {
            	// ��� �� �ش� ���� ����
				a[d.x][d.y] = i;
                // ���� �� ������������ �̵�
				dfs(a,arr,idx+1);
                // ���� �� �������ǿ��� 1~9�� ���� �������˻縦 ������� ���ϸ� �ٽ� ���� 0����
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
                // ������ �Է� �������� �� ���������� ��ǥ�� ArrayList�� ����
				if(a[i][j] == 0) {
					arr.add(new Dot(i,j));
				}
			} 
		}
		
        // ��������,ArrayList,���� �ε����� ���ڷ� �Ѱ��ش�.
		dfs(a,arr,0);
			
	}
}