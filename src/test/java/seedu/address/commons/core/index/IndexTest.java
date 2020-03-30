package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {
        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createOneBasedIndex_invalidIndex_throwsIndexOutOfBoundsException() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));
    }

    @Test
    public void createZeroBasedIndex() {
        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    public void createZeroBasedIndex_invalidIndex_throwsIndexOutOfBoundsException() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));
    }

    @Test
    public void equalsMethod() {
        final Index fifthPersonIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertEquals(fifthPersonIndex, Index.fromOneBased(5));
        assertEquals(fifthPersonIndex, Index.fromZeroBased(4));

        // same object -> returns true
        assertEquals(fifthPersonIndex, fifthPersonIndex);

        // null -> returns false
        assertNotEquals(null, fifthPersonIndex);

        // different types -> returns false
        assertNotEquals(5.0f, fifthPersonIndex);

        // different index -> returns false
        assertNotEquals(fifthPersonIndex, Index.fromOneBased(1));
    }
}
