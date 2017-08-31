package edu.lasalle.pprog2.ac2.model;

import java.util.Comparator;

/**
 * Clase Tasca
 *
 * S'encarrega de gestionar la informació referent a una tasca en concret
 *
 * @Author Miguel Abellán Donoso
 *
 * Versió 1.2
 */

public class Tasca implements Comparable{

    private String nomTasca;
    private String prioritat;
    private String data;

    public static final Comparator<Tasca> compareByData1 = new Comparator<Tasca>() {
        @Override
        public int compare(Tasca o1, Tasca o2) {
            return o1.getData().compareTo(o2.getData());
        }
    };
    public static final Comparator<Tasca> compareByData2 = new Comparator<Tasca>() {
        @Override
        public int compare(Tasca o1, Tasca o2) {
            return o2.getData().compareTo(o1.getData());
        }
    };

    public static final Comparator<Tasca> compareByPriority1 = new Comparator<Tasca>() {
        @Override
        public int compare(Tasca o1, Tasca o2) {
            return o1.getPrioritat().toLowerCase().compareTo(o2.getPrioritat().toLowerCase());
        }
    };

    public static final Comparator<Tasca> compareByPriority2 = new Comparator<Tasca>() {
        @Override
        public int compare(Tasca o1, Tasca o2) {
            return o2.getPrioritat().toLowerCase().compareTo(o1.getPrioritat().toLowerCase());
        }
    };




    public String getNomTasca() {
        return nomTasca;
    }

    public void setNomTasca(String nomTasca) {
        this.nomTasca = nomTasca;
    }

    public String getPrioritat() {
        return prioritat;
    }

    public void setPrioritat(String prioritat) {
        this.prioritat = prioritat;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Tasca) {
            Tasca t = (Tasca) o;
            return data.compareTo(t.getData());
        }
        return 0;
    }
}

