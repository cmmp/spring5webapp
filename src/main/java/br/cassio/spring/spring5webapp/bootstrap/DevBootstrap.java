package br.cassio.spring.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.cassio.spring.spring5webapp.model.Author;
import br.cassio.spring.spring5webapp.model.Book;
import br.cassio.spring.spring5webapp.model.Publisher;
import br.cassio.spring.spring5webapp.repositories.AuthorRepository;
import br.cassio.spring.spring5webapp.repositories.BookRepository;
import br.cassio.spring.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	private void initData() {
		Author eric = new Author("Eric", "Evans");
		Publisher harper = new Publisher("Harper Collins", "New York");
		Book ddd = new Book("Domain driven design", "1234", harper);

		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		publisherRepository.save(harper);
		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("Rod", "Johnson");
		Publisher abcd = new Publisher("abcd", "London");
		Book noEJB = new Book("J2EE development without EJB", "4321", abcd);
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		publisherRepository.save(abcd);
		bookRepository.save(noEJB);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

}
