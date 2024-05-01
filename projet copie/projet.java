package projet;

import java.util.Arrays;

public class Projet {

    public static int glouton(int[][] G, int d) {
        int L = G.length, C = G[0].length, P = 0, l = 0, c = d;
        
        while (l < L - 1) {
            P += G[l][c];
            int maxC = c, maxPucerons = G[l + 1][c];

            for (int col = c - 1; col <= c + 1; col++) {
                if (col >= 0 && col < C && G[l + 1][col] > maxPucerons) {
                    maxPucerons = G[l + 1][col];
                    maxC = col;
                }
            }
            c = maxC;
            l++;
        }
        P += G[L - 1][c];
        return P;
    }

    public static int[] glouton(int[][] G) {
        int C = G[0].length;
        int[] Ng = new int[C];

        for (int d = 0; d < C; d++) {
            Ng[d] = glouton(G, d);
        }
        return Ng;
    }

    public static int[][][] calculerMA(final int[][] G, final int d) {
        final int L = G.length, C = G[0].length;
        final int[][] M = new int[L][C], A = new int[L][C];

        for (int c = 0; c < C; c++) {
            M[0][c] = m(G, 0, c);
        }

        for (int l = 1; l < L; l++) {
            for (int c = 0; c < C; c++) {
                int maxP = m(G, l, c), maxC = c;

                for (int col = c - 1; col <= c + 1; col++) {
                    if (col >= 0 && col < C) {
                        int P = m(G, l - 1, col);
                        if (P > maxP) {
                            maxP = P;
                            maxC = col;
                        }
                    }
                }
                M[l][c] = maxP;
                A[l][c] = maxC;
            }
        }
        return new int[][][]{M, A};
    }

    public static void acnpm(int[][] A, int l, int c) {
        if (l == 0) System.out.println("(" + l + ", " + c + ")");
        else {
            System.out.println("(" + l + ", " + c + ")");
            acnpm(A, l - 1, A[l][c]);
        }
    }

    public static void acnpm(int[][] M, int[][] A) {
        int L = M.length, cStar = argMax(M[L - 1]);
        acnpm(A, L - 1, cStar);
    }

    public static int optimal(int[][] G, int d) {
        int L = G.length, C = G[0].length;
        int[][] dp = new int[L][C];

        for (int i = 0; i < C; i++) dp[0][i] = G[0][i];

        for (int i = 1; i < L; i++) {
            for (int j = 0; j < C; j++) {
                int maxPucerons = dp[i - 1][j];

                for (int k = -1; k <= 1; k++) {
                    int col = j + k;
                    if (col >= 0 && col < C) {
                        maxPucerons = Math.max(maxPucerons, dp[i - 1][col]);
                    }
                }
                dp[i][j] = G[i][j] + maxPucerons;
            }
        }
        int maxPucerons = Arrays.stream(dp[L - 1]).max().orElse(0);
        return maxPucerons;
    }

    public static int[] optimal(int[][] G) {
        int C = G[0].length;
        int[] Nmax = new int[C], tempResult = new int[C];

        for (int d = 0; d < C; d++) {
            int L = G.length;
            int[][] dp = new int[L][C];

            for (int i = 0; i < C; i++) dp[0][i] = G[0][i];

            for (int i = 1; i < L; i++) {
                for (int j = 0; j < C; j++) {
                    int maxPucerons = dp[i - 1][j];

                    for (int k = -1; k <= 1; k++) {
                        int col = j + k;
                        if (col >= 0 && col < C) {
                            maxPucerons = Math.max(maxPucerons, dp[i - 1][col]);
                        }
                    }
                    dp[i][j] = G[i][j] + maxPucerons;
                }
            }
            tempResult[d] = Arrays.stream(dp[L - 1]).max().orElse(0);
        }
        System.arraycopy(tempResult, 0, Nmax, 0, C);
        return Nmax;
    }

    public static float[] gainRelatif(int[] Nmax, int[] Ng) {
        int C = Nmax.length;
        float[] Gain = new float[C];

        for (int d = 0; d < C; d++) {
            int Ngi = (Ng[d] != 0) ? Ng[d] : 1;
            Gain[d] = (float) (Nmax[d] - Ng[d]) / Ngi;
        }
        return Gain;
    }

    public static void main(String[] args) {
        int[][] G = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int dQuestion1 = 1, gloutonResult = glouton(G, dQuestion1);

        System.out.println("Question 1 - Nombre de pucerons pour le chemin glouton : " + gloutonResult);

        int[] Ng = glouton(G);
        System.out.println("Question 2 - Nombre de pucerons pour chaque case de départ (glouton) : " + Arrays.toString(Ng));

        int[][][] MA = calculerMA(G, dQuestion1);
        int[][] A = MA[1];

        System.out.println("Question 4 - Affichage du chemin optimal : ");
        acnpm(A, G.length - 1, argMax(MA[0][MA[0].length - 1]));

        int lQuestion5 = G.length - 1, cQuestion5 = argMax(MA[0][lQuestion5]);
        System.out.println("Question 5 - Affichage du chemin optimal (dernière ligne) : ");
        acnpm(A, lQuestion5, cQuestion5);

        int optimalResult = optimal(G, dQuestion1);
        System.out.println("Question 6 - Nombre de pucerons pour le chemin optimal : " + optimalResult);

        int[] Nmax = optimal(G);
        float[] gainRelatifResult = gainRelatif(Nmax, Ng);
        System.out.println("Question 7 - Gain relatif pour chaque case de départ : " + Arrays.toString(gainRelatifResult));

        System.out.println("Question 8 - Affichage du gain relatif pour chaque case de départ : ");
        float[] gainRelatifResultQ8 = gainRelatif(Nmax, Ng);
        for (int i = 0; i < gainRelatifResultQ8.length; i++) {
            System.out.println("Case " + i + ": " + gainRelatifResultQ8[i]);
        }
    }

    private static int argMax(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
