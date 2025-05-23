package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 이면 로그인 실패
     */
    public Member login(String loginId, String password){
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
//        Member member = findMember.get();
//        if(member.getPassword().equals(password)){
//            return member;
//        } else {
//            return null;
//        }

        return findMember.filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
