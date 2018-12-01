package db;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String year;

    public Book(String _isbn, String _title, String _author, String _year) {
        this.isbn = _isbn;
        this.title = _title;
        this.author = _author;
        this.year = _year;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getYear() {
        return year;
    }

    public void setIsbn(String _isbn) {
        isbn = _isbn;
    }
    public void setTitle(String _title) {
        isbn = _title;
    }
    public void setAuthor(String _author) {
        isbn = _author;
    }
    public void setYear(String _year) {
        isbn = _year;
    }

}