package com.finder.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * The class responsible for establishing a link between two lists
 *
 */
public final class CompatibilityFinder {

	private final MatcherIndex MATCHER_INDEX = new MatcherIndex();

	/**
	 * Create a compatibility list to firstInputList, following the LEFT JOIN
	 * example in a database where NULL is the character '?'
	 *
	 * @param firstInputList  list of rows of the left column
	 * @param secondInputList list of rows of the right column
	 * @return compatibilityList list of rows that displays links to firstInputList
	 */
	public final List<String> getCompatibilityList(List<String> firstInputList, List<String> secondInputList) {

		List<String> сompatibilityToFirstList = new ArrayList<>();

		// Finding the best match, by using the O(N^2) algorithm
		for (int i = 0; i < firstInputList.size(); i++) {
			String outterElement = firstInputList.get(i);
			double maxMatch = 0;
			int resultJ = 0;

			for (int j = 0; j < secondInputList.size(); j++) {
				String innerElement = secondInputList.get(j);

				double currentMatch = MATCHER_INDEX.similarity(outterElement, innerElement);
				if (maxMatch < currentMatch) {
					maxMatch = currentMatch;
					resultJ = j;
				}
			}

			// Check duplicate
			if (сompatibilityToFirstList.contains(secondInputList.get(resultJ))) {

				// If a duplicate exists, then the comparison is the best match between the new
				// and old similarity index.
				int indexOf = сompatibilityToFirstList.indexOf(secondInputList.get(resultJ));
				double oldSimilarity = MATCHER_INDEX.similarity(firstInputList.get(indexOf),
						сompatibilityToFirstList.get(indexOf));
				double newSimilarity = MATCHER_INDEX.similarity(firstInputList.get(i), secondInputList.get(resultJ));

				if (oldSimilarity >= newSimilarity) {
					сompatibilityToFirstList.add("?");
				} else {
					сompatibilityToFirstList.set(indexOf, "?");
					сompatibilityToFirstList.add(secondInputList.get(resultJ));
				}

			} else {
				сompatibilityToFirstList.add(secondInputList.get(resultJ));
			}
		}
		return сompatibilityToFirstList;
	}

}
