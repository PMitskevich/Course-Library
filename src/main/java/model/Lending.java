package model;

import java.time.LocalDate;

public class Lending {
    private long lendingId;
    private long bookId;
    private long visitorId;
    private long librarianId;
    private LocalDate lendDate;
    private LocalDate returnDate;
    private int lendQuantity;

    public long getLendingId() { return lendingId; }

    public void setLendingId(long lendingId) { this.lendingId = lendingId; }

    public long getBookId() { return bookId; }

    public void setBookId(long bookId) { this.bookId = bookId; }

    public long getVisitorId() { return visitorId; }

    public void setVisitorId(long visitorId) { this.visitorId = visitorId; }

    public long getLibrarianId() { return librarianId; }

    public void setLibrarianId(long librarianId) { this.librarianId = librarianId; }

    public LocalDate getLendDate() { return lendDate; }

    public void setLendDate(LocalDate lendDate) { this.lendDate = lendDate; }

    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public int getLendQuantity() { return lendQuantity; }

    public void setLendQuantity(int lendQuantity) { this.lendQuantity = lendQuantity; }
}
