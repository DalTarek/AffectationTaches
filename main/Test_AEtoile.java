package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.Heuristique_MinTempsTotal;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeAEtoile;

public class Test_AEtoile {

	public static void main(String[] args) {
		ProblemeAffectationTaches probleme = null;

		if (args.length == 1 && args[0].equals("1")) {
			System.out.println("Greedy problème simple");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeSimple());
		}

		if (args.length == 1 && args[0].equals("2")) {
			System.out.println("Greedy problème moins simple");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		}

		if (args.length == 1 && args[0].equals("3")) {
			System.out.println("Greedy problème complexe");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeComplexe());
		}

		if (args.length == 1 && args[0].equals("4")) {
			System.out.println("Greedy problème aleatoire");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeAleatoire());
		}

		if (args.length == 1) {
			Heuristique_MinTempsTotal h = new Heuristique_MinTempsTotal();
				
			// on cr�e l'algorithme
			AlgorithmeAbstract aEtoile = new AlgorithmeAEtoile(probleme, h);
			
			SolutionPartielle resultat = aEtoile.construireMeilleur();
			System.out.println(resultat);
		} else {
			System.out.println("Argument nécessaire pour choisir la difficulté du problème");
		}
	}
}
