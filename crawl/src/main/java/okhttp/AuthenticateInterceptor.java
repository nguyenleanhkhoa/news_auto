package okhttp;

import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AuthenticateInterceptor implements Interceptor {
    private String credentials;

    public AuthenticateInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println("interceptor");
        return null;
    }
}
