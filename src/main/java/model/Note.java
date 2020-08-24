package model;

public class Note {

    private File file;
    private String note;
    private int startTime;
    private int endTime;

    public Note(File file, String note, int startTime, int endTime) {
        this.file = file;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /*Metode de tip getter si setter necesare in cadrul aplicatiei*/
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getNote() {
        return note;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return file.toString() + " has the note " + note + " that starts at " + startTime + " and ends at " + endTime;
    }
}
