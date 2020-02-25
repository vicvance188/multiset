package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

	private List<T> multiset;

	/**
	 * constructeur sans argument construit un multiset vide
	 */
	public NaiveMultiSet() {

		this.multiset = new ArrayList<T>();
	}

	/**
	 * constructeur de copie initialise un multiset avec le contenu de la collection
	 *
	 * @param <T>
	 *
	 */
	public NaiveMultiSet(Collection<T> collection) {

		this.multiset = new ArrayList<T>();
		for (T item : collection) {
			add(item);
		}
	}

	/**
	 * ajoute 'count' fois 'e' au multiset
	 *
	 * @return le multiset a été modifié par l'opération
	 */
	public boolean add(T e, int count) {
		for (int i = 0; i < count; i++)
			multiset.add(e);
		return true;
	}

	/**
	 * ajoute une fois 'e' au multiset
	 *
	 * @return le multiset a été modifié par l'opération
	 */
	public boolean add(T e) {
		return add(e, 1);
	}

	/**
	 * retire 'count' fois 'e' du multiset
	 *
	 * @return si le multiset a été modifié par l'opération
	 */
	public boolean remove(Object e, int count) {
		@SuppressWarnings("unchecked")
		T item = (T) e;
		boolean bool = false;
		for (int index = 0; index < multiset.size(); index++) {
			if (multiset.remove(item)) {
				bool = true;
				continue;
			} else {
				if (!bool) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * retire 'e' du multiset
	 *
	 * @return si le multiset a été modifié par l'opération
	 */
	public boolean remove(Object e) {
		return remove(e, 1);
	}

	public int count(T o) {
		int count = 0;
		for (T elt : multiset) {
			if (elt.equals(o))
				count++;
		}
		return count;
	}

	public void clear() {
		multiset.clear();

	}

	public int size() {
		return multiset.size();
	}

	public Iterator<T> iterator() {
		return new HashMultiSetIterator(multiset);
	}

	public List<T> elements() {
		List<T> list = new ArrayList<T>();
		for (T item : multiset) {
			if (!list.contains(item))
				list.add(item);
		}
		return list;
	}


	// #######################################
	// # ITERATEUR
	// #######################################
	/**
	 * Classe interne à NaiveMultiSet présentant son itérateur
	 *
	 */
	public class HashMultiSetIterator implements Iterator<T> {
		private Iterator<T> iterateur; // Map.Entry correspond à la valeur et la clé d'un element de
										// notre
										// ensemble
		// ici l'iterateur itere sur les valeurs et clé d'un map
		int current = 0;
		/** nombre d'itération (nombre d'occurence de l'element) */
		T elt;
		/** elt qui sera retourné par next */
		T entree;

		/** element iterant de l'iterateur */

		/**
		 * constructeur de notre iterateur
		 *
		 * @param multiset notre multiset
		 */
		public HashMultiSetIterator(List<T> multiset) {
			iterateur = multiset.iterator(); // creation de l'iterateur sur les entrées du multiset
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.util.Iterator#hasNext() verifie s'il y a un element qui n'a pas
		 * encore ete parcouru
		 */
		public boolean hasNext() {
			return hasNext();
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.util.Iterator#next() renvoie l'element suivant
		 */
		public T next() {
			return next();
		}
	}
}
