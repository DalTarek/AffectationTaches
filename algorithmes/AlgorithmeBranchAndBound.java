package algorithmes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import generic.AlgorithmeAbstract;
import generic.Heuristique;
import generic.Problem;
import generic.SolutionPartielle;

/**
 * classe a completer qui permet d'effectuer un branch and bound <li>partie
 * branch : recherche en margeur d'abord <li>partie bound : utilise une
 * heuristique optimiste
 * 
 * la partie bound se basera sur la methode filtrer a ecrire.
 * 
 * @author vthomas
 * 
 */
public class AlgorithmeBranchAndBound extends AlgorithmeAbstract {

	/**
	 * l'heuristique utilisee dans le branch and bound
	 */
	Heuristique heuristique;

	/**
	 * constructeur d'un algorithme
	 * 
	 * @param p
	 *            le probleme a traiter
	 * @param h
	 *            l'heuristique a utiliser
	 */
	public AlgorithmeBranchAndBound(Problem p, Heuristique h) {
		super(p);
		this.heuristique = h;
	}

	@Override
	public SolutionPartielle construireMeilleur(FileWriter f) {

		// construit liste des solutions a tester
		LinkedList<SolutionPartielle> ouverte = new LinkedList<SolutionPartielle>();
		ouverte.add(this.problemeAResoudre.solutionInitiale());

		// construit liste solution complete (au debut vide)
		ArrayList<SolutionPartielle> complete = new ArrayList<SolutionPartielle>();

		// valeur maximale observee (utilisee pour filtrer dans branch and
		// bound)
		double minGaranti = Double.MAX_VALUE;

		// partie qui contruit la liste des solutions completes avec parcours en
		// largeur
		// en utilisant ouvert et ferme
		// afficher le nb iterations tous les 1000 pas de temps

		// PENSER : a retirer les solutions invalides au fur et a mesure du
		// parcours

		while (!ouverte.isEmpty()) {
			SolutionPartielle solutionCourante = ouverte.getFirst();
			ouverte.removeFirst();

			// a chaque pas de temps
			// - soit la solution est complete => ajoute aux solutions finies
			if (solutionCourante.estComplete())
				complete.add(solutionCourante);
			else { // - soit la solution est incomplete => ajoute les fils dans
					// ouvert
				for (SolutionPartielle fils : solutionCourante.solutionsVoisines()) {
					if (!fils.invalide()) {

						// on regarde la valeur de evaluation du fils +
						// heuristique du fils
						double minFils = problemeAResoudre.evaluer(fils);
						double maxFils = minFils + heuristique.estimer(fils);

						// si le fils ameliore mon maximum garanti
						if (maxFils < minGaranti)
							minGaranti = maxFils;

						// si la valeur esperee au mieux est plus grande que le
						// maximum garanti
						// on elague
						// sinon on ajoute à la liste ouverte
						if (maxFils <= minGaranti) {
							ouverte.addLast(fils);
						}

					}
				}
			}

			this.compteur++;

			if (this.compteur % 1000 == 0) {
				// afficher le nb iterations tous les 1000 pas de temps
				// System.out.println("nombre iteration:" + this.compteur);
				try {
					f.append("" + compteur + " " 
							+ this.problemeAResoudre.evaluer(solutionCourante) + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("nombre iteration:" + this.compteur);

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
	private SolutionPartielle chercherMeilleureSolution(
			ArrayList<SolutionPartielle> liste) {
		SolutionPartielle meilleur = null;
		double min = Double.MAX_VALUE;
		for (SolutionPartielle s : liste) {
			if (this.problemeAResoudre.evaluer(s) <= min) {
				min = this.problemeAResoudre.evaluer(s);
				meilleur = s;
			}
		}
		return meilleur;
	}

}
