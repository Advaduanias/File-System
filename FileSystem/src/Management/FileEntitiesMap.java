package Management;

import java.util.HashMap;
import Logic.*;

/**
 * @author Adva Duanias
 *
 */
public class FileEntitiesMap {
	HashMap<String, FileSystemEntity> fileSystem;
	private static FileEntitiesMap instance;
	public static String MainDir = "Main";

	private FileEntitiesMap() {
		fileSystem = new HashMap<String, FileSystemEntity>();
		Directory main = new Directory(" ", "Main");
		fileSystem.put(MainDir, main);
	}

	public static FileEntitiesMap getInstance() {
		if (instance == null) {
			synchronized (FileEntitiesMap.class) {
				if (instance == null) {
					instance = new FileEntitiesMap();
				}
			}
		}

		return instance;
	}
}
