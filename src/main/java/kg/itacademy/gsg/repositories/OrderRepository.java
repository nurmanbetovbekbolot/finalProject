package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.models.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {
    @Query("select new kg.itacademy.gsg.models.OrderModel(o.id,o.title,o.clientId,o.managerId,o.packageId,o.createdDate) FROM Order o ORDER BY o.id ASC")
    Page<OrderModel> findAllOrdersWithPagination(Pageable pageable);
}
