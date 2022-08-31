package com.finder.properties;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class Properties {

	public static final int DEFAULT_K = 5;
	public static final Pattern SPACE_REG = Pattern.compile("\\s+");
	public static final Path INPUTFILEPATH = Path.of("src", "main", "resources", "input.txt");
	public static final Path OUTPUTFILEPATH = Path.of("src", "main", "resources", "output.txt");

}
