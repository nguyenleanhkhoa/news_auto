package Interface;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import Entity.Category;
import Entity.News;

import java.util.List;

public interface ICrawlFunction {
    WebDriver setUpConnecttionUrl(String url);
    void openLinkImage(String url, WebDriver driver);
    String getTitle(List<String> nodeTitle, Element itemElement);
    String getHref(List<String> node, Element itemElement);
    String getImage(List<String> nodeImage, Element itemElement);
    String getDescription(List<String> nodeDescription, Element itemElement);
    String getContentOfTitle(List<String> nodeContent, Document docDetail);
    String getTimeCreateOfContent(List<String> nodeTime, Document docDetail);
    String getCategoryOfContent(List<String> nodeCategory, Document docDetail);
    List<News> getElementContentOfNews(Category categoryItem, List<String> nodeRoot, List<String> nodeTitle,
                                       List<String> nodeImage, List<String> nodeDescription, Document document);
}
