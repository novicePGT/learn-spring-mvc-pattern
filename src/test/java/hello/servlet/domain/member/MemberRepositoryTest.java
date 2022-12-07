package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    /*
     싱글톤 테스트일 경우는 new 로 생성하는 것이 아님
     Spring 을 쓰면 싱글톤 사용할 이유가 없음 -> Spring 이 싱글톤을 보장해줌
     */
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore(); // 테스트가 끝날 때마다 초기화 하기 위함.
    }

    @Test
    void 저장테스트() {
        //given
        Member member = new Member("park-geuntae", 24);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    @DisplayName("전체 조회 테스트")
    void 전체_조회_테스트() {
        //given
        Member member = new Member("member1", 20);
        Member member2 = new Member("member2", 24);
        Member member3 = new Member("member3", 28);

        memberRepository.save(member);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(3);
    }
}