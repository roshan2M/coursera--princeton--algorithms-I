package week_1.lectures.directed_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	public ArrayDeque<String> queue = new ArrayDeque<>();
	public HashSet<String> discovered = new HashSet<>();

	public WebCrawler(String root) {
		queue.offer(root);
		discovered.add(root);
		String webText = "";

		// Read the web text.
		try {
			URL url = new URL(root);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
			}
			webText = stringBuilder.toString();
		} catch (IOException e) {
			System.out.println("Could not get valid text from URL: " + root);
		}

		// Find and append all web links.
		String regexp = "http://(\\w+\\.)*(\\w+)";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(webText);
		while (matcher.find()) {
			String link = matcher.group();
			if (!discovered.contains(link)) {
				System.out.println("Found a unique link: " + link);
				discovered.add(link);
				queue.offer(link);
			}
		}
	}

}
