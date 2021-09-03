package okhttp;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import Entity.News;
import Entity.User;
import Interface.OkhttpDataFunctionImplement;
import model.Token;
import org.apache.log4j.Logger;

import javax.ws.rs.client.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OkHttpDataFunction implements OkhttpDataFunctionImplement {
    public static final String BASE_URL = "http://113.161.80.157:16532/rest-api/";
    public static final String API_URL_SOURCE = "getdetailweb/api/v1/source/";
    public static final String API_URL_NEWS = "getdetailweb/api/v1/news/";
    public static final String API_URL_CONTENT = "getdetailweb/api/v1/content/";
    public static final String API_URL_WEBFORMAT = "getdetailweb/api/v1/webformat/";
    public static final String API_URL_CATEGORY = "getdetailweb/api/v1/category/";
    public static final String API_URL_USER = "getdetailweb/api/v1/user/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Logger log = Logger.getLogger(OkHttpDataFunction.class);

    @Override
    public List<String> getAllWeb(OkHttpClient client) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_SOURCE).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllNews(OkHttpClient client) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_NEWS).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            if(!result.equals("[]")) {
                dataResult.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllNewsByCategory(OkHttpClient client, String category) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_NEWS + "category/"+category).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllNewsByWebsourceId(OkHttpClient client, int websourceId) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_NEWS + "website/" + websourceId).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            if(!result.equals("[]")) {
                dataResult.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllFormatStringByUrlName(OkHttpClient client, String urlName) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_WEBFORMAT + "name/" + urlName).newBuilder();

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllWebFormatByWebsiteId(OkHttpClient client, Long id) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_WEBFORMAT + "website/" + id).newBuilder();

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllWebFormatByWebsiteIdAndCategoryId(OkHttpClient client, Long id, Long categoryId) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_WEBFORMAT + "websiteId=" + id + "/categoryId=" + categoryId).newBuilder();

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllWebSource(OkHttpClient client) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_SOURCE).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getAllCategory(OkHttpClient client) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_CATEGORY).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getCategoryByNewsCategory(OkHttpClient client, String category) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_CATEGORY + "category/" + category).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public List<String> getContentFormatById(OkHttpClient client, Long id) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_WEBFORMAT + "id/" + id).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            dataResult.add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public String getTokenByLoginAdmin(User user) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BASE_URL + API_URL_USER + "login");
        Invocation.Builder invocationBuilder = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        javax.ws.rs.core.Response response = invocationBuilder.post(Entity.entity(user, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    @Override
    public void createNews(OkHttpClient client, String news) {
        RequestBody requestBody = RequestBody.create(JSON,news);
        Request request = new Request.Builder()
                .url(BASE_URL + API_URL_NEWS + "create")
                .header("Authorization", Token.getTokenStr())
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            News newsObject = new Gson().fromJson(news,News.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistNewsInUrl(OkHttpClient client, String title, String websiteId) {
        boolean dataResult = false;
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_NEWS + "check/title=" + title + "/websiteId=" + websiteId).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            dataResult = Boolean.parseBoolean(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public void createContentById(OkHttpClient client, String content, Long id) {
        RequestBody requestBody = RequestBody.create(JSON,content);
        Request request = new Request.Builder()
                .url(BASE_URL + API_URL_CONTENT + "create/id/" + id)
                .header("Authorization",Token.getTokenStr())
                .post(requestBody)
                .build();
        Response response = null;
        try {
             response = client.newCall(request).execute();
            response.body().close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getContentbyIdNews(OkHttpClient client , Long id) {
        List<String> dataResult = new ArrayList<>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + API_URL_CONTENT + "id/" + id).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            if(result.equals("")) {
                return dataResult;
            } else {
                dataResult.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }
}
