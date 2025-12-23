import java.util.Scanner;
import java.util.Vector;

class GuessResponse {
    int guess;
    int numCircles;
    int numSquares;

    GuessResponse(int g, int nc, int ns) {
        this.guess = g;
        this.numCircles = nc;
        this.numSquares = ns;
    }
}

public class Main {

    static final int CASE_INSUFFICIENT_INFORMATION = 0;
    static final int CASE_SUFFICIENT_TO_GUESS_SECRET = 1;
    static final int CASE_ONE_ADDITIONAL_GUESS_SUFFICIENT = 2;

    static final byte[][] DIGITS = new byte[10000][4];
    static final byte[] COUNTS = new byte[10000 * 10];

    static {
        for (int x = 0; x < 10000; x++) {
            int a = x / 1000;
            int b = (x / 100) % 10;
            int c = (x / 10) % 10;
            int d = x % 10;

            DIGITS[x][0] = (byte) a;
            DIGITS[x][1] = (byte) b;
            DIGITS[x][2] = (byte) c;
            DIGITS[x][3] = (byte) d;

            COUNTS[x * 10 + a]++;
            COUNTS[x * 10 + b]++;
            COUNTS[x * 10 + c]++;
            COUNTS[x * 10 + d]++;
        }
    }

    private static int responseKey(int secret, int guess) {
        byte[] sd = DIGITS[secret];
        byte[] gd = DIGITS[guess];

        int circles = 0;
        if (sd[0] == gd[0]) circles++;
        if (sd[1] == gd[1]) circles++;
        if (sd[2] == gd[2]) circles++;
        if (sd[3] == gd[3]) circles++;

        int baseS = secret * 10;
        int baseG = guess * 10;

        int totalMatches = 0;
        for (int d = 0; d < 10; d++) {
            int cs = COUNTS[baseS + d] & 0xFF;
            int cg = COUNTS[baseG + d] & 0xFF;
            totalMatches += (cs < cg) ? cs : cg;
        }

        int squares = totalMatches - circles;
        return circles * 5 + squares;
    }

    private static int[] solveGuessingGameII(GuessResponse[] rounds) 
    {
        int[] ret = new int[2];

        int n = rounds.length;
        int[] guesses = new int[n];
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            guesses[i] = rounds[i].guess;
            expected[i] = rounds[i].numCircles * 5 + rounds[i].numSquares;
        }

        int[] candidates = new int[10000];
        int k = 0;
        for (int secret = 0; secret < 10000; secret++) {
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (responseKey(secret, guesses[i]) != expected[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) candidates[k++] = secret;
        }

        if (k == 1) {
            ret[0] = CASE_SUFFICIENT_TO_GUESS_SECRET;
            ret[1] = candidates[0];
            return ret;
        }

        if (k == 0 || k > 15) {
            ret[0] = CASE_INSUFFICIENT_INFORMATION;
            return ret;
        }

        int[] used = new int[25];
        for (int guess = 0; guess < 10000; guess++) {
            for (int i = 0; i < 25; i++) used[i] = -1;
            boolean collision = false;
            for (int i = 0; i < k; i++) {
                int key = responseKey(candidates[i], guess);
                if (used[key] != -1) {
                    collision = true;
                    break;
                }
                used[key] = i;
            }

            if (!collision) {
                ret[0] = CASE_ONE_ADDITIONAL_GUESS_SUFFICIENT;
                ret[1] = guess;
                return ret;
            }
        }

        ret[0] = CASE_INSUFFICIENT_INFORMATION;
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int numRounds = sc.nextInt();

            GuessResponse[] rounds = new GuessResponse[numRounds];

            for(int j = 0; j < numRounds; j++) {
                rounds[j] = new GuessResponse(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
            int[] ret = solveGuessingGameII(rounds);

            if(ret[0] == CASE_INSUFFICIENT_INFORMATION) {
                System.out.println("There is no guess that can uniquely identify the secret.");
            } else {
                if(ret[0] == CASE_SUFFICIENT_TO_GUESS_SECRET) {
                    System.out.println("The secret is: " + String.format("%04d", ret[1]));
                } else {
                    System.out.println("There is a guess that can uniquely identify the secret: " + String.format("%04d", ret[1]));
                }
            }
        }
    }
}