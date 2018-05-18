package probleme.affectationTaches;

import generic.Problem;
import generic.SolutionPartielle;

import probleme.affectationTaches.SolutionAffectationTaches;

/**
 * Classe permettant d'implementer le probleme d'affectation de taches
 */

public class ProblemeAffectationTaches extends Problem {

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
    public ProblemeAffectationTaches(Tableau tab) {
        tempsPourTaches = tab;
        nbTaches = tab.tableauTemps[0].length;
        nbPersonnes = tab.tableauTemps.length;
    }

    public SolutionPartielle solutionInitiale() {
        return new SolutionAffectationTaches(this);
    }

    /**
     * Permet d'evaluer une solution
     */
    @Override
    public double evaluer(SolutionPartielle s) {
        // teste que la solution est du bon type
		if (!(s instanceof SolutionAffectationTaches)) {
			throw new IllegalArgumentException("mauvais types");
        }
        
        // On est sur que le type de s est bon, on peut caster
        return ((SolutionAffectationTaches) s).tempsTotal;
    }
}