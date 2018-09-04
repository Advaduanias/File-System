package Logic;

/**
 * @author Adva Duanias
 *
 */
public class File extends FileSystemEntity {
	private long fileSize;

	public File(String parentDirName, String fileName, long fileSize) {
		super(parentDirName, fileName);
		// Validate that file size is positive long integer and set it to the given
		// fileSize.
		setFileSize(fileSize);
	}

	public long getFileSize() {
		return this.fileSize;
	}

	/**
	 * Private method that used to validate file size is accordingly to
	 * requirements, and if it is set it to the given size.
	 * 
	 * @param fileSize
	 * @throws IllegalArgumentException
	 */
	private void setFileSize(long fileSize) {
		if (fileSize < 0) {
			throw new IllegalArgumentException("fileSize is not valid" + "\n" + "Please insert positive fileSize");
		}
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		StringBuilder fileProperties = new StringBuilder();
		fileProperties.append("[" + "File properties: ");
		fileProperties.append("Name: " + getName() + ", ");
		fileProperties.append("Create date: " + getcreateDate() + ", ");
		fileProperties.append("Size: " + getFileSize() + ", ");
		fileProperties.append("ParentDirName: " + getParentDirName() + "]");
		return fileProperties.toString();
	}
}
