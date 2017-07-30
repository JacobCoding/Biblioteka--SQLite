package BazyDanych;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qbala on 2017-07-13.
 */
public class BookSqliteDao implements BookDao {


    // Klasa w której zawarta jest funkcjonalnosć pozwalająca stworzyć bazę danych SQLite
    // oraz dodawanie to tej bazy obiektów typu Book zawierających 3 pola
    // - możliwe jest także usuwanie rekordów na podstawie tytułu
    // -

    private Connection connection;
    public BookSqliteDao() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:library.db");
           // createTable();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void createTable(){

        String sql = "CREATE TABLE IF NOT EXISTS Book("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"title TEXT,"
                +"author TEXT,"
                +"pages INTEGER"
                +")";
        Statement s = null;

        try{
            s = connection.createStatement();
            s.executeUpdate(sql);
        }catch (SQLException e){

            e.printStackTrace();
        }

    }

    public List<Book> findAll() {

        List<Book> books = new ArrayList<>();
        Statement s = null;   // to samo co Statement s = null;
        ResultSet resultSet;
        try{
            s = connection.createStatement();
            resultSet = s.executeQuery("SELECT * FROM Book");
            while (resultSet.next()){

                String author = resultSet.getString(2);
                String title = resultSet.getString(3);
                int pages = resultSet.getInt(4);

                // odczytujemy dane i tworzymy książkę i wrzucamy do arrayListy
                books.add(new Book(author,title,pages));

            }


        }catch (SQLException e){

            e.printStackTrace();
        }

        return books;
    }

    // INSERT INTO table_name (column1, column2, column3, ...)
    // VALUES (value1, value2, value3, ...);

    public void addBook(Book book) {

        String secondPart="('"+book.getTitle()+" ',' "+book.getAuthor()+" ', "+book.getPages()+")";
        String sql = "INSERT INTO Book (title, author, pages) VALUES" + secondPart;

        Statement s = null;   // to samo co Statement s = null;

        try{
            s = connection.createStatement();
            s.execute(sql);
        }catch (SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(String title) {



        String sql = "DELETE FROM Book WHERE title = ' "+title+" ' ";
        Statement s ;   // to samo co Statement s = null;

        try{
            s = connection.createStatement();
            s.executeUpdate(sql);
        }catch (SQLException e){

            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        BookSqliteDao bookSqliteDao = new BookSqliteDao();

        bookSqliteDao.createTable();

        bookSqliteDao.addBook(new Book("Gdzieś tam","Ela", 607));
        bookSqliteDao.addBook(new Book("Król","Karol", 719));

//        bookSqliteDao.deleteBook("Król");

        System.out.println(bookSqliteDao.findAll());

    }

}