package algorithmes;

import java.io.FileWriter;
import java.io.IOException;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;

public class AlgorithmeGreedy extends AlgorithmeAbstract {

	public AlgorithmeGreedy(Problem p) {
		super(p);
	}

	@Override
	public SolutionPartielle construireMeilleur(FileWriter f) {

		SolutionPartielle enCours = this.problemeAResoudre.solutionInitiale();
		
		SolutionPartielle meilleureSolution = enCours;
		
		while (!enCours.estComplete()) {
			// On stocke le meilleur choix courant pour le comparer aux autres dans la boucle
			double meilleurChoix = Double.MAX_VALUE;
			double evaluationCourante = 0;
			
			// On parcourt toutes les solutions voisines et on retourne la meilleure
			for (SolutionPartielle solution : enCours.solutionsVoisines()) {
				evaluationCourante = problemeAResoudre.evaluer(solution);
				if (evaluationCourante < meilleurChoix) {
					meilleurChoix = evaluationCourante;
					meilleureSolution = solution;
				}
			}
			
			this.compteur++;
			
			enCours = meilleureSolution;

			if (this.compteur % 1000 == 0) {
				// afficher le nb iterations tous les 1000 pas de temps
				// System.out.println("nombre iteration:" + this.compteur);
			}

			try {
				f.append("" + compteur + " " 
					+ this.problemeAResoudre.evaluer(enCours) + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Nombre d'itérations : " + this.compteur);
		
		return enCours;
	}
	
}
