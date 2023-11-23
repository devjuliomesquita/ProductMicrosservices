package com.juliomesquita.projecteventsourcecqrs.query.api.controller;

import com.juliomesquita.projecteventsourcecqrs.command.api.model.ProductRestModel;
import com.juliomesquita.projecteventsourcecqrs.query.api.queries.ProductQueryGet;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductQueryController {
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts(){
        ProductQueryGet productQueryGet =
                new ProductQueryGet();
        List<ProductRestModel> productRestModels =
                queryGateway
                        .query(productQueryGet, ResponseTypes
                                .multipleInstancesOf(ProductRestModel.class)
                        ).join();
        return productRestModels;
    }
}
