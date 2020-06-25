package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.*;
import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.*;
import kg.itacademy.gsg.repositories.OrderRepository;
import kg.itacademy.gsg.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    ClientTasksServiceImpl clientTasksService;

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
        Order newOrder = new Order();
        Package pFromDB = packageService.getPackageById(orderCreationModel.getPackageId());
        User user = userService.getUserById(orderCreationModel.getClientId());
        User manager = userService.getUserById(orderCreationModel.getManagerId());
        newOrder.setPackageId(pFromDB);
        newOrder.setClientId(user);
        newOrder.setManagerId(userService.getUserById(orderCreationModel.getManagerId()));
        newOrder.setTitle(orderCreationModel.getTitle());
        List<CategoryModel> categoryModelList = categoryService.getAllByPackageId(pFromDB.getId());
        Order orderSave = orderRepository.save(newOrder);
        for(CategoryModel catModel : categoryModelList){
            List<TaskModel> taskModelList = taskService.findAllByCategoryId(catModel.getId());
            for (TaskModel taskModel : taskModelList){
                Task task = new Task(taskModel.getTitle(), taskModel.getDescription(), taskModel.getStatus(), categoryService.getCategoryById(catModel.getId()));
                taskService.saveTask(task);
                ClientTasks clientTasks = new ClientTasks();
                clientTasks.setClient(user);
                clientTasks.setTask(task);
                clientTasks.setManager(manager);
                clientTasks.setOrder(orderSave);
                clientTasksService.save(clientTasks);
            }
        }
        return orderSave;
    }

    @Override
    public Page<ClientCategoryTasksModel> getClientCategoryTasks(Order order, Pageable pageable) {
        List<CategoryModel> categoryModelList = categoryService.getAllByPackageId(order.getPackageId().getId());
        List<ClientCategoryTasksModel> clientCategoryTasksModelsList = new ArrayList<>();
        for(CategoryModel category : categoryModelList){
            ClientCategoryTasksModel clientCategoryTasksModel = new ClientCategoryTasksModel();
            clientCategoryTasksModel.setId(category.getId());
            clientCategoryTasksModel.setTitle(category.getTitle());
            clientCategoryTasksModel.setTaskModels(taskService.getAllByClientAndOrder(order));
            clientCategoryTasksModelsList.add(clientCategoryTasksModel);
        }
        return new PageImpl<>(clientCategoryTasksModelsList);
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
