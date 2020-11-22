package model;

public class Book {
    private long bookId;
    private long authorId;
    private long publishingId;
    private String name;
    private String genre;
    private int quantity;

    public long getBookId() { return bookId; }

    public void setBookId(long bookId) { this.bookId = bookId; }

    public long getAuthorId() { return authorId; }

    public void setAuthorId(long authorId) { this.authorId = authorId; }

    public long getPublishingId() { return publishingId; }

    public void setPublishingId(long publishingId) { this.publishingId = publishingId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
