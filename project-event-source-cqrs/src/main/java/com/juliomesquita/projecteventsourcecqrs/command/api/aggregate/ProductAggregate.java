package com.juliomesquita.projecteventsourcecqrs.command.api.aggregate;

import com.juliomesquita.projecteventsourcecqrs.command.api.commands.ProductCreateCommand;
import com.juliomesquita.projecteventsourcecqrs.command.api.events.ProductCreatedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(ProductCreateCommand productCreateCommand){
            //Validations
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(productCreateCommand, productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    public ProductAggregate(){}

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }



}
