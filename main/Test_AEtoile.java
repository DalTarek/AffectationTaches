package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.Heuristique_MinTempsTotal;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeAEtoile;

public class Test_AEtoile {

	public static void main(String[] args) {
		System.out.println("Greedy problème moins simple");
		// on cr�e le probl�me
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		
		Heuristique_MinTempsTotal h = new Heuristique_MinTempsTotal();
		
		// on cr�e l'algorithme
		AlgorithmeAbstract aEtoile = new AlgorithmeAEtoile(probleme, h);
		
		SolutionPartielle resultat = aEtoile.construireMeilleur();
		System.out.println(resultat);
	}
}
