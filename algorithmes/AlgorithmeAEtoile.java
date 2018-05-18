package algorithmes;

import java.util.PriorityQueue;

import generic.AlgorithmeAbstract;
import generic.Heuristique;
import generic.Problem;
import generic.SolutionPartielle;

public class AlgorithmeAEtoile extends AlgorithmeAbstract {

	Heuristique heuristique;

	public AlgorithmeAEtoile(Problem p, Heuristique h) {
		super(p);
		this.heuristique = h;
	}

	@Override
	public SolutionPartielle construireMeilleur() {

		int i = 0;
		
		SolutionPartielle solutionEnCours = this.problemeAResoudre
				.solutionInitiale();
		PriorityQueue<ValeurAEtoile> atraiter = new PriorityQueue<ValeurAEtoile>();

		SolutionPartielle meilleureTrouvee = null;

		// initialisation
		{
			ValeurAEtoile valEtoile;
			double valeur = this.problemeAResoudre.evaluer(solutionEnCours);
			double heuristique = this.heuristique.estimer(solutionEnCours);
			valEtoile = new ValeurAEtoile(solutionEnCours, valeur, heuristique);
			atraiter.add(valEtoile);
		}
		
		// Stocke la taille de la liste à traiter
		// Permet de savoir si la liste a changé ou pas
		// pour arreter la boucle
		int cpt = 0;
		
		while (atraiter.size() != cpt) {
			meilleureTrouvee = atraiter.poll().solutionStockee;
			
			// On stocke la taille de la liste avant parcourt des voisins
			// Pour savoir si la liste ouverte a été modifée
			cpt = atraiter.size();
			
			for (SolutionPartielle solution : meilleureTrouvee.solutionsVoisines()) {
				ValeurAEtoile valEtoile;
				double valeur = this.problemeAResoudre.evaluer(solution);
				double heuristique = this.heuristique.estimer(solution);
				valEtoile = new ValeurAEtoile(solution, valeur, heuristique);
				atraiter.add(valEtoile);
			}
			
			i++;

			if (i % 10000 == 0)
				// afficher le nb iterations tous les 1000 pas de temps
				System.out.println("nombre iteration:" + i);
		}
		
		System.out.println("nombre iteration:" + i);

		return meilleureTrouvee;
	}

}
