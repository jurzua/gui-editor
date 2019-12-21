package cl.talca.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListConcurrencyIssue {

    public static void main(String ... args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        //removeListByDoubleLists(list);

        list = list.stream()
                .filter(integer -> integer != 3)
                .collect(Collectors.toList());


        System.out.println(Arrays.toString(list.toArray()));
    }

    private static void removeListByDoubleLists(List<Integer> list) {
        List<Integer> listToDelete = new ArrayList<>();
        for(Integer i : list) {
            if(i == 3) {
                listToDelete.add(i);
                // list.remove(i);
            }
        }
        for(Integer toDelete : listToDelete) {
            list.remove(toDelete);
        }
    }

}
