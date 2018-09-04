package Logic;

import java.util.Date;

/**
 * @author Adva Duanias
 *
 */

public class FileSystemEntity {
	private String parentDirName;
	private String name;
	private Date createDate;

	public FileSystemEntity(String parentDirName, String name) {
		// Validate that name is not null and is up to 32 characters.
		setName(name);
		// Validate that parentDirName is not null and up to 32 characters.
		setParentDirName(parentDirName);
		// Initialise the createDate to be the current date and time.
		this.createDate = new Date();
	}

	/**
	 * Validate that name is accordingly to requirements:up to 32 characters and not
	 * null.
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name.length() > 32 || name == null) {
			throw new IllegalArgumentException("Not valid name");
		} else {
			this.name = name;
		}
	}
	
	/**
	 * Validate that parentDirName is accordingly to requirements:up to 32 characters and not
	 * null.
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 */
	
	private void setParentDirName(String parentDirName) throws IllegalArgumentException {
		if (name.length() > 32 || name == null) {
			throw new IllegalArgumentException("Not valid name");
		} else {
			this.parentDirName = parentDirName;
		}
	}


	public String getParentDirName() {
		return this.parentDirName;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return createDate
	 */
	public Date getcreateDate() {
		return this.createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSystemEntity other = (FileSystemEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
