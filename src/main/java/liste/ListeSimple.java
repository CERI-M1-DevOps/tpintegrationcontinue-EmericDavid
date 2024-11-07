package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne la taille de la liste.
     * 
     * @return La taille de la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un élément en tête de la liste.
     * 
     * @param element L'élément à ajouter.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la valeur du premier nœud dont l'élément correspond à l'élément
     * donné.
     * 
     * @param element        L'élément à modifier.
     * @param nouvelleValeur La nouvelle valeur à attribuer.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie la valeur de tous les nœuds dont l'élément correspond à l'élément
     * donné.
     * 
     * @param element        L'élément à modifier.
     * @param nouvelleValeur La nouvelle valeur à attribuer.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation textuelle de la liste.
     * 
     * @return Une représentation textuelle de la liste.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier nœud dont l'élément correspond à l'élément donné.
     * 
     * @param element L'élément à supprimer.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les nœuds dont l'élément correspond à l'élément donné.
     * 
     * @param element L'élément à supprimer.
     */
    public void supprimeTous(int element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Méthode récursive pour supprimer tous les nœuds dont l'élément correspond à
     * l'élément donné.
     * 
     * @param element L'élément à supprimer.
     * @param tete    Le nœud courant.
     * @return Le nouveau nœud tête de la liste.
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else
            return null;
    }

    /**
     * Retourne le nœud précédant le dernier nœud de la liste.
     * 
     * @return Le nœud précédant le dernier nœud de la liste, ou null si la liste
     *         est vide ou ne contient qu'un seul élément.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des nœuds de la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le nœud précédant le nœud donné.
     * 
     * @param r Le nœud dont on cherche le précédent.
     * @return Le nœud précédant le nœud donné.
     */
    public Noeud getPrecedent(Noeud r) {
        // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node
        // existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange les positions de deux nœuds dans la liste.
     * 
     * @param r1 Le premier nœud à échanger.
     * @param r2 Le second nœud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}