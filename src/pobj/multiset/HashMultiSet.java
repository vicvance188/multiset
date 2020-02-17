package pobj.multiset;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashMultiSet<T> implements MultiSet<T>{
  
  private HashMap<T, Integer> multiset;
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
    for(T elt:collection) {
      add(elt);
    }
    
  }
  
  
  /**
   * ajoute 'count' fois 'e' au multiset
   * @return le multiset a été modifié par l'opération
   */
  @Override
  public boolean add(Object e, int count) {
    T elt = (T) e;                      // cast 'e' comme elt de notre multiset
    if(!multiset.containsKey(elt)) {    // verifie si l'élement n'existe pas dans le multiset
      multiset.put(elt, count);         // ajoute count fois l'element au multiset
    }else {                             // si l'element existe deja
      multiset.put(elt, multiset.get(elt)+count); //ajoute count fois de plus l'element
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
    T elt = (T) e;
    if(!multiset.containsKey(elt)) {
      multiset.put(elt, multiset.get(elt));
    }else {
      multiset.put(elt, multiset.get(elt)+1);
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
    T elt = (T) e;
    if(multiset.containsKey(elt)) {     // verifie que l'element existe dans le mutliset
      if(multiset.get(elt) == 1) {      // s'il n'est qu'en un exemplaire
        multiset.remove(elt);           // on le retire du multiset
        taille--;                       // reduit la taille du multiset
        return true;                    // confirmation
      }
      else {
        multiset.put(elt, multiset.get(elt)-1);   // diminue le nombre d'element elt de 1
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
    T elt = (T) e;
    if(multiset.containsKey(elt)) {     // verifie que 'e' est present dans le multiset
      if(multiset.get(elt) == count) {  // si 'e' present 'count' fois
        multiset.remove(elt);           // 'e' est retiré du multiset
        taille--;
        return true;                    // confirmation
      }
      if(multiset.get(elt) > count) {   // si 'e' est present plus que 'count' fois
        multiset.put(elt, multiset.get(elt) - count); // on retire 'count' fois 'e'
        taille--;
        return true;
      }
      if(multiset.get(elt) < count) {   // si 'e' n'est pas disponible en suffisament d'exemplaire
        multiset.remove(e);             // on retire 'e' du multiset
        taille --;
        return true;
      }
    }
    return false;    
  }

  @Override
  public int count(Object o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

}
