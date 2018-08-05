package week_1.problem_set.wordnet;

/*********
 *
 * @author Roshan Munjal
 *
 * WordNet Module API.
 *
 *********/

import java.util.ArrayList;
import java.util.HashMap;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

public class WordNet {

	private final HashMap<Integer, String> idToSynset = new HashMap<>();
	private final HashMap<String, ArrayList<Integer>> synonymToId = new HashMap<>();
	private final Digraph wordnetGraph;
	private final SAP shortestAncestralPath;

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null) {
			throw new IllegalArgumentException("File synsets or hypernyms cannot be null.");
		}
		In synsetsIn = new In(synsets);
		In hypernymsIn = new In(hypernyms);

		// Build synsets to ids.
		while (!synsetsIn.isEmpty()) {
			String[] values = synsetsIn.readLine().split(",");
			int givenId = Integer.parseInt(values[0]);
			String givenSynset = values[1];
			idToSynset.put(givenId, givenSynset);
			String[] synonyms = givenSynset.split(" ");

			for (String synonym : synonyms) {
				if (!synonymToId.containsKey(synonym)) {
					synonymToId.put(synonym, new ArrayList<>());
				}
				synonymToId.get(synonym).add(givenId);
			}
		}

		// Build wordnet digraph.
		wordnetGraph = new Digraph(idToSynset.size());
		while (!hypernymsIn.isEmpty()) {
			String[] values = hypernymsIn.readLine().split(",");
			int synset = Integer.parseInt(values[0]);

			for (int i = 1; i < values.length; i++) {
				wordnetGraph.addEdge(synset, Integer.parseInt(values[i]));
			}
		}

		// Check invalid conditions.
		if (new DirectedCycle(wordnetGraph).hasCycle()) {
			throw new IllegalArgumentException("Wordnet graph cannot have a cycle.");
		}

		int graphRoots = 0;
		for (int i = 0; i < wordnetGraph.V(); i++) {
			if (!wordnetGraph.adj(i).iterator().hasNext()) {
				graphRoots++;
			}
		}
		if (graphRoots != 1) {
			throw new IllegalArgumentException("Wordnet graph must have a single root.");
		}

		// Build shortest ancestral path.
		shortestAncestralPath = new SAP(wordnetGraph);
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return synonymToId.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		if (word == null) {
			throw new IllegalArgumentException("Given noun cannot be null.");
		}
		return synonymToId.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException("Given nouns cannot be null.");
		}
		return shortestAncestralPath.length(synonymToId.get(nounA), synonymToId.get(nounB));
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException("Given nouns cannot be null.");
		}
		return idToSynset.get(shortestAncestralPath.ancestor(synonymToId.get(nounA), synonymToId.get(nounB)));
	}

	public static void main(String[] args) {
	}

}
