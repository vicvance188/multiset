package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{

  private Map<T, Integer> multiset;
  int taille;
  /**
   * constructeur sans argument
   * construit un multiset vide
   */
  public HashMultiSet() {
    this.multiset = new HashMap<T, Integer>();
    this.taille = 0;
  }
  /**
   * constructeur de copie
   * initialise un multiset avec le contenu de la collection
   * @param <T>
   *
   */
  public HashMultiSet(Collection<T> collection) {
    this.multiset = new HashMap<T, Integer>();
    this.taille = 0;
    for (T e : collection) {
      add(e);
    }
  }


  /**
   * ajoute 'count' fois 'e' au multiset
   * @return le multiset a été modifié par l'opération
   */
  public boolean add( T e, int count) {
    if(!multiset.containsKey(e)) {    // verifie si l'élement n'existe pas dans le multiset
      multiset.put(e, count);         // ajoute count fois l'element au multiset
    }else {                             // si l'element existe deja
      multiset.put(e, multiset.get(e) + count); //ajoute count fois de plus l'element
    }
    this.taille += count;               // taille du multiset augmenter de count
    return true;
  }

  /**
   * ajoute une fois 'e' au multiset
   * @return le multiset a été modifié par l'opération
   */
  public boolean add(T e) {      // cf  méthode add précédente, mais avec count = 1
   return add(e, 1);
  }

  /**
   * retire 'count' fois 'e' du multiset
   * @return si le multiset a été modifié par l'opération
   */
  public boolean remove(Object e, int count) {
    @SuppressWarnings("unchecked")
    T key = (T) e;
    int temp = multiset.get(key);
    if(multiset.containsKey(key)) {     // verifie que 'e' est present dans le multiset
      if(temp == count) {  // si 'e' present 'count' fois
        multiset.remove(key);           // 'e' est retiré du multiset
        taille-=count;
        return true;                    // confirmation
      }
      if(temp > count) {   // si 'e' est present plus que 'count' fois
        multiset.put(key, temp - count); // on retire 'count' fois 'e'
        taille-= count;
        return true;
      }
      if(temp < count) {   // si 'e' n'est pas disponible en suffisament d'exemplaire
        multiset.remove(e);             // on retire 'e' du multiset
        taille -= temp;
        return true;
      }
    }
    return false;
  }

  /**
   * retire 'e' du multiset
   * @return si le multiset a été modifié par l'opération
   */
  public boolean remove(Object e) {
    return remove(e, 1);
  }

  public int count(T o) {
    return multiset.get(o);
  }

  public void clear() {
    multiset.clear();

  }

  public int size() {
    return taille;
  }

  public Iterator<T> iterator() {
    return new HashMultiSetIterator(multiset);
  }

  public List<T> elements(){
    List<T> list = new ArrayList<T>();
    for(T t : multiset.keySet()) {
      list.add(t);
    }
    return list;
  }


  //#######################################
  //# ITERATEUR
  //#######################################
  /**
   * Classe interne à HashMultiSet présentant son itérateur
   *
   */
  public class HashMultiSetIterator implements Iterator<T> {
    private Iterator<Entry<T, Integer>> iterateur;  // Map.Entry correspond à la valeur et la clé d'un element de notre
                                                    // ensemble
    // ici l'iterateur itere sur les valeurs et clé d'un map
    int               current = 0;    /** nombre d'itération (nombre d'occurence de l'element) */
    T                 elt;            /** elt qui sera retourné par next*/
    Entry<T, Integer> entree;         /** element iterant de l'iterateur*/

    /**
     * constructeur de notre iterateur
     * @param multiset notre multiset
     */
    public HashMultiSetIterator(Map<T, Integer> multiset) {
      iterateur = multiset.entrySet().iterator(); // creation de l'iterateur sur les entrées du multiset
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     * verifie s'il y a un element qui n'a pas encore ete parcouru
     */
    public boolean hasNext() {
      if (current == 0)
        return iterateur.hasNext();
      return true;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     * renvoie l'element suivant
     */
    public T next() {
      if (current == 0) {             // verifie qu'on a bien itéré 'count' fois 'e'
        if (iterateur.hasNext()) {    // verifie qu'il reste des elements non exploré
          entree = iterateur.next();  // prends le prochain element
          elt = entree.getKey();      // recupere la clé du l'element actuel
          current = entree.getValue();// recupere 'count' de 'e' pour le nombre d'itération
        } else
          throw new NoSuchElementException();
      }
      current--;                    // decremente le nombre de fois ou elt sera renvoyé
      return elt;                   // elt renvoyé par next
    }
  }
}
