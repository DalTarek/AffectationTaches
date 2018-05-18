package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeParcoursProfondeur;

public class Test_Profondeur {
	public static void main(String[] args) {
		System.out.println("Profondeur problème moins simple");
		// on cr�e le probl�me
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		
		// on cr�e l'algorithme
		AlgorithmeAbstract profondeur = new AlgorithmeParcoursProfondeur(probleme);
		
		SolutionPartielle resultat = profondeur.construireMeilleur();
		System.out.println(resultat);
	}
}
