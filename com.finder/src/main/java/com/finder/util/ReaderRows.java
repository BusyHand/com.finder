package com.finder.util;

import static com.finder.properties.Properties.INPUTFILEPATH;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


/**
 * The class responsible for reading the lines of a file and storing them in columns
 *
 */
public final class ReaderRows {


	/**
	 * Reading the lines of the file that the client will enter, the first input for
	 * firstInputList, the second for the secondInputList.
	 *
	 * @param firstInputList  list of rows of the first column
	 * @param secondInputList list of rows of the second column
	 */

	public final void readAndInputData(List<String> firstInputList, List<String> secondInputList) {
		try (Scanner inputScanner = new Scanner(System.in);
				Scanner fileScanner = new Scanner(INPUTFILEPATH, StandardCharsets.UTF_8)) {
			int countColumn = 0;
			int rowsCount = 0;

			System.out.println("input.txt");
			while (countColumn < 2) {
				countColumn++;
				if (inputScanner.hasNext()) {
					rowsCount = inputScanner.nextInt();
				}

				while (fileScanner.hasNextLine() && rowsCount > 0) {
					rowsCount--;
					String nextRow = fileScanner.nextLine();
					if (countColumn == 1) {
						firstInputList.add(nextRow);
					} else {
						secondInputList.add(nextRow);
					}
				}

				// Write delimiters of lists
				if (countColumn == 1) {
					firstInputList.forEach(System.out::println);
				} else {
					secondInputList.forEach(System.out::println);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Произошла ошибка во время чтения данных", e);
		}
	}

}
