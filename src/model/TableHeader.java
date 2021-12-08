package model;


import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
/**
 * In aceasta clasa vom crea o metodele care primesc obiecte si genereaza header-ul tabelului din baza de date si il populeaza
 * @author Tincu Diana
 */
public class TableHeader {
    /**
     * Acesta metoda genereaza liniile tabelului
     * @param object
     */
    public static List<String> generateHeaders(Object object) {
        List<String> hlist = new ArrayList<>();
        for (Field f : object.getClass().getDeclaredFields())
        {
            f.setAccessible(true); hlist.add(f.getName());
        }
        return hlist;
    }

    /**
     * Acesta metoda primeste obiecte cu ajutorul carora populeaza tabelul
     * @param object
     */
    public static List<String> generateRows(Object object) {
        List<String> list = new ArrayList<>();
        for (Field f : object.getClass().getDeclaredFields()) {
            Object ob;
            f.setAccessible(true);
            try {
                ob= f.get(object); list.add(String.valueOf(ob));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

