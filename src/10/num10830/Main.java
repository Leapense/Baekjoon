package num10830;

import java.util.Scanner;

public class Main {
  static int N;
  static int[][] A;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    A = new int[N][N];
    long B = sc.nextLong();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int el = sc.nextInt();
        A[i][j] = el;
      }
    }

    sc.close();

    long[][] result = mul(B);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sb.append(result[i][j] % 1000 + " ");
      }
      sb.append("\n");
    }

    System.out.println(sb.toString());
  }


  public static long[][] mul(long b) {
    long[][] result = new long[N][N];
    long[][] temp = new long[N][N];

    if(b == 1) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          result[i][j] = A[i][j] % 1000;
        }
      }
    }else if(b % 2 == 0) {
      
      temp = mul(b/2);

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          int el = 0;
          for (int k = 0; k < N; k++) {
            el += temp[i][k] * temp[k][j];
          }
          result[i][j] = el % 1000;
        }
      }
    }else {
      temp = mul(b-1);

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          int el = 0;
          for (int k = 0; k < N; k++) {
            el += temp[i][k] * A[k][j];
          }
          result[i][j] = el % 1000;
        }
      }
    }
    
    return result;
    
  }
}