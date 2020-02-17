package pobj.multiset;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashMultiSet<T> implements MultiSet<T>{
  
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
    if(!multiset.containsKey(key)) {
      multiset.put(key, multiset.get(key));
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
}
