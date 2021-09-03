package Interface;

import com.squareup.okhttp.OkHttpClient;

import Entity.User;

import java.util.List;

public interface OkhttpDataFunctionImplement {
    List<String> getAllWeb(OkHttpClient client);
    List<String> getAllNews(OkHttpClient client);
    List<String> getAllNewsByCategory(OkHttpClient client, String category);
    List<String> getAllNewsByWebsourceId(OkHttpClient client, int websourceId);
    List<String> getAllFormatStringByUrlName(OkHttpClient client, String urlName);
    List<String> getAllWebFormatByWebsiteId(OkHttpClient client, Long id);
    List<String> getAllWebFormatByWebsiteIdAndCategoryId(OkHttpClient client, Long id,Long categoryId);
    List<String> getAllWebSource(OkHttpClient client);
    List<String> getAllCategory(OkHttpClient client);
    List<String> getCategoryByNewsCategory(OkHttpClient client, String category);
    List<String> getContentFormatById(OkHttpClient client , Long id);
    String getTokenByLoginAdmin(User user);
    List<String> getContentbyIdNews(OkHttpClient client , Long id);
    void createNews(OkHttpClient client, String news);
    boolean checkExistNewsInUrl(OkHttpClient client, String title, String websiteId);
    void createContentById(OkHttpClient client, String content, Long id);
}
