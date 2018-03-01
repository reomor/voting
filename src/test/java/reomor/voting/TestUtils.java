package reomor.voting;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import reomor.voting.model.User;
import reomor.voting.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static reomor.voting.UserTestData.admin1001;
import static reomor.voting.util.JsonUtil.writeValue;

public class TestUtils {
    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static <T> T readFromJson(ResultActions actions, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(actions), clazz);
    }

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }

    @SafeVarargs
    public static <T> ResultMatcher contentJsonArray(T ... expected) {
        return contentJson(expected);
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }

    public static RequestPostProcessor userAuth(User user) {
        return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }

    public static HttpHeaders getAuthHeadersForUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        String plainCredentials = user.getEmail() + ":" +user.getPassword();
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        headers.add("Authorization", "Basic " + base64Credentials);

        return headers;
    }

}
