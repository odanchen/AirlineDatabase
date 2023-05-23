/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 15 May 2023
version #1
*/

package logic.sorting.flights;

import logic.data_record.FlightInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A FlightSorter class, used to sort flights in a specific order, determined by the comparator used with it.
 * The class is not meant to be instantiated because all of its methods are static.
 *
 * @author Oleksandr Danchenko
 */
public class FlightSorter {
    /**
     * A private constructor, prevents the class from being instantiated, because all of its methods are static.
     *
     * @author Oleksandr Danchenko
     */
    private FlightSorter() {

    }

    /**
     * A static method, creates a sublist of the given list in the given range.
     *
     * @param list  the list, from which a sublist would be taken.
     * @param begin the index of the first element in the list taken (inclusive).
     * @param end   the index of the last element in the list taken (non-inclusive).
     * @return a range of the list specified by the user
     * @author Oleksandr Danchenko
     */
    private static List<FlightInfo> subList(List<FlightInfo> list, int begin, int end) {
        List<FlightInfo> ans = new ArrayList<>(end - begin);
        for (int i = begin; i < end; i++) {
            ans.add(list.get(i));
        }
        return ans;
    }

    /**
     * Merges two sorted lists into a single sorted list.
     *
     * @param list1      the first list.
     * @param list2      the second list.
     * @param comparator a class which compares two flights.
     * @return a sorted list containing all elements of both lists provided.
     * @author Oleksandr Danchenko
     */
    private static List<FlightInfo> merge(List<FlightInfo> list1, List<FlightInfo> list2, FlightComparator comparator) {
        int idx1 = 0, idx2 = 0;
        List<FlightInfo> ans = new ArrayList<>(list1.size() + list2.size());
        while (ans.size() < list1.size() + list2.size()) {
            if (idx1 < list1.size() && (idx2 == list2.size() || comparator.compare(list1.get(idx1), list2.get(idx2)) <= 0)) {
                ans.add(list1.get(idx1++));
            } else {
                ans.add(list2.get(idx2++));
            }
        }
        return ans;
    }

    /**
     * Sorts the list by the given criteria.
     *
     * @param list       the list to be sorted.
     * @param comparator an object which acts as a way to compare elements in the list, the criteria for logic.sorting.
     * @return a list sorted using the given criteria.
     * @author Oleksandr Danchenko.
     */
    public static List<FlightInfo> sort(List<FlightInfo> list, FlightComparator comparator) {
        if (list.size() <= 1) return list;

        List<FlightInfo> list1 = subList(list, 0, list.size() / 2);
        List<FlightInfo> list2 = subList(list, list.size() / 2, list.size());

        return merge(sort(list1, comparator), sort(list2, comparator), comparator);
    }
}
