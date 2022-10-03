package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            String str = "hello";
            Member member = new Member();
            member.setName(str);
            member.setCity(str);
            member.setStreet(str);
            member.setZipcode("12345");

            Item item = new Item();
            item.setName(str);
            item.setPrice(5000);
            item.setStockQuantity(100);

            Order order = new Order();
            OrderItem orderItem = new OrderItem();
            order.getOrderItems().add(orderItem);
            order.setMember(member);
            order.setStatus(OrderStatus.ORDER);
            order.setOrderDate(LocalDateTime.now());
            orderItem.setCount(2);
            orderItem.setOrderPrice(10000);
            orderItem.setItem(item);
            orderItem.setOrder(order);

            List<Object> list = new ArrayList<>();
            list.add(member);
            list.add(item);
            list.add(orderItem);
            list.add(order);

            list.stream().forEach(e->em.persist(e));

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getName());
            System.out.println("findMember.getOrders().get(0).getId() = " + findMember.getOrders().get(0).getId());
            System.out.println("findMember.getOrders().get(0).getOrderItems().get(0) = " + findMember.getOrders().get(0).getOrderItems().get(0));
            
            Item findItem = em.find(Item.class, item.getId());

            System.out.println("findItem.getName() = " + findItem.getName());

            OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
            System.out.println("findOrderItem.getOrderPrice() = " + findOrderItem.getOrderPrice());
            System.out.println("findOrderItem.getOrder().getId() = " + findOrderItem.getOrder().getId());
            System.out.println("findOrderItem.getOrder().getMember().getName() = " + findOrderItem.getOrder().getMember().getName());
            System.out.println("findOrderItem.getItem().getName() = " + findOrderItem.getItem().getName());

            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("findOrder.getMember(). = " + findOrder.getMember().getId());
            System.out.println("findOrder.getOrderItems().get(0).getId() = " + findOrder.getOrderItems().get(0).getId());
            System.out.println("findOrder.getMember().getName() = " + findOrder.getMember().getName());

            System.out.println("=================================");

            System.out.println("findMember = " + findMember);
            System.out.println("item.getId = " + item);
            System.out.println("findOrderItem = " + findOrderItem);
            System.out.println("findOrder.getId() = " + findOrder);

            tx.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();

    }

}
