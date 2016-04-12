package patternTree;

import trainingSet.CorrectUrl;

import java.util.*;

/**
 * Created by navid
 */

public class PatternTreeMaker {

    /***
     * @param urlGroup List of CorrectUrls
     * @param keyDone  set of keys that have been processed
     * @return a patternTree node for URLs in urlGroup
     */
    public PatternTreeNode<String> makePatternTree(List<CorrectUrl> urlGroup, Set<String> keyDone, PatternTreeNode<String> parentNode , Map<String,List<String>> trivialMap) {

        //TODO
//        1: Create a new node t
        PatternTreeNode<String> patternTreeNode = new PatternTreeNode<>(urlGroup, parentNode);

//        3: Calculate entropy H(k) for each key in U
        Map<String, Double> entropies = calculateEntropy(urlGroup);

//        2: Generate a regular expression for t to describe URLs in U
        patternTreeNode.setData(generateRegularExpresion(entropies));

//        4: if ( ∃ k !∈ kDone ) {    5: Let k∗ !∈ kDone be the key that minimizes H(k)
        for (Map.Entry<String, Double> entry : sortMapByValue(entropies).entrySet()) {
            if (!keyDone.contains(entry.getKey()) && entry.getValue() != 0) {

                //        6: Vk∗ = ∅

                //        7: for all URL u ∈ U do

                for (CorrectUrl url : urlGroup) {
//            8: if u(k∗) is a trivial value then
//                    if(trivialMap.containsKey(entry.getKey()) && trivialMap.get(entry.getKey()).contains())

//               9: Vk∗ = (Vk∗ ∪ “∗”)
//            10:else
//               11:Vk∗ = (Vk∗ ∪ u(k∗))
//          12:  end if
//          13: end for
                }
//        14: if (all u(k∗) are trivial values) {
//        15: return the node t
//        16:end if

//        17: Split U into sub-groups U1 , . . . , Ut according to Vk∗ AND 18: for(all subgroup Ui) {
                for (List<CorrectUrl> urlSubGroup : splitUrlGroup()) {
//            19: ch = BuildPatternTree(Ui , K done ∪ {k∗}) And 20: add ch to t as a child node
                    keyDone.add(entry.getKey());
                    patternTreeNode.addChild(makePatternTree(urlSubGroup, keyDone, patternTreeNode , trivialMap));
//        21: end for
                }
//        22: end if
            }
        }
//      return the node t
        return patternTreeNode;

    }

    /***
     * Generates a regular expression for pattern tree node to describe URLs in U
     *
     * @return String value of regular expression
     */
    private String generateRegularExpresion(Map<String, Double> antropyList) {
        //TODO
        // if Key entropy != 0 -> return *

        return null;
    }

    private Map<String, Double> calculateEntropy(List<CorrectUrl> urlGroup) {
        //TODO
        Map<String, Double> entropy = new LinkedHashMap<>();

        return entropy;
    }

    private List<List<CorrectUrl>> splitUrlGroup() {
        //TODO

        return null;
    }


    /***
     * Sort a Map by Values. Converts the Map into a List, sorts the List by Comparator and put the sorted list back to a Map
     *
     * @param map unsort Map
     * @return a sorted Map
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


}
