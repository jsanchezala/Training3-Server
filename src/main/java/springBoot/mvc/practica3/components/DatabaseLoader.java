package springBoot.mvc.practica3.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springBoot.mvc.practica3.repositories.ProductRepository;

import javax.annotation.PostConstruct;

@Component
public class DatabaseLoader {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    private void initDatabase(){

    }

}
