package probleme.affectationTaches;

import java.util.ArrayList;
import java.util.List;

import generic.SolutionPartielle;

public class SolutionAffectationTaches extends SolutionPartielle {

    /**
     * Probleme a traiter
     */
    ProblemeAffectationTaches problemeATraiter;

    /**
     * Stocke à chaque case le numéro de la personne qui effectue la tache
     * -1 si aucune personne affectee
     */
    int[] affectations;

    /**
     * Temps total actuel pour effectuer les taches
     */
    int tempsTotal;

    /**
     * tache traitee
     */
    int profondeurTraitee;

    /**
     * 
     * @param p probleme
     */
    public SolutionAffectationTaches(ProblemeAffectationTaches p) {
    	int nbTaches = p.nbTaches;
    	affectations = new int[nbTaches];
        for (int i = 0; i < nbTaches; i++) {
            affectations[i] = -1;
        }

        tempsTotal = 0;
        profondeurTraitee = -1;
        problemeATraiter = p;
    }

    /**
     * Constructeur de copie
     * @param s : solution à copier
     */
    public SolutionAffectationTaches(SolutionAffectationTaches s) {
        this.problemeATraiter = s.problemeATraiter;
        this.affectations = s.affectations.clone();
        this.profondeurTraitee = s.profondeurTraitee;
        this.tempsTotal = s.tempsTotal;
    }

    /**
     * retourne le voisinage de la solution
     */
    public SolutionPartielle[] solutionsVoisines() {
        if (this.estComplete()) {
            return new SolutionPartielle[0];
        }

        SolutionPartielle[] solutions = new SolutionPartielle[problemeATraiter.nbPersonnes];
        for (int i = 0; i < problemeATraiter.nbPersonnes; i++) {
            SolutionAffectationTaches solutionPersonneI = new SolutionAffectationTaches(this);        
            solutionPersonneI.profondeurTraitee++;
            solutionPersonneI.affecter(i);
            solutions[i] = solutionPersonneI;
        }

        return solutions;
    }

    public boolean invalide() {
        return false;
    }

    private void affecter(int i) {
        affectations[profondeurTraitee] = i;

        // on calcule la somme de la dur�e des t�ches effectu�es par la personne i
        int sommeTempsPersonneI = 0;
        for (int j = 0; j < affectations.length; j++) {
            if (affectations[j] == i) {
                sommeTempsPersonneI += problemeATraiter.tempsPourTaches.tableauTemps[i][j];
            }
        }

        // Si le temps total de la personne i depasse le temps total deja enregistre
        // on met � jour le temps total
        if (sommeTempsPersonneI > tempsTotal) {
            tempsTotal = sommeTempsPersonneI;
        }
    }

    /**
     * Permet de savoir si la solution est complete
     * c'est a dire que toutes les taches sont affectees
     */
    public boolean estComplete() {
    	for (int i = 0; i < affectations.length; i++) {
    		if (affectations[i] == -1)
    			return false;
    	}
    	
    	return true;
    }
    
    /**
     * On affiche la liste des t�ches effectu�es par chaque personne
     */
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < problemeATraiter.nbPersonnes; i++) {
    		sb.append("Personne " + (i+1) + " : ");
    		for (int j = 0; j < affectations.length; j++) {
    			if (affectations[j] == i) {
    				sb.append((j+1) + " ");
    			}	
    		}
    		sb.append("\n");		
    	}
    	sb.append("Temps total = " + tempsTotal + "\n");
    	
    	return sb.toString();
    }

}