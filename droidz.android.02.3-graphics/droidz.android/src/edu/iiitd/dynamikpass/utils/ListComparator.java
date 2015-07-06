package edu.iiitd.dynamikpass.utils;

import java.util.List;

public class ListComparator<T> {

    public Boolean compare(List<List<T>> a, List<List<T>> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).size() != b.get(i).size()) {
                return false;
            }
            for (int k = 0; k < a.get(i).size(); k++) {
                if(!a.get(i).get(k).equals(b.get(i).get(k))) {
                    return false;
                }
            }
        }
        return true;
    }

}
