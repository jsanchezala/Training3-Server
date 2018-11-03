package springBoot.mvc.practica3.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springBoot.mvc.practica3.model.Producto;
import org.springframework.http.ResponseEntity;
import springBoot.mvc.practica3.repositories.ProductRepository;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@Api(tags="Product")
public class ControladorPrincipal {


    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/show/{codigo}")
    @ApiOperation(value = "getting a product")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Producto.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unhandled exception")
    })
    public ResponseEntity<Producto> mostrarProducto(@PathVariable String codigo) {
        Producto producto = productRepository.findByCodigo(codigo);
        if (Objects.isNull(producto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
    }


    @PostMapping("/newProduct")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "creating a product or update a product")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Created"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unhandled exception")
    })
    public void product(@RequestBody Producto producto) {
        Producto aux_producto = productRepository.findByCodigo(producto.getCodigo());
        if (Objects.isNull(aux_producto)) {
            productRepository.save(producto);
        }else{
            productRepository.delete(producto);
            productRepository.save(producto);
        }

    }

    @DeleteMapping(value = "/delete/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "deleting a product")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Delete"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad Request"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unhandled exception")
    })
    public void remove(@PathVariable String codigo){
        productRepository.deleteByCodigo(codigo);
        //productRepository.delete(productRepository.findByCodigo(codigo));
    }


    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "getting all products")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK", response = Producto[].class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Unhandled exception")
    })
    public List<Producto> getList() {
        List<Producto> all = productRepository.findAll();
        return all;
    }




}