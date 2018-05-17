package probleme.affectationTaches;

import generic.ProblemeAbstract;
import generic.SolutionAbstract;

import probleme.affectationTaches.SolutionAffectationTaches;

/**
 * Classe permettant d'implementer le probleme d'affectation de taches
 */

public class ProblemeAffectationTaches implements ProblemeAbstract {

    /**
     * Nombre de taches a effectuer
     */
    int nbTaches;
    
    /**
     * Nombre de personnes pour effectuer les taches
     */
    int nbPersonnes;

    /**
     * Tableau des temps mis par chaque personne pour effectuer chaque tache
     */
    Tableau tempsPourTaches;

    /**
     * @param p : nombre de personnes
     * @param t : nombre de taches
     * @param t : temps mis par chaque personne pour chaque taches
     */
    public ProblemeAffectationTaches(int p, int t, Tableau tab) {
        nbTaches = t;
        nbPersonnes = p;
        tempsPourTaches = tab;
    }

    /**
     * Permet d'evaluer une solution
     */
    @Override
    public double evaluation(SolutionAbstract s) {
        // teste que la solution est du bon type
		if (!(s instanceof SolutionAffectationTaches)) {
			throw new IllegalArgumentException("mauvais types");
        }
        
        // On est sur que le type de s est bon, on peut caster
        return ((SolutionAffectationTaches) s).tempsTotal;
    }
}