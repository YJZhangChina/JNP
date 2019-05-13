package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PathUtil {

	public static Stream<Path> list(Path path) {
		try {
			return Files.list(path);
		} catch (IOException e) {
			throw new RuntimeException(path.toAbsolutePath() + " not exists.");
		}
	}

	public static Stream<Path> list(Path path, String prefix) {
		return list(path).filter(file -> file.getFileName().toString().startsWith(prefix));
	}

}
