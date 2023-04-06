package com.sda.inventoryservice.controller;

import com.sda.inventoryservice.Constants;
import com.sda.inventoryservice.entity.Inventory;
import com.sda.inventoryservice.entity.OrderStatus;
import com.sda.inventoryservice.entity.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryController {

    @RabbitListener(queues = Constants.QUEUE )
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        Inventory inventory = new Inventory();
        inventory.setQty(10);
        Product product = new Product();
        product.setId(orderStatus.getOrder().getOrderId());
        Integer qty = orderStatus.getOrder().getQty();
        System.out.println("qty: " + qty);
        if(orderStatus.getOrder().getQty() < inventory.getQty()){
             System.out.println("SUCCESS");
            System.out.println("Order Placed Successfully");
        }else{
            System.out.println("FAILED");
            System.out.println("Order Failed");
        }
//        System.out.println("Message Received from queue: " +orderStatus );
    }
}