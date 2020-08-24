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

    public File(String fileName, String author, String length, String filePath) {
        this.fileName = fileName;
        this.author = author;
        this.length = length;
        this.filePath = filePath;
    }

    /*Metode de tip getter si setter necesare in cadrul aplicatiei*/

    public int getIdFile() {
        return idFile;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAuthor() {
        return author;
    }

    public String getLength() {
        return length;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object obj) {
        return ((File) obj).getIdFile() == this.getIdFile();
    }

    @Override
    public String toString() {
        return  fileName + ", author " + author + " and length " + length;
    }

}
