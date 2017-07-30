package BazyDanych;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Qbala on 2017-07-12.
 */


// Klasa wyświetlająca książki znajdujące się w pliku books.txt (znajduje się w katalogu projektu - nie może być
// wrzucony do żadnego pakunku) - scieżka C:\Users\Qbala\IdeaProjects\Exeptions8day\books.txt
// na samym dole mamy maina do testu


public class BookFileDao implements BookDao {

    private static final String FILE_NAME = "books.txt";

    @Override
    public List<Book> findAll() {

        String fileName = FILE_NAME; // można zmienić na "books.txt" (tutaj dry)
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            //1. filter line 3
            //2. convert all content to upper case
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("line3"))
                    .map(String::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Book> books = new ArrayList<>();
        for (String line : list) {
            String[] names = line.split(",");
            Integer pages= Integer.parseInt(names[2].trim());
            Book book = new Book(names[0], names[1], pages);
            books.add(book);
        }

        return books;

    }

    @Override
    public void addBook(Book book) {
        String text = book.toString() + "\n";
        try {
            Files.write(Paths.get(FILE_NAME), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteBook(String title) {

    }

    public static void main(String[] args) {

        BookDao bookDao = new BookFileDao();

        for (Book book : bookDao.findAll()){
            System.out.println(book);
        }

    }


}



