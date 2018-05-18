package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeParcoursLargeur;

public class Test_Largeur {
	public static void main(String[] args) {
		System.out.println("Largeur problème moins simple");
		// on cr�e le probl�me
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		
		// on cr�e l'algorithme
		AlgorithmeAbstract largeur = new AlgorithmeParcoursLargeur(probleme);
		
		SolutionPartielle resultat = largeur.construireMeilleur();
		System.out.println(resultat);
	}
}
