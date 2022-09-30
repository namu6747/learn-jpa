package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    //.setFirstResult(5)
                    //.setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            em.flush();
            em.persist(member);

            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally {
            em.close();

        }
        emf.close();
    }

}
