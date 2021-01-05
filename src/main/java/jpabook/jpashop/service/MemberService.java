package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //성능을 위해서 조회기능은 읽기전용이라고 표시. 읽기전용은 데이터 변경안됨
@RequiredArgsConstructor //final인 애만 가지고 생성자 만들어줌
public class MemberService {
/*    @Autowired  //필드 인젝션
    private MemberRepository memberRepository;*/

    //생성자 인젝션 사용하기
    private final MemberRepository memberRepository;

   /* public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/


    //회원가입
    @Transactional //기본은 readonly가 false임
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    } //실무에서는 name을 unique제약조건으로 주는것이 안전함

    //회원전체조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
