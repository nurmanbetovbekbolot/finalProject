package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.OrderModel;
import kg.itacademy.gsg.repositories.OrderRepository;
import kg.itacademy.gsg.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> p = orderRepository.findById(id);
        return p.orElse(new Order());
    }

    @Override
    public Page<OrderModel> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Order updateOrder(OrderModel orderModel) {
        return orderRepository.findById(orderModel.getId())
                .map(newOrder -> {
                    newOrder.setTitle(orderModel.getTitle());
                    newOrder.setClientId(orderModel.getClientId());
                    newOrder.setManagerId(orderModel.getManagerId());
                    newOrder.setPackageId(orderModel.getPackageId());
                    return orderRepository.save(newOrder);
                })
                .orElseThrow(() -> new RecordNotFoundException("Order not found with id:" + orderModel.getId()));
    }

    @Override
    public Order saveOrder(OrderModel orderModel) {
        Order order = new Order();
        order.setTitle(orderModel.getTitle());
        order.setClientId(orderModel.getClientId());
        order.setManagerId(orderModel.getManagerId());
        order.setPackageId(orderModel.getPackageId());
        order.setCreatedDate(orderModel.getCreatedDate());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
