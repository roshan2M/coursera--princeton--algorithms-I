package week_1.lectures.undirected_graphs;

import com.google.common.graph.*;
import week_1.lectures.directed_graphs.WebCrawler;
import week_1.quizzes.UndirectedGraphsQuiz;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		webCrawlerTest("https://www.princeton.edu");
	}

	public static void graphTests() {
		MutableGraph G = GraphBuilder.undirected().build();
		G.putEdge(0, 1);
		G.putEdge(0, 2);
		G.putEdge(0, 3);
		G.putEdge(2, 5);
		G.putEdge(1, 6);
		G.putEdge(1, 4);
		G.putEdge(4, 7);

		System.out.println(UndirectedGraphsQuiz.graphDiameter(ImmutableGraph.copyOf(G)));
		System.out.println(Arrays.toString(UndirectedGraphsQuiz.iterativeDFS(ImmutableGraph.copyOf(G), 0)));
		System.out.println(UndirectedGraphsQuiz.graphCenterNaive(ImmutableGraph.copyOf(G)));

		MutableGraph G2 = GraphBuilder.undirected().build();
		G2.putEdge(0, 1);
		G2.putEdge(0, 2);
		G2.putEdge(1, 2);

		System.out.println(Arrays.toString(UndirectedGraphsQuiz.eulerCycle(ImmutableGraph.copyOf(G2))));
	}

	public static void webCrawlerTest(String root) {
		WebCrawler princetonWebCrawler = new WebCrawler(root);
	}

}
