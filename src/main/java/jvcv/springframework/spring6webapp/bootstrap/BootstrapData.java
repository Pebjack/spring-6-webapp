package jvcv.springframework.spring6webapp.bootstrap;

import jvcv.springframework.spring6webapp.domain.Author;
import jvcv.springframework.spring6webapp.domain.Book;
import jvcv.springframework.spring6webapp.domain.Publisher;
import jvcv.springframework.spring6webapp.repositories.AuthorRepository;
import jvcv.springframework.spring6webapp.repositories.BookRepository;
import jvcv.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        ddd.setIsbn("527852");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        Publisher pub1 = new Publisher();

        pub1.setPublisherName("NiceBook Publishers");
        pub1.setAddress("1865 Novak Street");
        pub1.setCity("Vancouver");
        pub1.setState("Ontario idk never been to Canada");
        pub1.setZip("90210");

        Publisher pub1Saved = publisherRepository.save(pub1);
        publisherRepository.save(pub1Saved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());
        System.out.println("Publisher Count:" + publisherRepository.count());

    }
}
