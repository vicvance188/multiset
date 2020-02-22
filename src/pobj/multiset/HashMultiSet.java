package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
  
  private Map<T, Integer> multiset;
  int taille;
  private Comparator <T> comp;
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
    for(T key:collection) {
      add(key);
    }
  }
  
  
  /**
   * ajoute 'count' fois 'e' au multiset
   * @return le multiset a été modifié par l'opération
   */
  @Override
  public boolean add(Object e, int count) {
    T key = (T) e;                      // cast 'e' comme key de notre multiset
    if(!multiset.containsKey(key)) {    // verifie si l'élement n'existe pas dans le multiset
      multiset.put(key, count);         // ajoute count fois l'element au multiset
    }else {                             // si l'element existe deja
      multiset.put(key, multiset.get(key)+count); //ajoute count fois de plus l'element
    }
    this.taille += count;               // taille du multiset augmenter de count
    return true;
  }

  /**
   * ajoute une fois 'e' au multiset
   * @return le multiset a été modifié par l'opération
   */
  @Override
  public boolean add(Object e) {      // cf  méthode add précédente, mais avec count = 1
    T key = (T) e;
    if(!multiset.containsKey(key)){
      multiset.put(key, 1);
    }else {
      multiset.put(key, multiset.get(key)+1);
    }
    this.taille ++;
    return true;
  }

  /**
   * retire 'e' du multiset
   * @return si le multiset a été modifié par l'opération
   */
  @Override
  public boolean remove(Object e) {
    T key = (T) e;
    if(multiset.containsKey(key)) {     // verifie que l'element existe dans le mutliset
      if(multiset.get(key) == 1) {      // s'il n'est qu'en un exemplaire
        multiset.remove(key);           // on le retire du multiset
        taille--;                       // reduit la taille du multiset
        return true;                    // confirmation
      }
      else {
        multiset.put(key, multiset.get(key)-1);   // diminue le nombre d'element key de 1
        taille --;
        return true;
      }
    }
    else {                              // si le multiset ne contient pas notre élément
      return false;                     // l'element n'a pas pu etre retiré
    }
  }

  
  /**
   * retire 'count' fois 'e' du multiset
   * @return si le multiset a été modifié par l'opération
   */
  @Override
  public boolean remove(Object e, int count) {
    T key = (T) e;
    if(multiset.containsKey(key)) {     // verifie que 'e' est present dans le multiset
      if(multiset.get(key) == count) {  // si 'e' present 'count' fois
        multiset.remove(key);           // 'e' est retiré du multiset
        taille-=count;
        return true;                    // confirmation
      }
      if(multiset.get(key) > count) {   // si 'e' est present plus que 'count' fois
        multiset.put(key, multiset.get(key) - count); // on retire 'count' fois 'e'
        taille-= count;
        return true;
      }
      if(multiset.get(key) < count) {   // si 'e' n'est pas disponible en suffisament d'exemplaire
        int temp = multiset.get(key);   // création d'une variable temporaire pour reduire la taille du multiset
        multiset.remove(e);             // on retire 'e' du multiset
        taille -= temp;
        return true;
      }
    }
    return false;    
  }

  @Override
  public int count(Object o) {
    T key = (T) o;
    return multiset.get(key);
  }

  @Override
  public void clear() {
    multiset.clear();
    
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return taille;
  }
  
  @Override
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
  
  public Comparator<T> getComp(){
	  return comp;
  }
  
  


  
  //#######################################
  //# ITERATEUR
  //#######################################
  /**
   * Classe interne à HashMultiSet présentant son itérateur
   *
   */
  public class HashMultiSetIterator<T> implements Iterator<T> {
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
    @Override
    public boolean hasNext() {
      if (current == 0)
        return iterateur.hasNext();
      return true;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     * renvoie l'element suivant
     */
    @Override
    public T next() {
      if (current == 0) {             // verifie qu'on a bien itéré 'count' fois 'e'
        if (iterateur.hasNext()) {    // verifie qu'il reste des elements non exploré
          entree = iterateur.next();  // prends le prochain element
          elt = entree.getKey();      // recupere la clé du l'element actuel
          current = entree.getValue();// recupere 'count' de 'e' pour le nombre d'itération
        } else {
          //throw new pasDelementException();
        }
      }

      current--;                    // decremente le nombre de fois ou elt sera renvoyé
      return elt;                   // elt renvoyé par next
    }
  }
}
