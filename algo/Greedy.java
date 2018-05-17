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
		SolutionAbstract voisinChoisi = null;
		// On stocke la valeur de la solution courante
		double valeurInitiale = valeur();

		// On prend parmi les voisins de la solution courante
		// la solution qui donne la plus grande valeur
		double minVoisins = valeurInitiale;
		for (SolutionAbstract voisin : solutionEnCours.retourneVoisinage()) {
			double valeurVoisin = problemeATraiter.evaluation(voisin);
			if (valeurVoisin < minVoisins) {
				voisinChoisi = voisin;
				minVoisins = valeurVoisin;
			}
		}
		
		// on s'arrete si la solution ne s'ameliore plus
		if (minVoisins == valeurInitiale)
			return false;

		// On change la solution courante
		solutionEnCours = voisinChoisi;
		return true;
	}

}
