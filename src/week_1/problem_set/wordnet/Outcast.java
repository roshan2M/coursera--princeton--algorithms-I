package week_1.problem_set.wordnet;

/*********
 *
 * @author Roshan Munjal
 *
 * Outcast Module API.
 *
 *********/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	private WordNet wordNet;

	// constructor takes a WordNet object
	public Outcast(WordNet givenWordNet) {
		if (givenWordNet == null) {
			throw new IllegalArgumentException("Input must be a valid WordNet.");
		}
		wordNet = givenWordNet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		if (nouns == null) {
			throw new IllegalArgumentException("List of nouns cannot be null.");
		}

		int distance, longestDistance = 0;
		String outcast = null;
		for (String nounA : nouns) {
			distance = 0;
			for (String nounB : nouns) {
				distance += wordNet.distance(nounA, nounB);
			}
			if (distance > longestDistance) {
				longestDistance = distance;
				outcast = nounA;
			}
		}
		return outcast;
	}

	// testing client for Outcast
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}
