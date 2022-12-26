package consulting.sit.catenax.controller;

import consulting.sit.catenax.model.ModelBaseInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public abstract class GenericControllerSingleId<T extends ModelBaseInterface<I>, I extends Serializable> extends GenericController<T, I> {
    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable I id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@RequestBody T entity, @PathVariable I id) {
        return ResponseEntity.ok(service.update(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(@PathVariable I id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
