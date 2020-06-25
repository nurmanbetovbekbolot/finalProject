package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.*;
import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.CategoryModel;
import kg.itacademy.gsg.models.OrderCreationModel;
import kg.itacademy.gsg.models.OrderModel;
import kg.itacademy.gsg.models.TaskModel;
import kg.itacademy.gsg.repositories.OrderRepository;
import kg.itacademy.gsg.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    PackageServiceImpl packageService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    TaskServiceImpl taskService;

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
        return orderRepository.findAllOrdersWithPagination(pageable);
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
    public Order saveOrder(OrderCreationModel orderCreationModel) {
        Order order = new Order();
        Package pFromDB = packageService.getPackageById(orderCreationModel.getPackageId());
        User user = userService.getUserById(orderCreationModel.getClientId());
        List<CategoryModel> categoryModelList = categoryService.getAllByPackageId(pFromDB.getId());
        Package p = new Package(pFromDB.getTitle()+ " для "+user.getFirstName()+" "+user.getLastName(),pFromDB.getDescription());
        packageService.savePackage(p);

        for(CategoryModel catModel : categoryModelList){
            Category category = new Category(catModel.getTitle(), p);
            categoryService.saveCategory(category);
            List<TaskModel> taskModelList = taskService.findAllByCategoryId(catModel.getId());
            for (TaskModel taskModel : taskModelList){
                Task task = new Task(taskModel.getTitle(), taskModel.getDescription(), taskModel.getStatus(), category);
                taskService.saveTask(task);
            }
        }

        order.setTitle(orderCreationModel.getTitle());
        order.setClientId(userService.getUserById(orderCreationModel.getClientId()));
        order.setManagerId(userService.getUserById(orderCreationModel.getManagerId()));
        order.setPackageId(p);

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteByOrderId(orderId);
    }

    @Override
    public Page<OrderModel> findAllByName(String toLowerCase, Pageable pageable) {
        return orderRepository.findAllByName(toLowerCase, pageable);
    }

    @Override
    public Page<OrderModel> findAllByDate(Date dateFrom, Date dateTo, Pageable pageable) {
        return orderRepository.findAllByDate(dateFrom,dateTo,pageable);
    }
}
