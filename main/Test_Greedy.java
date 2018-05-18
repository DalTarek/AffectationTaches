package main;

import algorithmes.AlgorithmeGreedy;
import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.SolutionAffectationTaches;
import probleme.affectationTaches.Tableau;

public class Test_Greedy {
	
	public static void main(String[] args) {
		System.out.println("Greedy problème moins simple");
		// on cr�e le probl�me
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		
		// on cr�e l'algorithme
		AlgorithmeAbstract greedy = new AlgorithmeGreedy(probleme);
		
		SolutionPartielle resultat = greedy.construireMeilleur();
		System.out.println(resultat);
	}

}
