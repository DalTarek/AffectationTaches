package main;

import generic.AlgorithmeAbstract;
import generic.SolutionPartielle;
import probleme.affectationTaches.ProblemeAffectationTaches;
import probleme.affectationTaches.Tableau;
import algorithmes.AlgorithmeParcoursLargeurFiltre;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test_LargeurFiltre {
	public static void main(String[] args) {
		ProblemeAffectationTaches probleme = null;

		if (args.length == 1 && args[0].equals("1")) {
			System.out.println("Largeur Filtre problème simple");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeSimple());
		}
		
		if (args.length == 1 && args[0].equals("2")) {
			System.out.println("Largeur Filtre problème moins simple");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeMoinsSimple());
		}

		if (args.length == 1 && args[0].equals("3")) {
			System.out.println("Largeur Filtre problème complexe");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeComplexe());
		}

		if (args.length == 1 && args[0].equals("4")) {
			System.out.println("Largeur Filtre problème aleatoire");
			// on cr�e le probl�me
			probleme = new ProblemeAffectationTaches(Tableau.initialiseProblemeAleatoire());
		}

		if (args.length == 1) {
			// on cr�e l'algorithme
			AlgorithmeAbstract largeurFiltre = new AlgorithmeParcoursLargeurFiltre(probleme);

			FileWriter f = null;
			try {
				f = new FileWriter(new File("resultats_LargeurFiltre.dat"));
				f.append("# valeur iterations\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			SolutionPartielle resultat = largeurFiltre.construireMeilleur(f);
			System.out.println(resultat);

			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			System.out.println("Argument nécessaire pour choisir la difficulté du problème");
		}
	}
}
