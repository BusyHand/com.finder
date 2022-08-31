package com.finder.main;

import java.util.ArrayList;
import java.util.List;

import com.finder.algorithms.CompatibilityFinder;
import com.finder.util.ReaderRows;
import com.finder.util.WriterData;

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
 * @author Oditsov Kirill
 *
 */
public final class Main {

	private static final ReaderRows READER_ROWS = new ReaderRows();
	private static final CompatibilityFinder COMPATIBILITY_FINDER = new CompatibilityFinder();
	private static final WriterData WRITER_DATA = new WriterData();

	public static void main(String[] args) {
		
		List<String> firstInputList = new ArrayList<>();
		List<String> secondInputList = new ArrayList<>();

		READER_ROWS.readAndInputData(firstInputList, secondInputList);

		if (firstInputList.isEmpty() || secondInputList.isEmpty()) {
			throw new RuntimeException("One of the sets is empty");
		}

		List<String> сompatibilityToFirstList = COMPATIBILITY_FINDER.getCompatibilityList(firstInputList,
				secondInputList);

		WRITER_DATA.writeData(firstInputList, сompatibilityToFirstList);
	}

}
