package com.example.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/ott")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping(path = "/order")
    public @ResponseBody Order addNewOrder(@RequestParam String description) {
        Order order = new Order();
        order.setDescription(description);
        order.setStatus(Status.NEW);
        orderRepository.save(order);
        return order;
    }

    @GetMapping(path = "/order")
    public @ResponseBody List<OrderWithComment> getAllOrders() {
        List<OrderWithComment> order_list = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            OrderWithComment full_order = new OrderWithComment(order, commentRepository.findByOrder(order));
            order_list.add(full_order);
        }
        return order_list;
    }

    @PutMapping(value = "/order/{id}")
    public @ResponseBody OrderWithComment editOrder(@PathVariable Integer id, @RequestParam Optional<Status> status, @RequestParam Optional<String> comment) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            if(status.isPresent()) {
                order.get().setStatus(status.get());
                orderRepository.save(order.get());
            }
            if(comment.isPresent()) {
                Comment text = new Comment();
                text.setText(comment.get());
                text.setOrder(order.get());
                commentRepository.save(text);
            }
            OrderWithComment full_order = new OrderWithComment(order.get(), commentRepository.findByOrder(order.get()));
            return full_order;
        }
        return null;
    }

    @GetMapping(value = "/order/{id}")
    public @ResponseBody Optional<Order> getOrder(@PathVariable Integer id) {
        return orderRepository.findById(id);
    }

    @DeleteMapping(value = "/order/{id}")
    public @ResponseBody Integer deleteOrder(@PathVariable Integer id) {
        orderRepository.deleteById(id);
        return id;
    }
}
