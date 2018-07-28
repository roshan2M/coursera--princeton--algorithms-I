package week_1.lectures.undirected_graphs;

import com.google.common.graph.*;
import week_1.quizzes.UndirectedGraphsQuiz;

public class Main {

	public static void main(String[] args) {
		MutableGraph G = GraphBuilder.undirected().build();
		G.putEdge(0, 1);
		G.putEdge(0, 2);
		G.putEdge(0, 3);
		G.putEdge(2, 5);
		G.putEdge(1, 6);
		G.putEdge(1, 4);
		G.putEdge(4, 7);

		System.out.println(UndirectedGraphsQuiz.longestSimplePath(ImmutableGraph.copyOf(G)));
	}

}
