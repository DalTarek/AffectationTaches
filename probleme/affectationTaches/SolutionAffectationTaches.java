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
     * @param n : nombre de taches a affecter
     */
    public SolutionAffectationTaches(int n) {
        affectations = new int[n];
        for (int i = 0; i < n; i++) {
            affectations[i] = -1;
        }

        tempsTotal = 0;
        profondeurTraitee = 0;
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
    public List<SolutionAbstract> retourneVoisinage() {
        if (this.estComplete()) {
            return new ArrayList<>();
        }

        ArrayList<SolutionAbstract> solutions = new ArrayList<>();

        for (int i = 0; i < problemeATraiter.nbPersonnes; i++) {
            SolutionAffectationTaches solutionPersonneI = new SolutionAffectationTaches(this);
            solutionPersonneI.profondeurTraitee++;
            solutionPersonneI.affecter(i);
            solutions.add(solutionPersonneI);
        }

        return solutions;
    }

    public void affecter(int i) {
        affectations[profondeurTraitee] = i;

        int sommeTempsPersonneI = 0;
        for (int j = 0; j < affectations.length; j++) {
            if (affectations[j] == i) {
                sommeTempsPersonneI += problemeATraiter.tempsPourTaches.tableauTemps[i][j];
            }
        }

        sommeTempsPersonneI += problemeATraiter.tempsPourTaches.tableauTemps[i][profondeurTraitee];

        if (sommeTempsPersonneI > tempsTotal) {
            tempsTotal = sommeTempsPersonneI;
        }
    }

    /**
     * Permet de savoir si la solution est complete
     * c'est a dire que toutes les taches sont affectees
     */
    public boolean estComplete() {
        return profondeurTraitee == problemeATraiter.nbPersonnes;
    }

}