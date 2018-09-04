package Management;

import java.util.HashMap;

import Logic.Directory;
import Logic.File;
import Logic.FileSystemEntity;

/**
 * @author Adva Duanias
 *
 */

public class FileSystemActions {

	private static FileEntitiesMap fileSystem = FileEntitiesMap.getInstance();

	/**
	 * addFile: 1.Validate that file or directory with the same name dosen't exist
	 * 2.Validate that parent directory exist 3.Create new file and add it under:
	 * parentDir and fileSystem.
	 * 
	 * @param parentDirName
	 * @param fileName
	 * @param fileSize
	 */
	public static void addFile(String parentDirName, String fileName, long fileSize) {
		// If file OR directory with the same name already exist - print error message.
		if ((isFileSystemEntityExist(fileName))) {
			System.out.println("File or directory with the same name already exist");
		}
		// If parent directory doesn't exist - print error message.
		else if (!(isFileSystemEntityExist(parentDirName))) {
			System.out.println("parentDirName doesn't exist");
		} else {
			// Create new file
			File fileToAdd = new File(parentDirName, fileName, fileSize);
			// Add file under parentDir
			Directory parentDir = (Directory) fileSystem.fileSystem.get(parentDirName);
			parentDir.addSubEntity(fileToAdd);
			// Add file to fileSystem map
			fileSystem.fileSystem.put(fileName, fileToAdd);
		}
	}

	/**
	 * addDir: 1. Validate that file or directory with the same name dosen't exist.
	 * 2. Validate that parentDirName is exist. 3. Create new directory, and add it
	 * under parentDir and fileSystem.
	 * 
	 * @param parentDirName
	 * @param dirName
	 */
	public static void addDir(String parentDirName, String dirName) {
		// If file OR directory with the same name already exist - print error message.
		if ((isFileSystemEntityExist(dirName))) {
			System.out.println("File or directory with the same name already exist");
		}
		// If parent directory doesn't exist return error message.
		else if (!isFileSystemEntityExist(parentDirName)) {
			System.out.print("ParentDir dosent exist");
		} else {
			// Get the parent directory
			Directory parentDir = (Directory) fileSystem.fileSystem.get(parentDirName);
			// Create new directory with the specified properties.
			Directory dirToAdd = new Directory(parentDirName, dirName);
			// Add the new directory under the parent directory
			parentDir.addSubEntity(dirToAdd);
			// Add the new directory to main fileSystem map
			fileSystem.fileSystem.put(dirName, dirToAdd);
		}
	}

	/**
	 * delete: 1.Validate that name exist in fileSystem. 2.Validate that name is not
	 * equal to Main. 3.Delete the fileSystemEntity from parentDir and fileSystem.
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public static void delete(String name) throws IllegalArgumentException {
		if (!(fileSystem.fileSystem.containsKey(name))) {
			throw new IllegalArgumentException("Name dosen't exist");
		} else if (name == FileEntitiesMap.MainDir) {
			System.out.print("Invalid action");
		} else {
			// Get parent directory for the specified file
			String parentDirName = fileSystem.fileSystem.get(name).getParentDirName();
			Directory parentDir = (Directory) fileSystem.fileSystem.get(parentDirName);
			// Delete file/directory from parent directory
			parentDir.removeSubEntity(name);
			// Delete file from fileSystem
			fileSystem.fileSystem.remove(name);
		}
	}

	/**
	 * showFileSytem: Displays all files & directories with their hierarchical
	 * structure
	 */
	public static void showFileSystem() {
		// Get the Main directory of the file system
		Directory mainDir = (Directory) fileSystem.fileSystem.get(FileEntitiesMap.MainDir);
		// Print title
		System.out.print("File System:" + "\n");
		// Print file system hierarchy (under main directory)
		tailRecursivePrint(mainDir, 0);
	}

	private static Boolean isFileSystemEntityExist(String name) {
		Boolean isFileSystemEntity;
		isFileSystemEntity = fileSystem.fileSystem.containsKey(name);
		return isFileSystemEntity;
	}

	/**
	 * Recursive print algorithm: 1. Get subentities of the given directory 2. If is
	 * empty - finish. 3. If not - for each: 3.1. If the current FileSytemEntity is
	 * a File object - print out. 3.2 Else,is a Directory object - print out inside
	 * "[]" , and reapeat steps 1-3 with subentities of the current directory
	 * (recursive call).
	 * 
	 * @param currentDir
	 * @param level
	 */
	private static void tailRecursivePrint(Directory currentDir, int level) {
		// Get subentities of the current directory
		HashMap<String, FileSystemEntity> subEntities = currentDir.getSubEntities();
		if (subEntities.isEmpty()) {
			return;
		}

		// Iterate over subentities: for each fileSystemEntity check if is a file or
		// directory

		for (String key : subEntities.keySet()) {
			FileSystemEntity currentFileSystemEntity = subEntities.get(key);
			// Print tabs for internal levels
			for (int i = 0; i < level; i++) {
				System.out.print("\t");
			}
			// If is a file - print file properties
			if (currentFileSystemEntity instanceof File) {
				System.out.print(currentFileSystemEntity.getName() + " " + currentFileSystemEntity.toString() + "\n");
			} else {
				// Else, is a directory - print directory properties
				System.out.println("[" + currentFileSystemEntity.getName() + "] " + currentFileSystemEntity.toString());
				// Recursive call with subentities of the current directory, and level + 1.
				tailRecursivePrint((Directory) currentFileSystemEntity, (level + 1));
			}

		}
	}
}
