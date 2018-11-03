package springBoot.mvc.practica3.repositories;

import org.springframework.data.repository.CrudRepository;
import springBoot.mvc.practica3.model.Producto;

import java.util.List;

public interface ProductRepository extends CrudRepository<Producto, String> {

    List<Producto> findAll();
    void deleteByCodigo(String code);
    Producto findByCodigo(String codigo);

}
