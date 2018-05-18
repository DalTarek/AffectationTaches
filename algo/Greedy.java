package algo;

import java.util.List;

import generic.AlgorithmeAbstract;
import generic.ProblemeAbstract;
import generic.SolutionAbstract;

/**
 * classe representant un algorithme de type greedy
 * 
 * @author vthomas
 *
 */
public class Greedy extends AlgorithmeAbstract {

	/**
	 * constructeur d'un algorithme greedy
	 * 
	 * @param probleme
	 *            le probleme a resoudre
	 * @param initiale
	 *            la solution initiale a modifier
	 */
	public Greedy(ProblemeAbstract probleme, SolutionAbstract initiale) {
		super(probleme, initiale);
	}

	/**
	 * permet d'améliorer la solution selon une approche gloutonne
	 */
	public boolean ameliorerSolution() {
		boolean fin = false;
		
		// voisins de la solution en cours
		List<SolutionAbstract> voisins = solutionEnCours.retourneVoisinage();

		// on prend le max de ces voisins
		double min = Integer.MAX_VALUE;
		double minAvant = min;
		for (SolutionAbstract v : voisins) {
			double val = problemeATraiter.evaluation(v);
			if (val <= min) {
				min = val;
				solutionEnCours = v;
			}
		}
		
		if (min == minAvant)
			fin = true;

		return fin;
	}

}
