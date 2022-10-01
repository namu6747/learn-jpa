package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            SecondMember secondMember = new SecondMember();
            secondMember.setUsername("C");

            em.persist(secondMember);

            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally {
            em.close();

        }
        emf.close();
    }

}
