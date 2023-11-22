package com.juliomesquita.projecteventsourcecqrs.command.api.events;

import com.juliomesquita.projecteventsourcecqrs.command.api.data.Product;
import com.juliomesquita.projecteventsourcecqrs.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRepository;
    public ProductEventsHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        this.productRepository.save(product);
    }
}
