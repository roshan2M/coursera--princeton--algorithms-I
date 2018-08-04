package week_1.lectures.directed_graphs;

import com.google.common.graph.*;
import week_1.quizzes.DirectedGraphsQuiz;

public class Main {

	public static void main(String[] args) {
		webCrawlerTest("https://www.princeton.edu");
		topologicalSortTest();
		shortestDirectedCycleTest();
		hamiltonianPathTest();
	}

	public static void webCrawlerTest(String root) {
		WebCrawler webCrawler = new WebCrawler(root);
	}

	public static void topologicalSortTest() {
		MutableGraph G = GraphBuilder.directed().build();
		G.putEdge(0, 1);
		G.putEdge(0, 2);
		G.putEdge(1, 3);
		G.putEdge(3, 4);
		G.putEdge(2, 4);
		G.putEdge(4, 5);

		TopologicalSort graphSort = new TopologicalSort(ImmutableGraph.copyOf(G));
		System.out.println("Reverse post notation in graph: " + graphSort.reversePost().toString());
	}

	public static void shortestDirectedCycleTest() {
		MutableGraph G = GraphBuilder.directed().build();
		G.putEdge(0, 1);
		G.putEdge(0, 2);
		G.putEdge(1, 3);
		G.putEdge(3, 4);
		G.putEdge(2, 4);
		G.putEdge(4, 5);
		G.putEdge(4, 0);
		G.putEdge(5, 0);

		System.out.println("Shortest directed cycle: " + DirectedGraphsQuiz.shortestDirectedCycle(G));
	}

	public static void hamiltonianPathTest() {
		MutableGraph G = GraphBuilder.directed().build();
		G.putEdge(0, 1);
		G.putEdge(0, 2);
		G.putEdge(1, 3);
		G.putEdge(3, 4);
		G.putEdge(2, 4);
		G.putEdge(4, 5);
		G.putEdge(4, 0);
		G.putEdge(5, 0);

		System.out.println("Hamiltonian path: " + DirectedGraphsQuiz.hamiltonianPath(ImmutableGraph.copyOf(G)));
	}

}
