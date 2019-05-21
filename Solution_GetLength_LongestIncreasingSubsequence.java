import java.util.Scanner;

public class Solution_GetLength_LongestIncreasingSubsequence {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sizeOfSequence = scanner.nextInt();
		int[] sequence = new int[sizeOfSequence];

		for (int i = 0; i < sizeOfSequence; i++) {
			sequence[i] = scanner.nextInt();
		}
		scanner.close();
		System.out.println(getLength_LongestIncreasingSubsequence(sequence));
	}

	/**
	 * A helper method for method "getLength_LongestIncreasingSubsequence".
	 * 
	 * If the current value of the sequence is greater than the lowest value
	 * (increasingValues[0]) and less than the highest value
	 * (increasingValues[length-1]) in working array "increasingValues", a binary
	 * search is applied so that we find its proper insertion index in this array.
	 * 
	 * @return insertion index.
	 */
	public static int insertionIndex(int[] tailTable, int lowerIndex, int upperIndex, int key) {
		while (upperIndex - lowerIndex > 1) {
			int middleIndex = lowerIndex + (upperIndex - lowerIndex) / 2;
			if (tailTable[middleIndex] >= key) {
				upperIndex = middleIndex;
			} else {
				lowerIndex = middleIndex;
			}
		}
		return upperIndex;
	}

	/**
	 * The methods calculates the length of the longest increasing subsequence. In
	 * order to get time complexity of (n*log(n)) a working array "increasingValues"
	 * and a helper method for binary search are applied.
	 * 
	 * In array "increasingValues", the current values are placed in increasing
	 * order as we iterate through the original sequence. Upon completion of the
	 * iteration, the number of the occupied slots in this array (value of "length")
	 * is the length of the longest increasing subsequence.
	 * 
	 * It should be noted that although the number of the occupied slots in array
	 * "increasingValues" corresponds to the longest increasing subsequence, the
	 * values within these slots do not necessarily represent the longest increasing
	 * subsequence.
	 * 
	 * This is so because new increasing subsequences could be initiated within the
	 * existing slots as we iterate through the original sequence.
	 * 
	 * See "Solution_GetValues_LongestIncreasingSubsequence" in this repository,
	 * that extracts the values of the longest increasing subsequence.
	 * 
	 * @return length of the longest increasing subsequence.
	 */
	public static int getLength_LongestIncreasingSubsequence(int[] sequence) {

		int[] increasingValues = new int[sequence.length];
		increasingValues[0] = sequence[0];
		int length = 1;

		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] < increasingValues[0]) {
				increasingValues[0] = sequence[i];
			} else if (sequence[i] > increasingValues[length - 1]) {
				increasingValues[length] = sequence[i];
				length++;
			} else {
				int index = insertionIndex(increasingValues, -1, length, sequence[i]);
				increasingValues[index] = sequence[i];
			}
		}
		return length;
	}
}
