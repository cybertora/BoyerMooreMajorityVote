package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreMajorityVoteTest {

    @Test
    void testMajorityElement() {
        assertEquals(3, BoyerMooreMajorityVote.findMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4}));
    }

    @Test
    void testNoMajorityElement() {
        assertEquals(-1, BoyerMooreMajorityVote.findMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4}));
    }

    @Test
    void testSingleElementArray() {
        assertEquals(1, BoyerMooreMajorityVote.findMajorityElement(new int[]{1}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(-1, BoyerMooreMajorityVote.findMajorityElement(new int[]{}));
    }
}
