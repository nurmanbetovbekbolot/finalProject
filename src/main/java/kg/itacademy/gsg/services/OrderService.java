package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.models.ClientCategoryTasksModel;
import kg.itacademy.gsg.models.OrderCreationModel;
import kg.itacademy.gsg.models.OrderModel;
import kg.itacademy.gsg.models.TaskModel;
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

    Page<ClientCategoryTasksModel> getClientCategoryTasks(Order order, Pageable pageable);

    void deleteOrderById(Long orderId);

    Page<OrderModel> findAllOrdersByClientId(Long id, Pageable pageable);

    Page<OrderModel> findAllOrdersByName(String toLowerCase, Pageable pageable);

    Page<OrderModel> findAllOrdersByDate(Date dateFrom, Date dateTo, Pageable pageable);

}
