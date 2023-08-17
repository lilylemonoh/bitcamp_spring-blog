package com.spring.blog.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

// 쿠키를 모든 로직에 생성하고 파기하는 로직을 만들기 귀찮으니 반복적으로 사용할 코드를 클래스에 모아둔 것
// 쿠키에 영구히 박제하는 게 아니라, 사용자측에 토큰을 전달하는 매개체로만 쿠키를 사용하고 바로 파기할 목적
public class CookieUtil {
    // 요청값(이름, 값, 만료기간)을 바탕으로 쿠키를 생성하기
    // response란? 사용자에게 해줄 응답을 설정하는 스프링 내장 객체
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
//        response.addHeader("abc", "result"); // 예를 들어 이렇게 적었다고 치면 header에 abc라는 키값으로 result value를 보냄
        //쿠키란? 헤더랑 바디 이외에 사용자측에 보내주는 것이다. (사용자측에서 저장)
        // 세션기반에서 세션은 사용자, 서버가 있을 때 서버에 저장하는 것이다.
    }

    // 쿠키의 이름을 입력받아 쿠키 삭제
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return; // 쿠키가 없으면
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0); // 쿠키는 유효시간을 0으로 수정하면 남은 시간과 상관없이 자동 파기됨
                response.addCookie(cookie);
            }
        }
    }

    // 자바 객체를 직렬화해 쿠키의 값으로 변환
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키를 역직렬화해 자바 객체로 변환
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}
