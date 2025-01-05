package com.spring.events.resource;

import com.spring.events.model.OrderResponse;
import com.spring.events.service.OrderService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Path("/apiv1/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;

    @PUT
    @Path("/{id}")
    public OrderResponse updateOrder(@NotNull @PathParam("id") Long id) {
        return orderService.updateOrder(id);
    }
}