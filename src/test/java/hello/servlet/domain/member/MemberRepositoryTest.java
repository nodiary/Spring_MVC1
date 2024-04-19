package hello.servlet.domain.member;

import hello.domain.member.Member;
import hello.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member saved = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saved.getId());
        assertThat(findMember).isEqualTo(saved);
    }
    @Test
    void findAll() {
        // given
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("hello", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        // when
        List<Member> result = memberRepository.findAll();
        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(member1);
        assertThat(result.get(1)).isEqualTo(member2);
    }
}
