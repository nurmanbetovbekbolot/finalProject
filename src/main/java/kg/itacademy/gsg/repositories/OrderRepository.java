package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.models.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {
    @Query("select new kg.itacademy.gsg.models.OrderModel(o.id,o.title,o.clientId,o.managerId,o.packageId,o.createdDate) FROM Order o ORDER BY o.id ASC")
    Page<OrderModel> findAllOrdersWithPagination(Pageable pageable);

    @Query("select new kg.itacademy.gsg.models.OrderModel(o.id,o.title,o.clientId,o.managerId,o.packageId,o.createdDate) FROM Order o WHERE (lower(o.title) like %:search% or lower(o.clientId.firstName) like %:search% or lower(o.managerId.firstName) like %:search% or lower(o.packageId.title) like %:search%) ORDER BY o.id ASC")
    Page<OrderModel> findAllByName(String search, Pageable pageable);

    @Query("select new kg.itacademy.gsg.models.OrderModel(o.id,o.title,o.clientId,o.managerId,o.packageId,o.createdDate) FROM Order o WHERE o.createdDate between :dateFrom and :dateTo")
    Page<OrderModel> findAllByDate(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete FROM gsg_orders WHERE id = :id", nativeQuery = true)
    void deleteByOrderId(@Param("id")Long id);
}
