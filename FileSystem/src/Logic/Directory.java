package Logic;

import java.util.HashMap;

/**
 * @author Adva Duanias
 *
 */
public class Directory extends FileSystemEntity {
	private HashMap<String, FileSystemEntity> subEntities;

	public Directory(String parentDirName, String dirName) {
		super(parentDirName, dirName);
		subEntities = new HashMap<String, FileSystemEntity>();
	}

	/**
	 * Add new file or directory under this directory.
	 * 
	 * @param fileSystemToAdd
	 */
	public void addSubEntity(FileSystemEntity fileSystemToAdd) {
		subEntities.put(fileSystemToAdd.getName(), fileSystemToAdd);
	}

	/**
	 * Remove file or directory under this directory.
	 * 
	 * @param name
	 */
	public void removeSubEntity(String name) {
		subEntities.remove(name);
	}

	/**
	 * @return HashMap of all files and directories under this directory.
	 */
	public HashMap<String, FileSystemEntity> getSubEntities() {
		return this.subEntities;
	}
	
	@Override
	public String toString() {
		StringBuilder fileSystemEntityProperties = new StringBuilder();
		fileSystemEntityProperties.append("[Properties: ");
		fileSystemEntityProperties.append("Name: " + getName() + ", ");
		fileSystemEntityProperties.append("ParentDirName: " + getParentDirName() + ", ");
		fileSystemEntityProperties.append("Create date:" + getcreateDate() + "] ");

		return fileSystemEntityProperties.toString();
	}
}
