package week_1.lectures.directed_graphs;

public class Main {

	public static void main(String[] args) {
		webCrawlerTest("https://www.princeton.edu");
	}

	public static void webCrawlerTest(String root) {
		WebCrawler princetonWebCrawler = new WebCrawler(root);
	}

}
