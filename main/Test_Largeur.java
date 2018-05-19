package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeParcoursLargeur;

public class Test_Largeur {
	public static void main(String[] args) {
		ProblemeAffectationTaches probleme = null;

		if (args.length == 1 && args[0].equals("1")) {
			System.out.println("Largeur problème simple");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeSimple());
		}

		if (args.length == 1 && args[0].equals("2")) {
			System.out.println("Largeur problème moins simple");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		}

		if (args.length == 1 && args[0].equals("3")) {
			System.out.println("Largeur problème complexe");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeComplexe());
		}

		if (args.length == 1 && args[0].equals("4")) {
			System.out.println("Largeur problème aleatoire");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeAleatoire());
		}
		
		if (args.length == 1) {
			// on cr�e l'algorithme
			AlgorithmeAbstract largeur = new AlgorithmeParcoursLargeur(probleme);
			
			SolutionPartielle resultat = largeur.construireMeilleur();
			System.out.println(resultat);
		} else {
			System.out.println("Argument nécessaire pour choisir la difficulté du problème");
		}
	}
}
