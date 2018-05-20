package algorithmes;

import java.io.FileWriter;
import java.io.IOException;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;

public class AlgorithmeParcoursProfondeur extends AlgorithmeAbstract {

	public AlgorithmeParcoursProfondeur(Problem p) {
		super(p);
	}

	@Override
	/**
	 * cherche la meilleure solution par appels récursifs
	 */
	public SolutionPartielle construireMeilleur(FileWriter f) {
		SolutionPartielle s = (calculerMax(this.problemeAResoudre.solutionInitiale(), f));
		System.out.println("Nombre d'itérations : " + this.compteur);
		return s;
	}

	/**
	 * méthode recursive appelée pour effectuee une recherche en profondeur.
	 * <p>
	 * le principe ==> la meilleure solution est la meilleure des solutions
	 * completes construite sur les fils de la solution courante
	 * 
	 * @param solutionInitiale
	 *            solution partielle dont on souhaite la meilleure solution
	 * @return meilleure solution en dessous de la solution initiale
	 */
	private SolutionPartielle calculerMax(SolutionPartielle solutionInitiale, FileWriter f) {

		this.compteur++;

		// on arrete la recursion si on est sur une solution valide
		if (solutionInitiale.estComplete()) {
			return (solutionInitiale);
		}

		// sinon on cherche la meilleure solution sur le meilleur fils
		SolutionPartielle[] solutionVoisines = solutionInitiale.solutionsVoisines();
		double min = Double.MAX_VALUE;
		SolutionPartielle meilleureGlobale = null;

		for (SolutionPartielle solutionAEtudier : solutionVoisines) {
			// trouve la meilleure solution complete à partir du fils
			// par appel recusrif
			SolutionPartielle meilleureFille = calculerMax(solutionAEtudier, f);
			// regarde si cette solution complete est la meilleure
			double evaluation = this.problemeAResoudre.evaluer(meilleureFille);
			if (evaluation < min) {
				min = evaluation;
				meilleureGlobale = meilleureFille;
			}

			if (this.compteur % 1000 == 0) {
				// afficher le nb iterations tous les 1000 pas de temps
				// System.out.println("nombre iteration:" + this.compteur);
			}
		}

		return meilleureGlobale;
	}

}
