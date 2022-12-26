package consulting.sit.catenax.controller;

import consulting.sit.catenax.model.ModelBaseInterface;
import consulting.sit.catenax.repository.GenericRepository;
import consulting.sit.catenax.service.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

@Component
public abstract class GenericController<T extends ModelBaseInterface<I>, I extends Serializable> {
    public GenericService<T, I> service;

    public void init(GenericRepository<T, I> repository) {
        service = new GenericService<>();
        service.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @PostMapping
    public ResponseEntity<T> save(@RequestBody T entity) {
        return ResponseEntity.ok(service.save(entity));
    }

}