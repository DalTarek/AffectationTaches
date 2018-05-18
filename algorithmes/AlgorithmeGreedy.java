package algorithmes;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;

public class AlgorithmeGreedy extends AlgorithmeAbstract {

	public AlgorithmeGreedy(Problem p) {
		super(p);
	}

	@Override
	public SolutionPartielle construireMeilleur() {

		SolutionPartielle enCours = this.problemeAResoudre.solutionInitiale();
		
		SolutionPartielle meilleureSolution = enCours;
		
		while (!enCours.estComplete()) {
			// On stocke le meilleur choix courant pour le comparer aux autres dans la boucle
			double meilleurChoix = 0;
			double evaluationCourante = 0;
			
			// On parcourt toutes les solutions voisines et on retourne la meilleure
			for (SolutionPartielle solution : enCours.solutionsVoisines()) {
				evaluationCourante = problemeAResoudre.evaluer(solution);
				if (evaluationCourante > meilleurChoix) {
					meilleurChoix = evaluationCourante;
					meilleureSolution = solution;
				}
			}
			
			this.compteur++;
			
			enCours = meilleureSolution;
		}
		
		return enCours;
	}
	
}
