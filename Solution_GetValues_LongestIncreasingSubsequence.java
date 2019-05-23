import java.util.Arrays;
import java.util.Scanner;

public class Solution_GetValues_LongestIncreasingSubsequence {
	private static int length_LongestIncreasingSubsequence;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int sizeOfSequence = scanner.nextInt();
		int[] sequence = new int[sizeOfSequence];
		for (int i = 0; i < sizeOfSequence; i++) {
			sequence[i] = scanner.nextInt();
		}
		scanner.close();

		int[] results = getValues_LongestIncreasingSubsequence(sequence);
		printResults(results);
	}

	/**
	 * A helper method for method
	 * "getIndexesOfValuesForLongestIncreasingSubsequence".
	 * 
	 * If the current value of the sequence is greater than the previous lowest
	 * value (sequence[index_increasingValues[0]]) and less than the previous
	 * highest value (sequence[index_increasingValues[length-1]]), a binary search
	 * is applied so that we find its proper insertion index in the working array
	 * "index_increasingValues[length-1]".
	 * 
	 * @return insertion index.
	 */
	public static int insertionIndex(int[] sequence, int[] index, int lowerIndex, int upperIndex, int key) {
		while (upperIndex - lowerIndex > 1) {
			int middleIndex = lowerIndex + (upperIndex - lowerIndex) / 2;
			if (sequence[index[middleIndex]] >= key) {
				upperIndex = middleIndex;
			} else {
				lowerIndex = middleIndex;
			}
		}
		return upperIndex;
	}

	/**
	 * The method finds the indexes of values of the longest increasing subsequence
	 * and stores them in array "trackBackIndexes".
	 * 
	 * In order to get time complexity of (n*log(n)) working arrays
	 * "index_increasingValues" and "trackBackIndexes", as well as a helper method
	 * for binary search, are applied.
	 * 
	 * In array "index_increasingValues", the indexes are placed as per increasing
	 * order of the current values, as we iterate through the original sequence.
	 * 
	 * Array "trackBackIndexes" allows to trace back the values of the longest
	 * increasing subsequence, starting from the highest value. This array has a
	 * length that equals "sequence.length + 1" because one slot is preserved for
	 * the value of "-1". The algorithm stops the tracking back of the values of the
	 * subsequence when the value of "-1" is reached.
	 * 
	 * @return Array "trackBackIndexes".
	 */
	public static int[] getIndexesOfValuesForLongestIncreasingSubsequence(int[] sequence) {
		int[] index_increasingValues = new int[sequence.length];
		int[] trackBackIndexes = new int[sequence.length + 1];
		Arrays.fill(trackBackIndexes, -1);
		int length = 1;

		for (int i = 1; i < sequence.length; i++) {
			if (sequence[i] <= sequence[index_increasingValues[0]]) {
				index_increasingValues[0] = i;
			} else if (sequence[i] > sequence[index_increasingValues[length - 1]]) {
				trackBackIndexes[i] = index_increasingValues[length - 1];
				index_increasingValues[length] = i;
				length++;
			} else {
				int position = insertionIndex(sequence, index_increasingValues, -1, length - 1, sequence[i]);
				trackBackIndexes[i] = index_increasingValues[position - 1];
				index_increasingValues[position] = i;
			}
		}
		trackBackIndexes[trackBackIndexes.length - 1] = index_increasingValues[length - 1];
		length_LongestIncreasingSubsequence = length;

		return trackBackIndexes;
	}

	/**
	 * The methods extracts the values of the longest increasing subsequence,
	 * starting from the highest value, and stores the results in increasing order
	 * in an array "longestIncreasingSubsequence".
	 * 
	 * @return longestIncreasingSubsequence.
	 */
	public static int[] getValues_LongestIncreasingSubsequence(int[] sequence) {
		int[] trackBackIndexes = getIndexesOfValuesForLongestIncreasingSubsequence(sequence);
		int[] longestIncreasingSubsequence = new int[length_LongestIncreasingSubsequence];
		int index = length_LongestIncreasingSubsequence - 1;

		for (int i = trackBackIndexes[trackBackIndexes.length - 1]; i >= 0; i = trackBackIndexes[i]) {
			longestIncreasingSubsequence[index] = sequence[i];
			index--;
		}
		return longestIncreasingSubsequence;
	}

	/**
	 * Print the values of the longest increasing subsequence on one line.
	 */
	public static void printResults(int[] results) {
		for (int i = 0; i < results.length; i++) {
			System.out.print(results[i] + " ");
		}
	}
}
