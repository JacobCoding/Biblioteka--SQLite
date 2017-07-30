package BazyDanych;

/**
 * Created by Qbala on 2017-07-12.
 */
public class Book {


    private String title;
    private String author;
    private int pages;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }


    public Book(String title, String author,int pages ) {

        this.title = title;
        this.author = author;
        this.pages = pages;
    }



    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return getTitle() + " , " +getAuthor()+" , " + getPages();
    }
}
