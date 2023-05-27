/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 15 May 2023
version #1
*/

package logic.sorting.seats;

import logic.data_record.Seat;

import java.util.ArrayList;

/**
 * A SeatSorter class, used to sort seats in a specific order, determined by the comparator used with it.
 * The class is not meant to be instantiated because all of its methods are static.
 *
 * @author Oleksandr Danchenko
 */
public class SeatSorter {
    /**
     * A private constructor, prevents the class from being instantiated, because all of its methods are static.
     *
     * @author Oleksandr Danchenko
     */
    private SeatSorter() {

    }

    /**
     * A static method, creates a subarray of the given array in the given range.
     *
     * @param array  the array, from which a subarray would be taken.
     * @param begin the index of the first element in the array taken (inclusive).
     * @param end   the index of the last element in the array taken (non-inclusive).
     * @return a range of the array specified by the user
     * @author Oleksandr Danchenko
     */
    private static Seat[] subArray(Seat[] array, int begin, int end) {
        Seat[] ans = new Seat[end - begin];
        for (int i = begin; i < end; i++) {
            ans[i - begin] = array[i];
        }
        return ans;
    }

    /**
     * Merges two sorted arrays into a single sorted array.
     *
     * @param arr1      the first array.
     * @param arr2      the second array.
     * @param comparator a class which compares two seats.
     * @return a sorted array containing all elements of both arrays provided.
     * @author Oleksandr Danchenko
     */
    private static Seat[] merge(Seat[] arr1, Seat[] arr2, SeatComparator comparator) {
        int idx1 = 0, idx2 = 0, ansIdx = 0;
        Seat[] ans = new Seat[arr1.length + arr2.length];
        while (ansIdx < ans.length) {
            if (idx1 < arr1.length && (idx2 == arr2.length || comparator.compare(arr1[idx1], arr2[idx2]) <= 0)) {
                ans[ansIdx] = arr1[idx1++];
            } else {
                ans[ansIdx] = arr1[idx2++];
            }
        }
        return ans;
    }

    /**
     * Sorts the array by the given criteria.
     *
     * @param array       the array to be sorted.
     * @param comparator an object which acts as a way to compare elements in the array, the criteria for logic.sorting.
     * @return an array sorted using the given criteria.
     * @author Oleksandr Danchenko.
     */
    public static Seat[] sort(Seat[] array, SeatComparator comparator) {
        if (array.length <= 1) return array;

        Seat[] arr1 = subArray(array, 0, array.length / 2);
        Seat[] arr2 = subArray(array, array.length / 2, array.length);

        return merge(sort(arr1, comparator), sort(arr2, comparator), comparator);
    }

    public static Seat[] selectionSort(Seat[] arr, SeatComparator comparator, boolean isNumberComparator) {
        Seat[] newArr = new Seat[arr.length];
        int length;
        ArrayList<Integer> emptyIdx = new ArrayList<>();

        int idxCounter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].isEmpty()) {
                newArr[idxCounter] = arr[i];
                idxCounter++;
            } else {
                emptyIdx.add(i);
            }
        }
        int emptyIdxCounter = idxCounter;
        for (int idx :
                emptyIdx) {
            newArr[emptyIdxCounter] = arr[idx];
            emptyIdxCounter++;
        }

        arr = newArr;
        if (isNumberComparator) length = emptyIdxCounter;
        else length = idxCounter;

        for (int i = 0; i < length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < length; j++) {
                if (comparator.compare(arr[j], arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            Seat tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
        return arr;
    }
}
