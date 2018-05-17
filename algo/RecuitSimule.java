package algo;

import generic.AlgorithmeAbstract;
import generic.ProblemeAbstract;
import generic.SolutionAbstract;

import java.util.List;
import java.util.Random;

/**
 * classe recuit simulé
 * <p>
 * herite de Algrotihme, à redefinir
 * <p>
 * attribut temperature
 * <p>
 * iteration doit - recuperer le voisinage - choisir au hasard un point - tirer
 * un nombre aleatoire - faire évoluer la temperature
 * 
 * 
 * @author vthomas
 * 
 */
public class RecuitSimule extends AlgorithmeAbstract {

	/**
	 * variation de temperature par iteration
	 */
	public double DELTA_TEMPERATURE = 0.999;

	/**
	 * la temperature qui décroit au cours du temps;
	 */
	double temperature;

	/**
	 * valeur de la solution en cours
	 */
	double valeurActuelleSolution = 0;
	
	/**
	 * instance de la classe random
	 * pour le choix au hasard d'un voisin
	 */
	Random random = new Random();

	/**
	 * constructeur
	 * 
	 * @param probleme
	 *            probleme à resoudre
	 * @param solutionInitiale
	 *            solution initiale fournie
	 * @param temperatuer
	 *            temperature initiale
	 */
	public RecuitSimule(ProblemeAbstract probleme, SolutionAbstract solutionInitiale, double temperature) {
		super(probleme, solutionInitiale);
		this.temperature = temperature;
	}

	@Override
	/**
	 * lance une iteration.
	 * <p>
	 * A completer en utilisant les méthodes
	 * <ul>
	 * <li>choisirHasard
	 * <li>estAccepte
	 * </ul>
	 */
	public boolean ameliorerSolution() {

		// Nombre de tirage de solution au hasard parmi les voisins
		int nombreTirages = 0;

		// a completer en utilisant les méthodes
		// * choisirHasard
		// * estAccepte

		// on limite le nombre de tirages non accepte a un nombre maximal (1000)
		while (nombreTirages <= 1000) {
			// On choisit une soluton au hasard parmi les voisines
			SolutionAbstract solutionHasard = choisirHasard();
			// Si elle est acceptée, on la prend et on met la température à jour
			if (estAcceptee(solutionHasard)) {
				solutionEnCours = solutionHasard;
				miseAJourTemperature();
				return true;
			}
			
			nombreTirages++;
		}

		return false;
	}

	private SolutionAbstract choisirHasard(){
		return solutionEnCours.retourneVoisinage().get(random.nextInt(solutionEnCours.retourneVoisinage().size()));
	}

	private void miseAJourTemperature() {
		this.temperature = this.temperature * DELTA_TEMPERATURE;
	}

	/**
	 * méthode stochastique pour savoir si une solution est validée
	 * 
	 * @param solutionAbstract
	 *            solution à comparer à la solution actuelle
	 * @return booleen qui valide ou non
	 */
	private boolean estAcceptee(SolutionAbstract solution) {
		Random r = new Random();

		// a completer en utilisant probaMetropolis
		// Si évaluation de "solution" meilleure que l'évaluation de la solution courante
		// on accepte
		if (problemeATraiter.evaluation(solution) < problemeATraiter.evaluation(solutionEnCours))
			return true;
		else {
			if (probaMetropolis(problemeATraiter.evaluation(solution) - problemeATraiter.evaluation(solutionEnCours)) > r.nextDouble())
				return true;
		}

		return false;
	}

	/**
	 * retourne la proba d'acceptation en fonction de la difference de valeur et
	 * de la temperature
	 * 
	 * @param deltaValeur
	 *            difference de valeur entre solution courante et la nouvelle
	 *            solution
	 * 
	 * @return probabilite d'accepter la nouvelle solution
	 */
	private double probaMetropolis(double deltaValeur) {
		// exp(-delta/T)
		return Math.exp(-deltaValeur/temperature);
	}

	/**
	 * par defaut on affiche aussi la temperature
	 */
	public String log() {
		return super.log() + "; T;" + temperature;
	}

}
