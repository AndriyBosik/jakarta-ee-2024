package com.example.foodordering.repository.impl;

import com.example.foodordering.common.ActionType;
import com.example.foodordering.common.EntityType;
import com.example.foodordering.common.UserType;
import com.example.foodordering.entity.Order;
import com.example.foodordering.entity.projection.AggregatedOrder;
import com.example.foodordering.repository.OrderRepository;
import com.example.foodordering.repository.ProjectAuditRepository;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private ProjectAuditRepository projectAuditRepository;

    @Override
    public long save(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        projectAuditRepository.save(UserType.USER, EntityType.ORDER, ActionType.CREATE, order.getId());
        return order.getId();
    }

    @Override
    public List<AggregatedOrder> findOrders() {
        return entityManager.createNativeQuery("select o.id as id, " +
                        " o.first_name as firstName, " +
                        " o.last_name as lastName, " +
                        " o.phone as phone, " +
                        " o.status as status, " +
                        " o.created_date as createdDate, " +
                        " sum(oi.quantity * f.price) as total " +
                        "from public.order o " +
                        " left join public.order_item oi on o.id = oi.order_id " +
                        " left join public.food f on f.id = oi.food_id " +
                        "group by o.id " +
                        "order by o.created_date", AggregatedOrder.class)
                .getResultList();
    }

    @Override
    public Optional<Order> findById(long id) {
        return Optional.ofNullable(
                entityManager.createQuery("select o from Order o join fetch o.items where o.id = :id", Order.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    public long update(Order order) {
        entityManager.merge(order);
        entityManager.flush();
        projectAuditRepository.save(UserType.ADMIN, EntityType.ORDER, ActionType.DELETE, order.getId());
        return order.getId();
    }
}
