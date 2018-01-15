package uoc.ded.practica;

import uoc.ei.tads.CuaAmbPrioritat;
import uoc.ei.tads.Posicio;
import uoc.ei.tads.Rang;
import uoc.ei.tads.Recorregut;
import uoc.ei.tads.Utilitats;

/**
 * Simplified version of an upgradeable priority queue based in the referenced
 * example from the TADs library.
 *
 * @see uoc.ei.exercicis.modul9.exercici1.CuaAmbPrioritatActualitzable
 */
public class CuaAmbPrioritatActualitzable<E> extends CuaAmbPrioritat<E> {

	/** Atribut que en Java 1.5 determina la compatibilitat entre objectes
	 * serialitzables de la mateixa classe. L'deitificador es calcula
	 * mitjan�ant un m�tode de la classe Utilitats. */
	private static final long serialVersionUID = Utilitats.getSerialVersionUID();

	public CuaAmbPrioritatActualitzable() {
		super();
	}

	public CuaAmbPrioritatActualitzable(int max) {
		super(max);
	}

	public CuaAmbPrioritatActualitzable(java.util.Comparator<E> comparador) {
		super(comparador);
	}

	public CuaAmbPrioritatActualitzable(int max, java.util.Comparator<E> comparador) {
		super(max,comparador);
	}
	
	/**
	 * Adds or updates element in the queue.
	 * 
	 * I have overridden this method so it keeps being the main entry point
	 * for this TAD. I've made this decision based on:
	 * 	- maintains same interface than {@link CuaAmbPrioritat}
	 * 	- simplifies consumer logic, since there is no need to worry element
	 *    is already in the queue
	 * 
	 * The drawback of this decision is that the cost of this method increases
	 * linearly with the number of elements in the queue, since a linear
	 * lookup is performed.
	 * 
	 * @param element element to add or update
	 */
	public void encuar(E elem) {
		Posicio<E> posicio = this.posicioElement(elem);
		if (posicio == null) {
			super.encuar(elem);
		} else {
			this.actualitzarPosicio(posicio);
		}
	}
	
	/**
	 * Updates queue with the given position.
	 * @param posicio
	 */
	private void actualitzarPosicio(Posicio<E> posicio) {
		this.enfonsarElement(posicio);
		this.pujarElement(posicio);
	}
	
	/**
	 * Looks for element in the queue and returns its position.
	 * 
	 * @param elem element to look for
	 * @return position of the element in the queue, null if not found
	 */
	private Posicio<E> posicioElement(E elem) {
		// define a stub position so this.comparar can be used
		// to perform element comparison
		Posicio<E> stubPosicio = new Rang<E>(-1, elem);
		Recorregut<E> recorregut = this.posicions();
		while (recorregut.hiHaSeguent()) {
			Posicio<E> posicio = recorregut.seguent();
			if (this.comparar(posicio, stubPosicio) == 0) {
				return posicio;
			}
		}
		return null;
	}
	
}
