package hello.login.web.session;

import static org.assertj.core.api.Assertions.*;

import hello.login.domain.member.Member;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
        //테스트 할 수 있도록 제공
        MockHttpServletResponse response = new MockHttpServletResponse();

        //세션 생성
        Member member = new Member();
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies()); //mySessionId = ~~~

        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
