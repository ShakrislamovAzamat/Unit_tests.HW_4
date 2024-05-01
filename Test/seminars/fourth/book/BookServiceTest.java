package seminars.fourth.book;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BookServiceTest {


    @Test
    public void TestFindBookByID() {

        //Создаем заглушку
        BookRepository mockBookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockBookRepository);

        //Задаем поведение заглушки с аргументом 1
        when(mockBookRepository.findById("1")).thenReturn(new Book("1", "Book1", "Author1"));

        //Получаем данные книги при вызове метода findBookByID
        Book actualIdBook = bookService.findBookById("1");

        //Проверяем, что был вызван метод findByID с аргументом 1 один раз
        verify(mockBookRepository, times(1)).findById("1");

        //проверки

        assertNotNull(actualIdBook);
        assertEquals("1", actualIdBook.getId());
        assertEquals("Book1", actualIdBook.getTitle());
        assertEquals("Author1", actualIdBook.getAuthor());
    }

    @Test
    public void TestFindAllBooks() {

        //Создаем заглушку
        BookRepository mockBookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(mockBookRepository);

        //Задаем поведение
        List<Book> expectedBooks = java.util.Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2"));
        when(mockBookRepository.findAll()).thenReturn(expectedBooks);

        //Получаем список книг вызывая метод findAll
        List<Book> actualBooks = bookService.findAllBooks();

        //Проверяем, что был вызван метод findAll  один раз
        verify(mockBookRepository, times(1)).findAll();

        //Проверки
        assertNotNull(actualBooks);
        assertEquals(expectedBooks, actualBooks);
    }
}