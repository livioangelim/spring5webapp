package guru.springframework.spring5webapp.bootstrap;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    //properties
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //constructor to initialize the properties
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Add author
        Author eric = new Author("Eric", "Evans");
        //Add book
        Book ddd = new Book("Domain Driven Design", "123123");


        authorRepository.save(eric); //save author before adding and saving book
        eric.getBooks().add(ddd); //input the book from this author
        ddd.getAuthors().add(eric); //input the author from this book
        bookRepository.save(ddd); //then save the book

        //add another author
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");

        authorRepository.save(rod);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        bookRepository.save(noEJB);

        //print to test the functionality
        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }

}
