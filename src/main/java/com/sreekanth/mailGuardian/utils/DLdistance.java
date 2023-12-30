package com.sreekanth.mailGuardian.utils;

import java.util.Arrays;

public class DLdistance {

	/**
	 * Calculated the Damerau-Levenshtein-Distance.
	 *
	 * @param a              input string one.
	 * @param b              input string two.
	 * @param alphabetLength the length of the alphabet.
	 * @return the distance.
	 */

	public static int damerauLevenshteinDistance(String a, String b, int alphabetLength) {
		final int INFINITY = a.length() + b.length();
		int[][] H = new int[a.length() + 2][b.length() + 2];
		H[0][0] = INFINITY;
		for (int i = 0; i <= a.length(); i++) {
			H[i + 1][1] = i;
			H[i + 1][0] = INFINITY;
		}
		for (int j = 0; j <= b.length(); j++) {
			H[1][j + 1] = j;
			H[0][j + 1] = INFINITY;
		}
		int[] DA = new int[alphabetLength];
		Arrays.fill(DA, 0);
		for (int i = 1; i <= a.length(); i++) {
			int DB = 0;
			for (int j = 1; j <= b.length(); j++) {
				int i1 = DA[b.charAt(j - 1)];
				int j1 = DB;
				int d = ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1);
				if (d == 0)
					DB = j;
				H[i + 1][j + 1] = min(H[i][j] + d, H[i + 1][j] + 1, H[i][j + 1] + 1,
						H[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
			}
			DA[a.charAt(i - 1)] = i;
		}
		return H[a.length() + 1][b.length() + 1];
	}

	/**
	 * Helper function for getting the minimal value of a set of ints.
	 *
	 * @param nums The integer values which should been checked.
	 * @return the minimal value.
	 */
	private static int min(int... nums) {
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			min = min < num ? min : num;
		}
		return min;
	}
}
