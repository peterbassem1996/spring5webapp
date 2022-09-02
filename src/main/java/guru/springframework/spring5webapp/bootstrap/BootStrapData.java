package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher nyt = new Publisher("NYT", "99 Hillside Ave", "South River", "NJ", "08882");
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "456456");

        publisherRepository.save(nyt);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);
        authorRepository.save(eric);
        authorRepository.save(rod);


        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(nyt);
        nyt.getBooks().add(ddd);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(nyt);
        nyt.getBooks().add(noEJB);

        publisherRepository.save(nyt);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);
        authorRepository.save(eric);
        authorRepository.save(rod);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of Books published by NYT: " + nyt.getBooks().size());

    }
}
