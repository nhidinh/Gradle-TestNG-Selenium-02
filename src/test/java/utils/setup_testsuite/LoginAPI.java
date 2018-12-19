package utils.setup_testsuite;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * User: Nhi Dinh
 * Date: 18/12/2018
 */
public class LoginAPI {
    private CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    private BasicCookieStore cookieStore = new BasicCookieStore();
    private HttpClientContext localContext = HttpClientContext.create();
    private void initRequest(){
        localContext.setCookieStore(cookieStore);
        httpclient.start();
    }

    private void GetRequest(String getUri){
        HttpGet httpGet = new HttpGet(getUri);
        System.out.println("Executing Request " + httpGet.getRequestLine());

        Future<HttpResponse> future =httpclient.execute(httpGet, localContext, null);

        HttpResponse response = null;
        try {
            response = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (response != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response: " + response.getStatusLine());
            System.out.println("Response Status Code: " + statusCode);
        }

        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie:cookies) {
            System.out.println("Local Cookie: " + cookie);
        }
    }

    private List<Cookie> PostRequest(String postURI) throws ExecutionException, InterruptedException {
        initRequest();
        HttpPost httpPost = new HttpPost(postURI);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("log", "nhidinh"));
        params.add(new BasicNameValuePair("pwd", "P4assw0rd1!"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Future<HttpResponse> future = httpclient.execute(httpPost, localContext, null);
        HttpResponse response = future.get();
        if (response != null) {
            System.out.println("Response: " + response);
        }

        return cookieStore.getCookies();
    }

    public void loginAPI(WebDriver driver, String loginURI) throws ExecutionException, InterruptedException, IOException {
        driver.get(loginURI);
        try {
            List<Cookie> cookies = PostRequest(loginURI);
            org.openqa.selenium.Cookie c;
            for (Cookie cookie : cookies) {
                System.out.println("Local Cookie: " + cookie);
                c = new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue());
                driver.manage().addCookie(c);
            }
        }finally {
            httpclient.close();
        }
    }

}
