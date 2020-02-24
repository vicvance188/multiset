package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

	private List<T> multiset;
	private Comparator<T> comp;

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
	@Override
	public boolean add(Object e, int count) {
		T item = (T) e;
		for (int i = 0; i < count; i++)
			multiset.add(item);
		return true;
	}

	/**
	 * ajoute une fois 'e' au multiset
	 * 
	 * @return le multiset a été modifié par l'opération
	 */
	@Override
	public boolean add(Object e) {
		T item = (T) e;
		multiset.add(item);
		return true;
	}

	/**
	 * retire 'e' du multiset
	 * 
	 * @return si le multiset a été modifié par l'opération
	 */
	@Override
	public boolean remove(Object e) {
		T item = (T) e;
		if (!multiset.contains(item)) {
			return false;
		} else {
			for (int index = 0; index < multiset.size(); index++) {
				if (multiset.get(index) == item) {
					multiset.remove(index);
					return true;
				}
			}
		}
		return true;
	}

	/**
	 * retire 'count' fois 'e' du multiset
	 * 
	 * @return si le multiset a été modifié par l'opération
	 */
	@Override
	public boolean remove(Object e, int count) {
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

	@Override
	public int count(Object o) {
		T item = (T) o;
		int count = 0;
		for (T elt : multiset) {
			if (elt.equals(item))
				count++;
		}
		return count;
	}

	@Override
	public void clear() {
		multiset.clear();

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return multiset.size();
	}

	@Override
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

	public Comparator<T> getComp() {
		return comp;
	}

	// #######################################
	// # ITERATEUR
	// #######################################
	/**
	 * Classe interne à NaiveMultiSet présentant son itérateur
	 *
	 */
	public class HashMultiSetIterator<T> implements Iterator<T> {
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
		@Override
		public boolean hasNext() {
			return hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next() renvoie l'element suivant
		 */
		@Override
		public T next() {
			return next();
		}
	}
}
