package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreMajorityTest {

    @Test
    void testEmptyArray() { // Тест пустого массива.
        assertEquals(-1, BoyerMooreMajorityVote.findMajorityElement(new int[]{}, tracker));
    }


    @Test
    void testSingleElement() {
        assertEquals(5, BoyerMooreMajorityVote.findMajorityElement(new int[]{5}, tracker));
    }

    @Test
    void testMajorityExists() {
        assertEquals(2, BoyerMooreMajorityVote.findMajorityElement(new int[]{2, 2, 1}, tracker));
        assertEquals(3, BoyerMooreMajorityVote.findMajorityElement(new int[]{3, 2, 3}, tracker));
    }

    @Test
    void testNoMajority() {
        assertEquals(-1, BoyerMooreMajorityVote.findMajorityElement(new int[]{1, 2, 3}, tracker));
    }

    @Test
    void testDuplicates() {
        assertEquals(1, BoyerMooreMajorityVote.findMajorityElement(new int[]{1,1,1,1}, tracker));
    }

    @Test
    void testEvenLengthNoMajority() {
        assertEquals(-1, BoyerMooreMajorityVote.findMajorityElement(new int[]{1,2,1,2}, tracker));
    }

    @Test
    void testSortedArray() { // Отсортированный с большинством.
        assertEquals(2, BoyerMooreMajorityVote.findMajorityElement(new int[]{1,2,2,2,3}, tracker));
    }

    @Test
    void testReverseSorted() {
        assertEquals(2, BoyerMooreMajorityVote.findMajorityElement(new int[]{3,2,2,2,1}, tracker));
    }
}