package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em; //생성자 인젝션 사용한거임

    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class, id);  //타입, pk를 넣은거임
    }
    public List<Member> findAll(){ //jpql이고 from의 대상이 테이블이 아니라 엔티티임
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); //jpql인데 나중에 sql로 번역된다.
    }
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name ", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
