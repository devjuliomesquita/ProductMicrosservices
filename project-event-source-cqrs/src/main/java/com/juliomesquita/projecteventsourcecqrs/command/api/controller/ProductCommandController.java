package com.juliomesquita.projecteventsourcecqrs.command.api.controller;

import com.juliomesquita.projecteventsourcecqrs.command.api.commands.ProductCreateCommand;
import com.juliomesquita.projecteventsourcecqrs.command.api.model.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {
    private final CommandGateway commandGateway;
    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel){

        ProductCreateCommand productCreateCommand =
                ProductCreateCommand
                        .builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();
        String result = commandGateway.sendAndWait(productCreateCommand);
        return result;
    }
}
