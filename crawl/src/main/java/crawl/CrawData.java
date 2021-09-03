/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import CommonFeature.CommonFeature;
import Entity.Category;
import Entity.News;
import Interface.ICrawlFunction;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Anhkhoa
 */
public class CrawData implements ICrawlFunction {

    ServletContext context;
    HttpResponse httpResponse;

    @Override
    public WebDriver setUpConnecttionUrl(String url) {
        WebDriver driver = null;

        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--silent");
            chromeOptions.addArguments("--log-level=3");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("test-type");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("no-sandbox");
            chromeOptions.addArguments("disable-infobars");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("start-minimize");

            System.setProperty("Webdriver.chrome.driver", "src\\chromedriver.exe");
            System.setProperty("Webdriver.chrome.args", "--disable-logging");
            System.setProperty("Webdriver.chrome.silentOutput", "true");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

//            capabilities.setBrowserName("chrome");
//            capabilities.setVersion("77.0");
//            capabilities.setCapability("enableVNC", true);
//            capabilities.setCapability("enableVideo", false);
//            driver = new RemoteWebDriver(new URL("http://113.161.80.157:24444/wd/hub/"), capabilities);

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), capabilities);
            driver.get(url);
//            try {
//                for (int i = 0; i < 2; i++) {
//                    try {
//                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//
//            }

            return driver;
        } catch (Exception e) {
            if (driver != null) {
                driver.close();
            }
            System.out.println("Set up error : " + e);
        }
        return null;
    }

    @Override
    public void openLinkImage(String url, WebDriver driver) {
        URL urlStr = null;
        try {
            String userAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
            urlStr = new URL(url);
            URLConnection urlConnection = urlStr.openConnection();
            urlConnection.setRequestProperty("User-Agent", userAgent);
            urlConnection.setRequestProperty("Cookie", String.valueOf(driver.manage().getCookies()));
            urlConnection.setRequestProperty("Accept", "*/*");
            urlConnection.setRequestProperty("Referer", url);
            urlConnection.setRequestProperty("Connection", "keep-alive");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle(List<String> nodeTitle, Element itemElement) {
        String title = "";
        Elements elements;
        try {
            for (String itemNodeTitle : nodeTitle) {
                elements = itemElement.getElementsByClass(itemNodeTitle);
                if (elements == null || elements.size() == 0) {
                    elements = itemElement.getElementsByTag(itemNodeTitle);
                }
                title = "";
                if (elements.size() != 0) {
                    if (elements.get(0).getAllElements().first() == null) {
                        title = "";
                    } else {
                        try {
                            if (elements.get(0).getAllElements().hasAttr("title")) {
                                title = elements.get(0).getAllElements().attr("title");
                            } else {
                                title = elements.get(0).getAllElements().first().text();
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return title;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }

    @Override
    public String getHref(List<String> node, Element itemElement) {
        String href = "";
        if (node.size() != 0) {
            Elements listHref = itemElement.getElementsByTag("a");
            href = listHref.first().absUrl("href");
        }
        return href;
    }

    @Override
    public String getImage(List<String> nodeImage, Element itemElement) {
        String imgStr = null;
        Elements elementsImage;
        for (int i = 0; i < nodeImage.size(); i++) {
            imgStr = null;
//            boolean checkElementImageBig = itemElement.getElementsByClass("thumb_big").size() > 0;
             elementsImage = itemElement.getElementsByClass(nodeImage.get(i));
             if(elementsImage.size() == 0) {
                 elementsImage = itemElement.getElementsByTag(nodeImage.get(i));
             }
            Elements listImg;
            if (i < elementsImage.size()) {
                listImg = elementsImage.get(i).getAllElements();

                for(int y = 0; y < listImg.size();y++) {
                    if(listImg.get(y).hasAttr("style")) {
                        String attr = listImg.attr("style");
                        Pattern pattern = Pattern.compile("background-image");
                        Matcher matcher = pattern.matcher(attr);
                        while (matcher.find()) {
                            Pattern patternStart = Pattern.compile("https:");
                            Matcher matcherStart = patternStart.matcher(attr);

                            Pattern patternEndJPG = Pattern.compile("jpg");
                            Pattern patternEndPNG = Pattern.compile("png");
                            Pattern patternEndJPEG = Pattern.compile("jpeg");

                            Matcher matcherEndJPG = patternEndJPG.matcher(attr);
                            Matcher matcherEndPNG = patternEndPNG.matcher(attr);
                            Matcher matcherEndJPEG = patternEndJPEG.matcher(attr);
                            if(matcherEndJPG.find()) {
                                while (matcherStart.find()) {
                                    imgStr = attr.substring(matcherStart.start(),matcherEndJPG.end());
                                }
                            } else if(matcherEndPNG.find()) {
                                while (matcherStart.find() ) {
                                    imgStr = attr.substring(matcherStart.start(),matcherEndPNG.end());
                                }
                            } else if(matcherEndJPEG.find()) {
                                while (matcherStart.find()) {
                                    imgStr = attr.substring(matcherStart.start(),matcherEndJPEG.end());
                                }
                            }

                        }
                        break;
                    }

                    else if (listImg.get(y).hasAttr("data-src")) {
                        String attr = listImg.get(y).attr("data-src");
                        imgStr = CommonFeature.checkImageRequire(attr);
                        if(imgStr.equals("")) {
                            continue;
                        } else {
                            break;
                        }

                    } else if (listImg.get(y).hasAttr("data-original")) {
                        String attr = listImg.get(y).attr("data-original");
                        imgStr = CommonFeature.checkImageRequire(attr);
                        if(imgStr.equals("")) {
                            continue;
                        } else {
                            break;
                        }
                    } else if (listImg.get(y).hasAttr("src")) {
                        String attr = listImg.get(y).attr("src");
                        imgStr = CommonFeature.checkImageRequire(attr);
                        if(imgStr.equals("")) {
                            continue;
                        }
                        else {
                            break;
                        }

                    }
                }
            }
        }
        return imgStr;
    }


//    @Override
//    public String getImage(List<String> nodeImage, Element itemElement) {
//        String imgStr = "";
//        Elements elementsImage;
//        for (int i = 0; i < nodeImage.size(); i++) {
//            imgStr = "";
////            boolean checkElementImageBig = itemElement.getElementsByClass("thumb_big").size() > 0;
//            elementsImage = itemElement.getElementsByClass(nodeImage.get(i));
//            if (elementsImage.size() == 0) {
//                elementsImage = itemElement.getElementsByTag(nodeImage.get(i));
//            }
//            Elements listImg;
//            if (i < elementsImage.size()) {
//                listImg = elementsImage.get(i).getAllElements();
//
//                for (int y = 0; y < listImg.size(); y++) {
//                    if (listImg.get(y).hasAttr("style")) {
//                        String attr = listImg.attr("style");
//                        Pattern pattern = Pattern.compile("background-image");
//                        Matcher matcher = pattern.matcher(attr);
//                        while (matcher.find()) {
//                            Pattern patternStart = Pattern.compile("http");
//                            Matcher matcherStart = patternStart.matcher(attr);
//
//                            Pattern patternEndJPG = Pattern.compile("jpg");
//                            Pattern patternEndPNG = Pattern.compile("png");
//                            Pattern patternEndJPEG = Pattern.compile("jpeg");
//
//                            Matcher matcherEndJPG = patternEndJPG.matcher(attr);
//                            Matcher matcherEndPNG = patternEndPNG.matcher(attr);
//                            Matcher matcherEndJPEG = patternEndJPEG.matcher(attr);
//                            if (matcherEndJPG.find()) {
//                                while (matcherStart.find()) {
//                                    imgStr = attr.substring(matcherStart.start(), matcherEndJPG.end());
//                                }
//                            } else if (matcherEndPNG.find()) {
//                                while (matcherStart.find()) {
//                                    imgStr = attr.substring(matcherStart.start(), matcherEndPNG.end());
//                                }
//                            } else if (matcherEndJPEG.find()) {
//                                while (matcherStart.find()) {
//                                    imgStr = attr.substring(matcherStart.start(), matcherEndJPEG.end());
//                                }
//                            }
//
//                        }
//                        break;
//                    } else if (listImg.get(y).hasAttr("data-src")) {
//                        String attr = listImg.get(y).attr("data-src");
//                        imgStr = CommonFeature.checkImageRequire(attr);
//                        if (imgStr.equals("")) {
//                            continue;
//                        } else {
//                            break;
//                        }
//
//                    } else if (listImg.get(y).hasAttr("data-original")) {
//                        String attr = listImg.get(y).attr("data-original");
//                        imgStr = CommonFeature.checkImageRequire(attr);
//                        if (imgStr.equals("")) {
//                            continue;
//                        } else {
//                            break;
//                        }
//                    } else if (listImg.get(y).hasAttr("src")) {
//                        String attr = listImg.get(y).attr("src");
//                        imgStr = CommonFeature.checkImageRequire(attr);
//                        if (imgStr.equals("")) {
//                            continue;
//                        } else {
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//        String path = saveImageFromURL(imgStr);
////        return encoderBase64ImagePath(path);
//        return saveImageFromURL(imgStr);
//    }


    @Override
    public String getDescription(List<String> nodeDescription, Element itemElement) {
        String description = "";
        Elements elementsDescription;
        for (int x = 0; x < nodeDescription.size(); x++) {
            description = "";
            elementsDescription = itemElement.getElementsByClass(nodeDescription.get(x));
            if (elementsDescription == null) {
                elementsDescription = itemElement.getElementsByTag(nodeDescription.get(x));
            }
            if (elementsDescription.size() != 0) {
                description = elementsDescription.first().text();
                break;
            } else {
                continue;
            }
        }
        return description;
    }

    @Override
    public String getContentOfTitle(List<String> nodeContent, Document docDetail) {
        String content = "";
        for (String itemContent : nodeContent) {
            if (!itemContent.equals("")) {
                Elements elements = docDetail.getElementsByClass(itemContent);

                Gson gson = new Gson();
                List<String> arrayContent = new ArrayList<String>();
                try {
                    for (Element item : elements) {
                        Elements listAllTagElements = item.getAllElements();
                        for (Element itemTagElement : listAllTagElements) {
                            arrayContent.add(itemTagElement.text());
                        }
                    }
                    content = gson.toJson(arrayContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return content;
    }

    @Override
    public String getTimeCreateOfContent(List<String> nodeTime, Document docDetail) {
        String time = "";
        for (String itemTime : nodeTime) {
            if (!itemTime.equals("")) {
                Elements elements = docDetail.getElementsByClass(itemTime);
                try {
                    for (Element item : elements) {
                        time = item.text();
                    }
                    return time;
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }
        return time;
    }

    @Override
    public String getCategoryOfContent(List<String> nodeCategory, Document docDetail) {
        String category = "";
        for (String itemCategory : nodeCategory) {

            if (itemCategory != null && !itemCategory.equals("")) {
                Elements elements = docDetail.getElementsByClass(itemCategory);
                try {
                    for (Element ele : elements) {
                        Elements nameCategory = ele.getAllElements();
                        category = nameCategory.get(0).text();
                    }
                    return category;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return category;
    }

    @Override
    public List<News> getElementContentOfNews(Category categoryItem, List<String> nodeRoot, List<String> nodeTitle, List<String> nodeImage, List<String> nodeDescription, Document document) {
        List<News> arrayObject;
        arrayObject = new ArrayList<News>() {
        };
        Elements listElement = null;
        try {
            for (String itemNodeRoot : nodeRoot) {
                String title = "";
                String href = "";
                String img = "";
                String description = "";
                String pathImage = "";
                listElement = document.getElementsByTag(itemNodeRoot);

                if (listElement.size() == 0) {
                    listElement = document.getElementsByClass(itemNodeRoot);
                }
                if (listElement.size() != 0) {
                    for (Element itemElement : listElement) {
                        if (itemElement.getElementsByClass(nodeTitle.get(0)).size() != 0) {
                            title = getTitle(nodeTitle, itemElement);

                            href = getHref(nodeRoot, itemElement);
                            img = getImage(nodeImage, itemElement);;
                            description = getDescription(nodeDescription, itemElement);
                            pathImage = getImage(nodeImage, itemElement);
                            if (!title.equals("")) {
                                arrayObject.add(new News(title, href, img, categoryItem, description, CommonFeature.ConvertStringToUnsignedForms(title),pathImage));
                            }
                        } else if (itemElement.getElementsByTag(nodeTitle.get(0)).size() != 0) {
                            title = getTitle(nodeTitle, itemElement);

                            href = getHref(nodeRoot, itemElement);
                            img = getImage(nodeImage, itemElement);;
                            description = getDescription(nodeDescription, itemElement);
                            pathImage = getImage(nodeImage, itemElement);
                            if (!title.equals("")) {
                                arrayObject.add(new News(title, href, img, categoryItem, description, CommonFeature.ConvertStringToUnsignedForms(title),pathImage));
                            }
                        }
                    }
                } else {
                    System.out.println("Don't have root node..");
                }
            }
            return arrayObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayObject;
    }


    /**
     * Get image from database
     *
     * @param path path
     * @return String
     */
    private String encoderBase64ImagePath(File path) {
        String imagePathString = "";
        if (path != null) {
            File fileFolder = new File(path.getPath());
            String encodeBase64 = null;
            try {
                String extension = FilenameUtils.getExtension(fileFolder.getName());
                FileInputStream fileInputStream = new FileInputStream(fileFolder);
                byte[] bytes = new byte[(int) path.length()];
                fileInputStream.read(bytes);
                encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                imagePathString = "data:image/" + extension + ";base64," + encodeBase64;
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imagePathString;
    }

    private String convertImagePathToBlob(File file) {
        byte[] picInBytes = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(picInBytes);
    }

    /**
     * Generating random numberic String
     *
     * @return string
     */
    private String generatingRandomNumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
