package probleme.affectationTaches;

import generic.Heuristique;
import generic.SolutionPartielle;

/**
 * heuristique simple qui consiste à affecter à chaque tache non affectee
 * la personne qui minimise le temps pour cette tache
 */

public class Heuristique_MinTempsTotal extends Heuristique {

	@Override
	public double estimer(SolutionPartielle solutionAnalysee) {
		SolutionAffectationTaches sol = (SolutionAffectationTaches)solutionAnalysee;
		
		SolutionAffectationTaches s = new SolutionAffectationTaches(sol);
		
		if (s.estComplete())
			return 0;
		
		s.profondeurTraitee++;

		// On complete les taches non affectee
		for (int i = s.profondeurTraitee; i < s.problemeATraiter.nbTaches; i++) {
			int personne = 0;
			double min = Double.MAX_VALUE;
			// On cherche la personne qui met le moins de temps pour cette tache
			for (int j = 0; j < s.problemeATraiter.nbPersonnes; j++) {
				if (s.problemeATraiter.tempsPourTaches.tableauTemps[j][i] < min) {
					min = s.problemeATraiter.tempsPourTaches.tableauTemps[j][i];
					personne = j;
				}
			}
			s.affecter(personne);
			s.profondeurTraitee++;
		}
			
		return s.tempsTotal - sol.tempsTotal;
	}

}
