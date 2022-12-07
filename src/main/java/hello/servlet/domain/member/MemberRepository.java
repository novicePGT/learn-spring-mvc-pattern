package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려함
 */

public class MemberRepository {

    // static 으로 생성하여 MemberRepository 가 하나씩만 생성됨
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    /*
     싱글톤으로 만들기 !
     싱글톤으로 만들 때는 private 로 생성자를 막아줘야함 -> 아무나 생성하지 못하게 해줌
     getInstance 로 메서드를 분리하여 반드시 getInstance 로 조회를 해야할 것이다
     */
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    /* 멤버를 저장하는 방법 */
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /* 아이디로 조회하는 방법 */
    public Member findById(Long id) {
        return store.get(id);
    }

    /* 모든 멤버를 조회하는 방법
    * 이렇게하면 store 의 모든 값을 새로운 arrayList 에 담아서 넘겨준다
    * 왜 이런 형식으로 하냐면, new ArrayList 에 값을 넣거나 조작해도 store 의 value list 를 건들지 않게 된다.
    * 반대로, store 를 직접 가져와서 조작한다면 실제로 변경되게 되어버린다. * 주의 *
    */
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /* 요거는 보통 테스트에서 사용하며, store 를 모두 날려버림 */
    public void clearStore() {
        store.clear();
    }
}
