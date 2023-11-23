package com.juliomesquita.projecteventsourcecqrs.query.api.projection;

import com.juliomesquita.projecteventsourcecqrs.command.api.data.Product;
import com.juliomesquita.projecteventsourcecqrs.command.api.data.ProductRepository;
import com.juliomesquita.projecteventsourcecqrs.command.api.model.ProductRestModel;
import com.juliomesquita.projecteventsourcecqrs.query.api.queries.ProductQueryGet;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> hondle(ProductQueryGet productQueryGet){
        List<Product> products = productRepository.findAll();
        List<ProductRestModel> productRestModels =
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .name(product.getName())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build()
                        )
                        .collect(Collectors.toList());


        return productRestModels;
    }
}
