package com.example.project.docs.api;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * An utility class which writes a list of strings to a file, with one entry on
 * a line.
 *
 */

public class ListWriter {

	private static final Logger _logger = Logger.getLogger(ListWriter.class.getName());

	private PrintWriter _writer;

	public ListWriter(String pathToFile) {
		try {
			_writer = new PrintWriter(new FileWriter(pathToFile));
		} catch (Exception e) {
			_logger.fine("Exception "+ e.getMessage());
		}
	}

	public ListWriter(String pathToFile, boolean append) {
		try {
			_writer = new PrintWriter(new FileWriter(pathToFile, append));
		} catch (Exception e) {
			_logger.fine("Exception "+ e.getMessage());
		}
	}

	public ListWriter(File f, boolean append) {
		try {
			_writer = new PrintWriter(new FileWriter(f, append));
		} catch (Exception e) {
			_logger.fine("Exception "+ e.getMessage());
		}
	}

	public ListWriter(Path path) {
		this(path.toFile(),true);
	}

	public void close() {
		if (_writer != null)
			_writer.close();
	}

	/**
	 * Write a line
	 * 
	 * @param line
	 */
	public void appendLine(String line) {
		_writer.println(line);
	}

	public void write(String... values) {
		for (String val:values) {
			_writer.println(val);
		}
	}
	
	
}
