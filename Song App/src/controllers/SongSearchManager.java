package controllers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static userInterface.Display.printRandomColorForPattern;
import static userInterface.Display.printlnRandomColor;

/**
 * 此类用于搜索歌曲。
 * This class is used to search for songs.
 *
 * @author Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class SongSearchManager {

    private final OkHttpClient okHttpClient;

    /**
     * 构造函数，初始化 okHttpClient 实例。
     * Constructor for SongSearchManager. Initializes the OkHttpClient instance.
     *
     * @author Siobhan Drohan, Mairead Meagher, Siobhan Roche, Fan Xinkang
     * @since version 6.0
     */
    public SongSearchManager() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * 根据输入的 url，读取页面内容并返回。
     * Read the page content and return it based on the input URL.
     *
     * @param url 要读取的页面的 URL。
     *            The URL of the page to be read.
     * @return 页面内容，如果请求失败则返回 null。
     *         The page content, or null if the request fails.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public String getPageContentSync(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        /*
          设置请求头。
          Set request headers.
         */
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .addHeader("Referer", "https://www.douban.com/")
                .addHeader("Host", "search.douban.com")
                .build();

        /*
          发送请求并获取响应。
          Send the request and get the response.
         */
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                printlnRandomColor("Request failed, status code: " + response.code());
            }
        } catch (IOException e) {
            printlnRandomColor("Request " + url + " failed。");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据输入的歌曲名字构建搜索URL。
     * Builds the search URL based on the input song name.
     *
     * @param query 要搜索的歌曲名字。
     *              The song name to search for.
     * @return 搜索URL。
     *         The search URL.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public String buildSearchUrl(String query) {

        /*
          特别说明：目前无法成功爬取数据，即使使用 API 密钥也无法解决，可能是豆瓣网站设置了严密的反爬虫系统，或阻止了部分浏览器访问。
          Special explanation: Unable to successfully crawl data, even if the API key is used, cannot solve, because the Douban website sets up a rigorous anti-crawler system, or prevents some browsers from accessing.
         */
        return "https://search.douban.com/music/subject_search?search_text=" + encodeQuery(query);
    }

    /**
     * 对查询参数进行 URL 编码。
     * Encodes the query parameter for URL.
     *
     * @param query 要编码的查询参数。
     *              The query parameter to encode.
     * @return 编码后的查询参数。
     *         The encoded query parameter.
     * @author Fan Xinkang
     * @since version 6.0
     */
    private String encodeQuery(String query) {
        try {
            return java.net.URLEncoder.encode(query, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return query;
        }
    }

    /**
     * 解析页面内容并提取歌曲信息。
     * Parses the page content and extracts song information.
     *
     * @param pageContent 要解析的页面内容。
     *                    The page content to parse.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public void parseSongInfo(String pageContent) {
        if (pageContent == null) {
            printlnRandomColor("Search failed.");
            return;
        }

        /*
          使用 Jsoup 解析页面内容。
          Parses the page content using Jsoup.
         */
        try {
            Document document = Jsoup.parse(pageContent);
            Elements items = document.select("div.result.item");

            if (items.isEmpty()) {
                printlnRandomColor("No relevant song information was found.");
                return;
            }

            /*
              遍历每个结果项。
              Traverses each result item.
             */
            for (Element item : items) {
                String songTitle = Objects.requireNonNull(item.selectFirst("a.title")).text();
                String songUrl = Objects.requireNonNull(item.selectFirst("a.title")).attr("href");

                /*
                  提取歌手信息。
                  Extracts singer information.
                 */
                String artistName = item.selectFirst("a[href^='/people/']") != null ? item.selectFirst("a[href^='/people/']").text() : "Unknown singer.";

                /*
                  提取评分信息。
                  Extracts rating information.
                 */
                String ratingValue = item.selectFirst("span.rating_nums") != null ? item.selectFirst("span.rating_nums").text() : "No rating for the time being.";

                /*
                  提取简介信息。
                  Extracts abstract information.
                 */
                String abstractText = item.selectFirst("p.pl") != null ? Objects.requireNonNull(item.selectFirst("p.pl")).text() : "No introduction for the time being";

                printlnRandomColor("Song name: " + songTitle);
                printlnRandomColor("Song link: " + songUrl);
                printlnRandomColor("Artist: " + artistName);
                printlnRandomColor("Song rating: " + ratingValue);
                printlnRandomColor("Song introduction: " + abstractText);
                printRandomColorForPattern("-----------------------------");
            }
        } catch (Exception e) {
            printlnRandomColor("解析页面内容时出错。");
            e.printStackTrace();
        }
    }

    /**
     * 根据输入的歌曲名字搜索并显示信息。
     * Searches for songs and displays information.
     *
     * @param query 要搜索的歌曲名字。
     *              The song name to search for.
     * @author Fan Xinkang
     * @since version 6.0
     */
    public void searchForSong(String query) {
        String url = buildSearchUrl(query);
        String pageContent = getPageContentSync(url);
        parseSongInfo(pageContent);
    }
}
/*
 * End of controllers.SearchManager Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */