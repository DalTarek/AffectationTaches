package generic;

import java.io.File;
import java.io.FileWriter;

/**
 * algoritghme abstrait à partir duquel construire les algorithmes
 * 
 * 
 */
public abstract class AlgorithmeAbstract {

	/**
	 * le nombre de noeuds visités
	 */
	public long compteur;

	/**
	 * le probleme a traiter
	 */
	protected Problem problemeAResoudre;

	public AlgorithmeAbstract(Problem p) {
		this.problemeAResoudre = p;
	}

	/**
	 * construit la meilleure solution au probleme
	 * @return Solution optimale
	 */
	public abstract SolutionPartielle construireMeilleur(FileWriter f);

}
