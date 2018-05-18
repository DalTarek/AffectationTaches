package probleme.affectationTaches;

import generic.Heuristique;
import generic.SolutionPartielle;

public class Heuristique_MinTempsTotal extends Heuristique {

	@Override
	public double estimer(SolutionPartielle solutionAnalysee) {
		SolutionAffectationTaches sol = (SolutionAffectationTaches)solutionAnalysee;
		
		SolutionAffectationTaches s = new SolutionAffectationTaches(sol);
		
		if (s.estComplete())
			return 0;
		
		s.profondeurTraitee++;
		for (int i = s.profondeurTraitee; i < s.problemeATraiter.nbTaches; i++) {
			int personne = 0;
			for (int j = 0; j < s.problemeATraiter.nbPersonnes; j++) {
				//double min = Double.MAX_VALUE;
				double max = 0;
				if (s.problemeATraiter.tempsPourTaches.tableauTemps[j][i] >= max) {
					max = s.problemeATraiter.tempsPourTaches.tableauTemps[j][i];
					personne = j;
				}
			}
			s.affecter(personne);
			s.profondeurTraitee++;
		}
			
		return s.tempsTotal - sol.tempsTotal;
	}

}
