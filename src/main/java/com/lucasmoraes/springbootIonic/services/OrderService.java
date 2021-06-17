package com.lucasmoraes.springbootIonic.services;

import com.lucasmoraes.springbootIonic.domain.BilletPayment;
import com.lucasmoraes.springbootIonic.domain.Order;
import com.lucasmoraes.springbootIonic.domain.OrderItem;
import com.lucasmoraes.springbootIonic.domain.enums.PaymentStatus;
import com.lucasmoraes.springbootIonic.repositories.OrderItemRepository;
import com.lucasmoraes.springbootIonic.repositories.OrderRepository;
import com.lucasmoraes.springbootIonic.repositories.PaymentRepository;
import com.lucasmoraes.springbootIonic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService
{

    @Autowired
    private OrderRepository repository;

    @Autowired
    private BilletService billetService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;


    public Order find(Integer id)
    {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
                "Object not found! Id: " + id + " Type: " + Order.class.getName()));
    }

    @Transactional
    public Order insert(Order obj)
    {
        // Setando id para null (garantia)
        obj.setId(null);
        // Nova data com instante atual
        obj.setInstant(new Date());

        // Setando cliente
        obj.setClient(clientService.find(obj.getClient().getId()));

        // Pedido criado agora ainda estará pendente
        obj.getPayment().setStatus(PaymentStatus.PENDENT);
        // Associação de mão dupla
        obj.getPayment().setOrder(obj);
        if(obj.getPayment() instanceof BilletPayment)
        {
            BilletPayment billetPayment = (BilletPayment) obj.getPayment();
            billetService.fillBilletPayment(billetPayment, obj.getInstant());
        }
        obj = repository.save(obj);
        paymentRepository.save(obj.getPayment());
        for(OrderItem orderItem : obj.getItems())
        {
            orderItem.setDiscount(0.0);
            orderItem.setProduct(productService.find(orderItem.getProduct().getId()));
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setOrder(obj);
        }
        orderItemRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}
