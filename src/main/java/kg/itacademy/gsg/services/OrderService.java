package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.models.OrderCreationModel;
import kg.itacademy.gsg.models.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Page<OrderModel> findAll(Pageable pageable);

    Order updateOrder(OrderModel orderModel);

    Order saveOrder(OrderCreationModel orderCreationModel);

    void deleteOrderById(Long orderId);

    Page<OrderModel> findAllByName(String toLowerCase, Pageable pageable);

    Page<OrderModel> findAllByDate(Date dateFrom, Date dateTo, Pageable pageable);

}
