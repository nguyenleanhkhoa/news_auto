/*
   * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import Database.CreateStatement;
import Entity.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;

import model.*;
import okhttp.OkHttpDataFunction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.TimerTask;
import java.util.*;

/**
 * @author Anhkhoa
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    private static String USERNAME = "";
    private static String PASSWORD = "";
//    private static OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();

    /**
     * Connect database
     */
    private static void ConnectDb() {

//        CreateStatement createStatement = new CreateStatement();
//        createStatement.deleteTable("category");
//        createStatement.deleteTable("webformat");
//        createStatement.deleteTable("content");
//        createStatement.deleteTable("news");
//        createStatement.deleteTable("websource");
//
//        createStatement.createTableCategory();
//        createStatement.createTableContent();
//        createStatement.createTableNews();
//        createStatement.createTableWebSource();
//        createStatement.createTableWebFormat();
//        createStatement.createTableUser();
    }

    /**
     * Get All Category from api
     *
     * @param client OkHttpClient
     * @return list
     */
    private static List<Category> getAllCategory(OkHttpClient client) {
        List<Category> listCategory = new ArrayList<>();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        ObjectMapper mapper = new ObjectMapper();
        List<String> categoryList = okHttpDataFunction.getAllCategory(client);
        String jsonCategory = null;
        try {
            jsonCategory = mapper.writeValueAsString(categoryList);
            JsonArray jsonCategoryArray = new JsonParser().parse(jsonCategory).getAsJsonArray();
            JsonArray jsonItemCategoryArray = new JsonParser().parse(jsonCategoryArray.get(0).getAsString()).getAsJsonArray();

            for (int i = 0; i < jsonItemCategoryArray.size(); i++) {
                Category category = new Gson().fromJson(jsonItemCategoryArray.get(i).toString(), Category.class);
                listCategory.add(category);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listCategory;
    }

    /**
     * Get all web format by website id
     *
     * @param client okhttpClient
     * @param id     id
     * @return list webformat
     */
    private static List<WebFormat> getAllWebFormatByWebsiteIdAndCategoryId(OkHttpClient client, Long id, Long categoryId) {
        List<WebFormat> listWebFormat = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        List<String> listNode = okHttpDataFunction.getAllWebFormatByWebsiteIdAndCategoryId(client, id, categoryId);
        String json = null;
        try {
            json = mapper.writeValueAsString(listNode);
            JsonArray jsonObjectArray = new JsonParser().parse(json).getAsJsonArray();
            JsonArray jsonElement = new JsonParser().parse(jsonObjectArray.get(0).getAsString()).getAsJsonArray();
            for (int i = 0; i < jsonElement.size(); i++) {
                WebFormat webFormat = new Gson().fromJson(jsonElement.get(i).toString(), WebFormat.class);
                listWebFormat.add(webFormat);
            }

            return listWebFormat;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listWebFormat;
    }

    /**
     * Get all web format by website id
     *
     * @param client okhttpClient
     * @param id     id
     * @return list webformat
     */
    private static List<WebFormat> getAllWebFormatByWebsiteId(OkHttpClient client, Long id) {
        List<WebFormat> listWebFormat = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        List<String> listNode = okHttpDataFunction.getAllWebFormatByWebsiteId(client, id);
        String json = null;
        try {
            json = mapper.writeValueAsString(listNode);
            JsonArray jsonObjectArray = new JsonParser().parse(json).getAsJsonArray();
            JsonArray jsonElement = new JsonParser().parse(jsonObjectArray.get(0).getAsString()).getAsJsonArray();
            for (int i = 0; i < jsonElement.size(); i++) {
                WebFormat webFormat = new Gson().fromJson(jsonElement.get(i).toString(), WebFormat.class);
                listWebFormat.add(webFormat);
            }

            return listWebFormat;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listWebFormat;
    }

    /**
     * Get All News from api
     *
     * @param client OkHttpClient
     * @return list
     */
    private static List<News> getAllNewsByWebsourceId(int websourceId, OkHttpClient client) {
        List<News> listNews = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        List<String> jsonNews = okHttpDataFunction.getAllNewsByWebsourceId(client, websourceId);
        if (jsonNews.size() != 0) {
            String jsonListNews = null;
            try {
                jsonListNews = mapper.writeValueAsString(jsonNews);
                JsonArray jsonArrayListNews = new JsonParser().parse(jsonListNews).getAsJsonArray();
                JsonArray itemNewsElement2 = new JsonParser().parse(jsonArrayListNews.get(0).getAsString()).getAsJsonArray();

                for (int i = 0; i < itemNewsElement2.size(); i++) {
                    String b = itemNewsElement2.get(i).toString();
                    News news = gSon.fromJson(b, News.class);
                    listNews.add(news);
                }
            } catch (JsonProcessingException e) {
                log.error(e.toString());
            }
        }
        return listNews;
    }

    /**
     * Get All Website
     *
     * @param client OkHttpClient
     * @return list
     */
    public static List<Websource> getAllWebSite(OkHttpClient client) {
        List<Websource> listWebSource = new ArrayList<>();
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        try {
            List<String> listUrl = okHttpDataFunction.getAllWeb(client);
            json = mapper.writeValueAsString(listUrl);
            JsonArray jsonWebSourceArray = new JsonParser().parse(json).getAsJsonArray();
            JsonArray jsonSourceArray = new JsonParser().parse(jsonWebSourceArray.get(0).getAsString()).getAsJsonArray();
            for (int i = 0; i < jsonSourceArray.size(); i++) {
                String jsonElement = jsonSourceArray.get(i).toString();
                Websource webSource = new Gson().fromJson(jsonElement, Websource.class);
                listWebSource.add(webSource);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listWebSource;
    }

    /**
     * Get And Insert Content Detail By Id News
     *
     * @param listNews     list Of News
     * @param client       okHttpClient
     */
    public static void getAndInsertContentDetailByIdNews(List<News> listNews, OkHttpClient client) {
        ObjectMapper mapper = new ObjectMapper();
        CrawData crawData = new CrawData();
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        for (News itemNews : listNews) {
            if (!checkExistContent(itemNews.getId())) {
                log.debug("website: " + itemNews.getLink());
                List<ContentFormat> contentFormatList = new ArrayList<>();
                List<String> idCategory = okHttpDataFunction.getCategoryByNewsCategory(client, itemNews.getCategory().getName());
                List<Category> categoryList = new ArrayList<>();
                String json = null;
                try {

                    json = mapper.writeValueAsString(idCategory);
                    JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
                    JsonArray jsonCategoryId = new JsonParser().parse(jsonArray.get(0).getAsString()).getAsJsonArray();

                    String jsonElement = jsonCategoryId.get(0).toString();
                    Category category = new Gson().fromJson(jsonElement, Category.class);
                    categoryList.add(category);


                    List<WebFormat> listWebFormat = getAllWebFormatByWebsiteIdAndCategoryId(client, itemNews.getWebsource().getId(), itemNews.getCategory().getId());
                    WebDriver driverContent = crawData.setUpConnecttionUrl(itemNews.getLink());
                    if(driverContent != null) {
                        for (WebFormat itemWebFormat : listWebFormat) {
                            ContentFormat contentFormat = new Gson().fromJson(itemWebFormat.getFormatContentDetail(), ContentFormat.class);
                            contentFormatList.add(contentFormat);
                            for (ContentFormat itemContentFormat : contentFormatList) {
                                if (contentFormatList.size() != 0) {
                                    List<String> nodeTime = itemContentFormat.getTime();
                                    List<String> nodeContent = itemContentFormat.getContent();
                                    List<String> nodeCategory = itemContentFormat.getCategory();

                                        Date now = new Date();
                                        Document docDetail = Jsoup.parse(driverContent.getPageSource());
                                        String contentOfTitle = crawData.getContentOfTitle(nodeContent, docDetail);
                                        String timeCreateOfContent = crawData.getTimeCreateOfContent(nodeTime, docDetail);
                                        String categoryOfContent = crawData.getCategoryOfContent(nodeCategory, docDetail);
                                        Content content = new Content(itemNews.getId(), contentOfTitle, categoryOfContent, timeCreateOfContent, now, now, itemNews);
                                        String jsonListContent = gSon.toJson(content);
                                        okHttpDataFunction.createContentById(client, jsonListContent, itemNews.getId());

                                }
                            }
                        }
                        driverContent.close();
                        driverContent.quit();
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    /**
     * Get link href by category parentnode
     *
     * @param listWebformatHome      list webformat home
     * @param listParentNodeCategory list parent node category
     * @return list menu
     */
    public static List<Menu> getLinkHrefByCategoryParentNode(List<WebFormat> listWebformatHome, List<Element> listParentNodeCategory) {
        List<Menu> urlCategory = new ArrayList<>();
        for (Element itemElement : listParentNodeCategory) {
            for (WebFormat itemWebformat : listWebformatHome) {
                List<CategoryFormat> listCategoryFormat = new ArrayList<>();
                CategoryFormat categoryFormat = new Gson().fromJson(itemWebformat.getCategoryFormat(), CategoryFormat.class);
                listCategoryFormat.add(categoryFormat);

                Elements listElement = itemElement.getElementsByTag(listCategoryFormat.get(0).getCategoryNode());
                if (listElement.size() != 0) {
                    for (Element itemElement1 : listElement) {
                        if (itemElement1.getElementsByTag("a").size() != 0) {
                            for (String item : listCategoryFormat.get(0).getCategoryTitle()) {
                                if (itemElement1.getElementsByTag("a").first().text().equals(item)) {
                                    urlCategory.add(new Menu(itemWebformat.getCategory(), itemElement1.getElementsByTag("a").first().absUrl("href")));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return urlCategory;
    }

    /**
     * Check exist url
     *
     * @param title    title news
     * @param listNews array news
     * @return boolean
     */
    public static boolean checkExistNews(String title, String url, List<News> listNews) {
        boolean exist = false;
        if (listNews.size() > 0) {
            for (News news : listNews) {
                if (news.getTitle().equals(title) || news.getLink().equals(url)) {
                    exist = true;
                }
            }
        }
        return exist;
    }

    /**
     * Check exist content
     *
     * @param id id
     * @return boolean
     */
    public static boolean checkExistContent(Long id) {
        boolean exist = false;
        OkHttpClient client = new OkHttpClient();
        List<Content> contentList = getContentByIdNews(id, client);

        if (contentList.size() != 0) {
            exist = true;
        }
        return exist;
    }


    /**
     * Get Content by id
     *
     * @param id     id
     * @param client client
     * @return list Content
     */
    public static List<Content> getContentByIdNews(Long id, OkHttpClient client) {
        List<Content> listContent = new ArrayList<>();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        ObjectMapper mapper = new ObjectMapper();
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        List<String> contentJson = okHttpDataFunction.getContentbyIdNews(client, id);
        if (contentJson.size() != 0) {
            String jsonListContent = null;
            try {
                jsonListContent = mapper.writeValueAsString(contentJson);
                JsonArray jsonArrayListNews = new JsonParser().parse(jsonListContent).getAsJsonArray();
                JsonArray itemNewsElement2 = new JsonParser().parse(jsonArrayListNews.get(0).getAsString()).getAsJsonArray();

                for (int i = 0; i < itemNewsElement2.size(); i++) {
                    String b = itemNewsElement2.get(i).toString();
                    Content content = gSon.fromJson(b, Content.class);
                    listContent.add(content);
                }
            } catch (JsonProcessingException e) {
                log.error("error : " + e.toString());
            }
        }
        return listContent;
    }

    /**
     * Insert News
     *
     * @param client          clien
     * @param itemUrl         item url
     * @param itemUrlCategory item category
     * @param listFormat      list format
     * @param doc             document
     */
    public static void InsertNews(OkHttpClient client, Websource itemUrl, Category itemUrlCategory, List<Format> listFormat, Document doc) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CrawData crawData = new CrawData();
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        List<String> listRoot = listFormat.get(0).getNodeRoot();
        List<String> listTitle = listFormat.get(0).getNodeTitle();
        List<String> listImg = listFormat.get(0).getNodeImage();
        List<String> listDescription = listFormat.get(0).getNodeDescription();
        List<News> listTitleAndLink = crawData.getElementContentOfNews(itemUrlCategory, listRoot, listTitle, listImg, listDescription, doc);
        // Insert title and Link href to database
        for (int i = listTitleAndLink.size() - 1; i > 0; i--) {
            boolean existUrl = okHttpDataFunction.checkExistNewsInUrl(client, listTitleAndLink.get(i).getHref(), String.valueOf(itemUrl.getId()));
            if (!existUrl) {
                Date now = new Date();

                News news = new News(
                        listTitleAndLink.get(i).getTitle(),
                        listTitleAndLink.get(i).getLink(),
                        listTitleAndLink.get(i).getImage(),
                        listTitleAndLink.get(i).getCategory(),
                        itemUrl.getId(),
                        listTitleAndLink.get(i).getDescription(),
                        listTitleAndLink.get(i).getHref(),
                        (long) 0, 0,
                        now, now,
                        itemUrl,listTitleAndLink.get(i).getPathImage());
                try {
                    String jsonListNews = mapper.writeValueAsString(news);
                    okHttpDataFunction.createNews(client, jsonListNews);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Insert data to database
     *
     * @param itemUrl
     * @param client
     */
    public static void InsertDataToDatabase(Websource itemUrl, OkHttpClient client) {
        // Set up connect and create table Db
        CrawData crawData = new CrawData();
        WebDriver driver = crawData.setUpConnecttionUrl(itemUrl.getUrl());

        try {
           if (driver != null) {

                List<WebFormat> listWebformatHome = getAllWebFormatByWebsiteId(client, itemUrl.getId());
                if (listWebformatHome.size() != 0) {
                    if (!listWebformatHome.get(0).getCategoryMenu().equals("")) {
                        String nodeMenu = listWebformatHome.get(0).getCategoryMenu();
                        if(driver.findElements(By.className(nodeMenu)).size() == 0) {
                            WebDriverWait wait2 = new WebDriverWait(driver, 10);
                            wait2.until(ExpectedConditions.elementToBeClickable(By.id(nodeMenu)));
//                            wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(listWebformatHome.get(0).getCategoryParent()))));
//                            System.out.println(driver.findElements(By.className(listWebformatHome.get(0).getCategoryParent())).size());
                        } else {
                            driver.findElement(By.className(nodeMenu)).click();
                        }
                    }

                    String document = driver.getPageSource();
                    driver.quit();
                    if (document != null) {
                        Document doc = Jsoup.parse(document);
                        doc.setBaseUri(itemUrl.getUrl());
                        Elements listParentNodeCategory = null;
                        List<Element> listElementParentNodeCategory = new ArrayList<>();
                        if(doc.getElementsByClass(listWebformatHome.get(0).getCategoryParent()).size() != 0) {
                            listParentNodeCategory = doc.getElementsByClass(listWebformatHome.get(0).getCategoryParent());
                            listElementParentNodeCategory = listParentNodeCategory;
                        } else {
                            Element itemParentNodeTemp = doc.getElementById(listWebformatHome.get(0).getCategoryParent());
                            listElementParentNodeCategory.add(itemParentNodeTemp);
                        }
                        // Get Url by category
                        List<Menu> listUrlCategory = getLinkHrefByCategoryParentNode(listWebformatHome, listElementParentNodeCategory);

                        if (listUrlCategory.size() == 0) {
                            log.error(itemUrl.getUrl() + " : Can't get url of category !");
                        } else {
                            for (Menu itemUrlCategory : listUrlCategory) {
                                WebDriver driverCategory = crawData.setUpConnecttionUrl(itemUrlCategory.getHref());
                                String documentCategory = driverCategory.getPageSource();
                                if (documentCategory != null) {
                                    Document docCategory = Jsoup.parse(documentCategory);
                                    docCategory.setBaseUri(itemUrl.getUrl());

                                    // get format by website id and category id
                                    List<WebFormat> listWebformat = getAllWebFormatByWebsiteIdAndCategoryId(client, itemUrl.getId(), itemUrlCategory.getCategory().getId());
                                    List<Format> listFormat = new ArrayList<>();

                                    Format formatString = new Gson().fromJson(listWebformat.get(0).getFormat(), Format.class);
                                    listFormat.add(formatString);

                                    // get data by home website
//                                  InsertNews(client, itemUrl, "", listFormat, doc);

                                    // get data by category website
                                    InsertNews(client, itemUrl, itemUrlCategory.getCategory(), listFormat, docCategory);
                                }
                                if (driverCategory != null) {
                                    driverCategory.quit();
                                }
                            }
                        }
                    }
                } else {
                    log.error("Don't have website format..........!");
                }
            }

        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String log4jConfPath = "./src/properties/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        OkHttpDataFunction okHttpDataFunction = new OkHttpDataFunction();
        ConnectDb();
//        System.out.println("========= Sign in ==========");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter email or username : ");
//        String email = scanner.nextLine();
//        System.out.print("Enter password : ");
//        String password = scanner.nextLine();

//            Timer timer = new Timer();
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    OkHttpClient client = new OkHttpClient();
//                    List<Websource> listWebSource = getAllWebSite(client);
//                    List<Category> listCategory = getAllCategory(client);
//                    for (Websource itemUrl : listWebSource) {
////                        if(itemUrl.getId() == 34) {
//                            System.out.println("=================================================== Get News =================================================================");
//                            log.debug("Tracking url : " + itemUrl.getUrl());
//                            InsertDataToDatabase(itemUrl, client);
////                        }
//                    }
//
////                    for (Websource itemUrl : listWebSource) {
////                        System.out.println("=================================== Get Content ==============================================================");
////                        List<News> listNews = getAllNewsByWebsourceId((int) itemUrl.getId(), client);
////                        getAndInsertContentDetailByIdNews(listNews, client);
////
////                    }
//
//                    System.out.println("Finished get data at " + new Date());
//                }
//            };
//            timer.schedule(timerTask, 1000, 1800000);
        

    }


}

