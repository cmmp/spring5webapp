package br.cassio.spring.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import br.cassio.spring.spring5webapp.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
