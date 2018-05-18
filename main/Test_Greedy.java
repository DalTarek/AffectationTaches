package main;

import algo.Greedy;
import generic.AlgorithmeAbstract;
import generic.ProblemeAbstract;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.SolutionAffectationTaches;
import probleme.affectationTaches.Tableau;

public class Test_Greedy {
	
	public static void main(String[] args) {
		// on crée le problème
		ProblemeAffectationTaches probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		
		SolutionAffectationTaches s = new SolutionAffectationTaches(probleme);
		
		// on crée l'algorithme
		AlgorithmeAbstract greedy = new Greedy(probleme, s);
		
		boolean fin = false;
		while (!fin) {
			fin = greedy.ameliorerSolution();
			System.out.println(greedy.log());
		}
	}

}
