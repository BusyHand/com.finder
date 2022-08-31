package com.finder.algorithms;

import static com.finder.properties.Properties.DEFAULT_K;
import static com.finder.properties.Properties.SPACE_REG;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for string similarities that rely on set operations (like cosine
 * similarity or jaccard index).
 *
 * k-shingling is the operation of transforming a string (or text document) into
 * a set of n-grams, which can be used to measure the similarity between two
 * strings or documents.
 *
 * Generally speaking, a k-gram is any sequence of k tokens. We use here the
 * definition from Leskovec, Rajaraman &amp; Ullman (2014), "Mining of Massive
 * Datasets", Cambridge University Press: Multiple subsequent spaces are
 * replaced by a single space, and a k-gram is a sequence of k characters.
 *
 * Default value of k is 3. A good rule of thumb is to imagine that there are
 * only 20 characters and estimate the number of k-shingles as 20^k. For small
 * documents like e-mails, k = 5 is a recommended value. For large documents,
 * such as research articles, k = 9 is considered a safe choice.
 *
 *
 */
public final class MatcherIndex {


	/**
	 * Similarity is computed as 2 * |A inter B| / (|A| + |B|).
	 *
	 * @param s1 The first string to compare.
	 * @param s2 The second string to compare.
	 * @return The computed Sorensen-Dice similarity.
	 */
	public final double similarity(final String s1, final String s2) {
		if (s1.equals(s2)) {
			return 1;
		}

		Map<String, Integer> profile1 = getProfile(s1);
		Map<String, Integer> profile2 = getProfile(s2);

		Set<String> union = new HashSet<String>();
		union.addAll(profile1.keySet());
		union.addAll(profile2.keySet());

		int inter = 0;

		for (String key : union) {
			if (profile1.containsKey(key) && profile2.containsKey(key)) {
				inter++;
			}
		}

		return 2.0 * inter / (profile1.size() + profile2.size());
	}

	/**
	 * Compute and return the profile of s, as defined by Ukkonen "Approximate
	 * string-matching with q-grams and maximal matches".
	 * https://www.cs.helsinki.fi/u/ukkonen/TCS92.pdf The profile is the number of
	 * occurrences of k-shingles, and is used to compute q-gram similarity, Jaccard
	 * index, etc. Pay attention: the memory requirement of the profile can be up to
	 * k * size of the string
	 *
	 * @param string
	 * @return the profile of this string, as an unmodifiable Map
	 */
	private final Map<String, Integer> getProfile(final String string) {
		HashMap<String, Integer> shingles = new HashMap<String, Integer>();

		String string_no_space = SPACE_REG.matcher(string).replaceAll(" ");
		for (int i = 0; i < (string_no_space.length() - DEFAULT_K + 1); i++) {
			String shingle = string_no_space.substring(i, i + DEFAULT_K);
			Integer old = shingles.get(shingle);
			if (old != null) {
				shingles.put(shingle, old + 1);
			} else {
				shingles.put(shingle, 1);
			}
		}

		return Collections.unmodifiableMap(shingles);
	}

}
