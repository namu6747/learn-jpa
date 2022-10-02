package hellojpa;


import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class HelloMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("first team");
            Team teamB = new Team();
            teamB.setName("second team");
            em.persist(teamA);
            em.persist(teamB);

            IntStream.range(1,10).forEach(i -> {
                Member member = new Member();
                String str = i + "";
                member.setName(str + "st");
                if(i % 2 == 0){
                    member.changeTeam(teamB);
                } else {
                    member.changeTeam(teamA);
                }
                em.persist(member);
            });
            em.flush();
            em.clear();
            List<Member> list = em.createQuery("SELECT m FROM Member m").getResultList();
            System.out.println("list = " + list);

            Member findMember1 = em.find(Member.class, 10L);
            System.out.println("findMember1 = " + findMember1);

            Team findTeam = findMember1.getTeam();
            System.out.println("findTeam = " + findTeam);

            List<Member> members = findTeam.getMembers();
            System.out.println("members = " + members);

            em.flush();
            em.clear();

            Team findTeam2 = em.find(Team.class, 2L);

            List<Member> members2 = findTeam2.getMembers();

            for (Member m : members2) {
                System.out.println("m.getName() = " + m.getName());
            }

            System.out.println("=====================");

            tx.commit();
        } catch(Exception e){
            e.printStackTrace();
            tx.rollback();

        } finally {
            em.close();

        }
        emf.close();
    }

}
