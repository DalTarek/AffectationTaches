package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeParcoursLargeurFiltre;

public class Test_LargeurFiltre {
	public static void main(String[] args) {
		System.out.println("Largeur Filtre problème moins simple");
		// on cr�e le probl�me
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		
		// on cr�e l'algorithme
		AlgorithmeAbstract largeurFiltre = new AlgorithmeParcoursLargeurFiltre(probleme);
		
		SolutionPartielle resultat = largeurFiltre.construireMeilleur();
		System.out.println(resultat);
	}
}
