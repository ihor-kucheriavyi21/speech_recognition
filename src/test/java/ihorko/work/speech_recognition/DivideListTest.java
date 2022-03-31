package ihorko.work.speech_recognition;

import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DivideListTest {
    @Test
    public final void givenList_whenParitioningIntoNSublistsUsingGroupingBy_thenCorrect() {

        int listSize = 3;
        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        List<List<Integer>> subSets = ListUtils.partition(intList, listSize);

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = List.of(7, 8);
        Assertions.assertEquals(subSets.size(), listSize);
        Assertions.assertEquals(lastPartition, expectedLastPartition);
    }
}
