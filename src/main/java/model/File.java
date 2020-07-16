package model;

public class File {

    private int idFile;
    private String  fileName;
    private String author;
    private String length;
    private String filePath;

    public File(int idFile, String fileName, String author, String length, String filePath) {
        this.idFile = idFile;
        this.fileName = fileName;
        this.author = author;
        this.length = length;
        this.filePath = filePath;
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "The audio file have the title " + fileName + ", author " + author + " and length " + length;
    }

}
