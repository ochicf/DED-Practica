package uoc.ded.practica;

import java.util.Comparator;

import uoc.ei.tads.LlistaEncadenada;
import uoc.ei.tads.Posicio;
import uoc.ei.tads.Recorregut;


/**
 * Classe que implementa una Llista encadenada ordenada. 
 * s'implementa un mètode per afegir elements amb el 
 * criteri d'ordenació definit (comparador)
 * @param <E>
 */
public class LlistaEncadenadaOrdenada<E> extends LlistaEncadenada<E> {
	
	Comparator<E> cmp;
	
	public LlistaEncadenadaOrdenada(Comparator<E> cmp) {
		super();
		this.cmp= cmp;
	}
	
	public void add(E e) {
		
		if (super.estaBuit()) super.afegirAlFinal(e);
		else {
			Recorregut<E> recorregut = super.posicions();
			Posicio<E> p = null;
			boolean found=false;
			while (recorregut.hiHaSeguent() && (!found) ) {
				p = recorregut.seguent();
				found = this.cmp.compare(p.getElem(), e)<=0;
				
			} 
							
			
			if (!found) {
				super.afegirAlFinal(e);
			}
			else {
				super.afegirAbansDe(p,  e);
			}
			
		}		
	}

}
