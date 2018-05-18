package probleme.affectationTaches;

import java.util.ArrayList;
import java.util.List;

import generic.SolutionAbstract;

public class SolutionAffectationTaches extends SolutionAbstract {

    /**
     * Probleme a traiter
     */
    ProblemeAffectationTaches problemeATraiter;

    /**
     * Stocke √† chaque case le num√©ro de la personne qui effectue la tache
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
        profondeurTraitee = 0;
        problemeATraiter = p;
    }

    /**
     * Constructeur de copie
     * @param s : solution √† copier
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
    public List<SolutionAbstract> retourneVoisinage() {
        if (this.estComplete()) {
            return new ArrayList<>();
        }

        ArrayList<SolutionAbstract> solutions = new ArrayList<>();
        for (int i = 0; i < problemeATraiter.nbPersonnes; i++) {
            SolutionAffectationTaches solutionPersonneI = new SolutionAffectationTaches(this);        
            solutionPersonneI.affecter(i);
            solutionPersonneI.profondeurTraitee++;
            solutions.add(solutionPersonneI);
        }

        return solutions;
    }

    private void affecter(int i) {
        affectations[profondeurTraitee] = i;

        // on calcule la somme de la durÈe des t‚ches effectuÈes par la personne i
        int sommeTempsPersonneI = 0;
        for (int j = 0; j < affectations.length; j++) {
            if (affectations[j] == i) {
                sommeTempsPersonneI += problemeATraiter.tempsPourTaches.tableauTemps[i][j];
            }
        }

        // on met ‡ jour le temps total
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
     * On affiche la liste des t‚ches effectuÈes par chaque personne
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