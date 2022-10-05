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
            Member member = Member.data();
            Item item = Item.data();
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("박재민");
            OrderItem orderItem = OrderItem.data(item);
            Delivery delivery = Delivery.data();
            Order order = Order.data(member,delivery);
            orderItem.setOrder(order);

            List<Object> list = new ArrayList<>();
            list.add(member);
            list.add(item);
            list.add(book);
            list.add(order);
            list.stream().forEach(e->em.persist(e));

            em.flush();
            nextObject(em);

            System.out.println("===== member start =====");
            /* LazyInitializationException
            Member proxyMember = em.getReference(Member.class,member.getId());
            em.detach(proxyMember);
            proxyMember.getName();
            */

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember);
            nextSql();
            System.out.println("findMember.getOrders().get(0) = " + findMember.getOrders().get(0));
            System.out.println("===== member end =====");

            nextObject(em);
            System.out.println("===== item start =====");
            Item findItem = em.find(Item.class, item.getId());
            System.out.println("findItem = " + findItem);
            nextSql();
            System.out.println("findItem.getCategories() = " + findItem.getCategories());
            System.out.println("===== item end =====");

            nextObject(em);
            System.out.println("===== order item start =====");
            OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
            System.out.println("findOrderItem = " + findOrderItem);
            nextSql();
            System.out.println("findOrderItem.getItem() = " + findOrderItem.getItem());
            nextSql();
            System.out.println("findOrderItem.getOrder() = " + findOrderItem.getOrder());
            System.out.println("===== order item end =====");

            nextObject(em);
            System.out.println("===== order start =====");
            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("findOrder = " + findOrder);
            nextSql();
            System.out.println("findOrder.getOrderItems().get(0) = " + findOrder.getOrderItems().get(0));
            nextSql();
            System.out.println("findOrder.getMember() = " + findOrder.getMember());
            nextSql();
            System.out.println("findOrder.getDelivery() = " + findOrder.getDelivery());
            nextSql();
            System.out.println("findOrder.getStatus() = " + findOrder.getStatus());
            System.out.println("===== order end =====");

            nextObject(em);
            System.out.println("===== delivery start =====");
            Delivery findDelivery = em.find(Delivery.class, delivery.getId());
            System.out.println("findDelivery = " + findDelivery);
            nextSql();
            System.out.println("findDelivery.getOrder() = " + findDelivery.getOrder());
            System.out.println("===== delivery end =====");

            tx.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();

    }

    private static void nextObject(EntityManager em){
        em.clear();
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private static void nextSql(){
        System.out.println();
    }

}
