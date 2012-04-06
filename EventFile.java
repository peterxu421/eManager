public class EventFile {
	private int fileID;
	private String fileName;
	private String fileDirectory;
	private String fileDescription;

	public EventFile(int fileID, String fileName, String fileDirectory,
			String fileDescription) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.fileDirectory = fileDirectory;
		this.fileDescription = fileDescription;
	}

	public EventFile(String fileName, String fileDirectory,
			String fileDescription) {
		super();
		this.fileName = fileName;
		this.fileDirectory = fileDirectory;
		this.fileDescription = fileDescription;
	}

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
}
