package BazyDanych;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qbala on 2017-07-12.
 */
public interface BookDao {
    List<Book> findAll();

   void addBook(Book book);

   void deleteBook(String title);

    // find all - pokaż wszystkie ksiązki w bazie danych

}
