package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.Heuristique_MinTempsTotal;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeBranchAndBound;

public class Test_BranchAndBound {
	
	public static void main(String[] args) {
		System.out.println("Branche and Bound problème moins simple");
		// on cr�e le probl�me
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());

		Heuristique_MinTempsTotal h = new Heuristique_MinTempsTotal();
		// on cr�e l'algorithme
		AlgorithmeAbstract branch = new AlgorithmeBranchAndBound(probleme, h);

		SolutionPartielle resultat = branch.construireMeilleur();
		System.out.println(resultat);
	}
}
