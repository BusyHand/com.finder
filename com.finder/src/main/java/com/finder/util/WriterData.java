package com.finder.util;

import static com.finder.properties.Properties.OUTPUTFILEPATH;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * The class that is responsible for writing data to a file and console
 *
 */
public final class WriterData {

	/**
	 * Write data to a file and output to the console, following the LEFT JOIN
	 * example in a database where NULL is the character '?'
	 *
	 * @param firstInputList           list of rows of the left column
	 * @param сompatibilityToFirstList list of rows of the right column
	 */
	public final void writeData(List<String> firstInputList, List<String> сompatibilityToFirstList) {
		try {
			Files.deleteIfExists(OUTPUTFILEPATH);
			Files.createFile(OUTPUTFILEPATH);
			try (PrintWriter printWriter = new PrintWriter(
					Files.newBufferedWriter(OUTPUTFILEPATH, StandardCharsets.UTF_8))) {
				System.out.println("ouput.txt");
				for (int i = 0; i < firstInputList.size(); i++) {
					String element1 = firstInputList.get(i);
					String element2 = сompatibilityToFirstList.get(i);
					System.out.println(element1 + ":" + element2);
					printWriter.println(element1 + ":" + element2);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Произошла ошибка во время записи данных", e);
		}

	}

}
