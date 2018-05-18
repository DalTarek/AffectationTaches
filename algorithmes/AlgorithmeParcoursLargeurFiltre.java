package algorithmes;

import java.util.ArrayList;
import java.util.LinkedList;

import generic.AlgorithmeAbstract;

import generic.Problem;
import generic.SolutionPartielle;

/**
 * effectue une recherche en largeur d'abord mais filtre pendant la recherche
 * les solutions invalides.
 */
public class AlgorithmeParcoursLargeurFiltre extends AlgorithmeAbstract {

	/**
	 * construit une recherche en largeur d'abord avec filtre des solutions
	 * invalides
	 * 
	 * @param p
	 *            probleme a resoudre
	 */
	public AlgorithmeParcoursLargeurFiltre(Problem p) {
		super(p);
	}

	@Override
	/**
	 * construit la meilleure solution
	 */
	public SolutionPartielle construireMeilleur() {
		
		int i = 0;

		// construit liste des solutions a tester
		LinkedList<SolutionPartielle> ouverte = new LinkedList<SolutionPartielle>();
		ouverte.add(this.problemeAResoudre.solutionInitiale());

		// construit liste solution complete (au debut vide)
		ArrayList<SolutionPartielle> complete = new ArrayList<SolutionPartielle>();

		// partie qui contruit la liste des solutions completes avec parcours en largeur
		// en utilisant ouvert et ferme
		// afficher le nb iterations tous les 1000 pas de temps

		// PENSER a retirer les solutions invalides au fur et a mesure du parcours

		while (!ouverte.isEmpty()) {
			SolutionPartielle solutionCourante = ouverte.getFirst();
			ouverte.removeFirst();
			
			// a chaque pas de temps
			// - soit la solution est complete => ajoute aux solutions finies 
			if (solutionCourante.estComplete())
				complete.add(solutionCourante);
			else { // - soit la solution est incomplete => ajoute les fils dans ouvert
				for (SolutionPartielle fils : solutionCourante.solutionsVoisines()) {
					if (!fils.invalide())
						ouverte.addLast(fils);
				}
			}
			
			i++;
		
			if (i % 10000 == 0) 
			// afficher le nb iterations tous les 1000 pas de temps
				System.out.println("nombre iteration:" + i);		
		}
		
		System.out.println("nombre iteration:" + i);

		// retourner la meilleure solution complete
		SolutionPartielle meilleure = chercherMeilleureSolution(complete);

		return (meilleure);
	}

	/**
	 * cherche la meilleure solution dans une liste
	 * 
	 * @param liste
	 *            dont on cherche la meilleure solution
	 * @return meilleure solution de la liste
	 */
	private SolutionPartielle chercherMeilleureSolution(ArrayList<SolutionPartielle> liste) {
		SolutionPartielle meilleur = null;
		double max = -1;
		for (SolutionPartielle s : liste) {
			if (this.problemeAResoudre.evaluer(s) >= max) {
				max = this.problemeAResoudre.evaluer(s);
				meilleur = s;
			}
		}
		return meilleur;
	}

}
