package BazyDanych;

/**
 * Created by Qbala on 2017-07-12.
 */
public class Library {

    private BookDao bookDao;

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public static void main(String[] args) {

        Library library = new Library();
        library.setBookDao(new BookSqliteDao());

        System.out.println(library.getBookDao().findAll());






//        BookDao bookDao = new BookFileDao();
//        System.out.println("Wypisane wszystkie książki");
//        System.out.println(bookDao.findAll());



    }




}
