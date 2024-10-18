package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for MatrixV0 methods to ensure that they work
 * as desired and pass edge cases.
 *
 * @author Jafar Jarrar
 */
public class TestsByStudent {
    
    /**
    * Tests whether new matrices are initialized correctly.
    */
    @Test
    public void testNewMatrix() {
        // Testing constructor of three params:
        Matrix<String> test1 = new MatrixV0<String>(7,8,"WEE");
        assertEquals("WEE", test1.get(1,3), "Case where valid input is entered.");
        assertEquals(7, test1.width(), "Checks if width is initialized correctly.");
        assertEquals(8, test1.height(), "Checks if height is initialized correctly.");

        // Testing constructor of two params:
        Matrix<String> test2 = new MatrixV0<String>(5,6);
        assertEquals(null, test2.get(2,3), "Checks if array is set to default value.");
        assertEquals(5, test2.width(), "Checks if width is initialized correctly.");
        assertEquals(6, test2.height(), "Checks if height is initialized correctly.");

        // Testing invalid input for constructor of three params:
        assertThrows(NegativeArraySizeException.class, () -> {Matrix<String> test3 = new MatrixV0<String>(-1,-1,"WEE");}
            , "Checks if negative inputs throw exception.");

        // Testing invalid input for constructor of two params:
        assertThrows(NegativeArraySizeException.class, () -> {Matrix<String> test4 = new MatrixV0<String>(-1,-1);}
            , "Checks if negative inputs throw exception.");
    } // testNewMatrix()

    /**
     * Tests if values are retrieved correctly.
     * Checks if out of bounds exception is thrown when wrong row or col values are entered.
     */
    @Test
    public void testGet() {
        Matrix<String> test1 = new MatrixV0<String>(2,2,"wee");

        // Normal cases:
        assertEquals("wee", test1.get(0,0), "Case where first value is to be retrieved.");
        assertEquals("wee", test1.get(1,1), "Case where ");

        // Cases where invalid input is entered in parameters:
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.get(-1,0);}
            , "Case where value to be retrieved is less than bounds.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.get(2,2);}
            , "Case where value to be retrieved is greater than bounds.");
    } // testGet()

    /**
     * Tests if values are set correctly.
     * Checks if out of bounds exception is thrown when wrong row or col values are entered.
     */
    @Test
    public void testSet() {
        Matrix<String> test1 = new MatrixV0<String>(2,3, "wee");
        test1.set(1,1,"woo");
        
        // Case where value is replaced then returned to original value.
        assertEquals("woo", test1.get(1,1));
        test1.set(1,1,"wee");
        assertEquals("wee", test1.get(1,1));

        // Cases where invalid input is entered:
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.set(3,2, "woo");}
            , "Case where value to be set is out of bounds of row.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.set(-1, -1, "woo");}
            , "Case where value to be set is negative.");
    } // testSet()

    /**
     * Tests if height field carries the correct value when a matrix is initialized.
     */
    @Test
    public void testHeight() {
        Matrix<String> test1 = new MatrixV0<String>(2,3, "waa");
        assertEquals(3, test1.height(), "Checks if height is set and retrieved correctly.");
    } // testHeight()

    /**
     * Tests if width field carries the correct value when a matrix is initialized.
     */
    @Test
    public void testWidth() {
        Matrix<String> test1 = new MatrixV0<String>(2,3, "waa");
        assertEquals(2, test1.width(), "Checks if width is set and retrieved correctly.");
    } // testWidth()

    /**
     * Tests if a row of default values is inserted correctly and fields are adjusted accordingly.
     * Checks whether out of bounds exception is thrown in cases where invalid input is entered.
     */
    @Test
    public void testInsertRow1() {
        Matrix<String> test1 = new MatrixV0<String>(2,3, "a");
        test1.insertRow(2);
        assertMatrixEquals(
            new String[][]
                {{"a", "a",},
                {"a", "a",},
                {"a", "a",},
                {"a", "a",}}, test1, "Checks if row is inserted in the middle of the matrix.");

        test1.insertRow(0);
        assertMatrixEquals(
            new String[][]
                {{"a", "a",},
                {"a", "a",},
                {"a", "a",},
                {"a", "a",},
                {"a", "a",}}, test1, "Checks if row is inserted in the beginning of the matrix.");
        test1.insertRow(5);
                assertMatrixEquals(
                    new String[][]
                        {{"a", "a",},
                        {"a", "a",},
                        {"a", "a",},
                        {"a", "a",},
                        {"a", "a",},
                        { "a", "a",}}, test1, "Checks if row is inserted at the end of the matrix.");
        assertEquals(6, test1.height(), "Checks if height is adjusted accordingly.");

        assertThrows(IndexOutOfBoundsException.class, () -> {test1.insertRow(8);}
            , "Row cannot be inserted two indices after end of matriix.");
    } // testInsertRow()

    /**
     * Tests if an array of values is inserted correctly as a row.
     * Checks whether array size exception is thrown in cases where the array size does not match
     * the matrix width.
     * @throws ArraySizeException
     */
    @Test
    public void testInsertRow2() throws ArraySizeException {
        Matrix<String> test1 = new MatrixV0<String>(2, 2, "b");
        test1.insertRow(1, new String[] {"boo", "boo"});
        assertMatrixEquals(
            new String[][]
                {{"b", "b"},
                {"boo", "boo"},
                {"b", "b"}}, test1, "Checks if new values are inserted in the middle.");
        test1.insertRow(0, new String[] {"boo", "boo"});
        assertMatrixEquals(
            new String[][]
                {{"boo", "boo"},
                {"b", "b"},
                {"boo", "boo"},
                {"b", "b"}}, test1, "Checks if new values are inserted in the beginning.");
        test1.insertRow(4, new String[] {"boo", "boo"});
        assertMatrixEquals(
            new String[][]
                {{"boo", "boo"},
                {"b", "b"},
                {"boo", "boo"},
                {"b", "b"},
                {"boo", "boo"}}, test1, "Checks if new values are inserted at the end.");
        assertEquals(5, test1.height(), "Checks if height is adjusted after adding rows.");
        assertThrows(ArraySizeException.class, () -> {test1.insertRow(1, new String[] {"a", "a", "a"});}
            , "Checks that exception is thrown given incorrect row size.");
    } // testInsertRow2()

    /**
     * Tests if a column of default values is inserted correctly and fields are adjusted accordingly.
     * Checks whether out of bounds exception is thrown in cases where invalid input is entered.
     */
    @Test
    public void testInsertCol1 () {
        Matrix<String> test1 = new MatrixV0<String>(3, 2, "c");
        test1.insertCol(2);
        assertMatrixEquals(
            new String[][]
                {{"c", "c", "c", "c"},
                {"c", "c", "c", "c"}}, test1
                , "Checks if column is inserted in middle.");
        test1.insertCol(0);
        assertMatrixEquals(
            new String[][]
                {{"c", "c", "c", "c", "c"},
                {"c", "c", "c", "c", "c"}}, test1
                , "Checks if column is inserted in the beginning.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.insertCol(6);}
            , "Adding column in invalid position.");
        test1.insertCol(5);
        assertMatrixEquals(
            new String[][]
                {{"c", "c", "c", "c", "c", "c"},
                {"c", "c", "c", "c", "c", "c"}}, test1
                , "Checks if column is inserted at the end.");
        assertEquals(6, test1.width(), "Checks if width is adjusted.");
    } // testInsertCol()

    /**
     * Tests if an array of values is inserted correctly as a column.
     * Checks whether array size exception is thrown in cases where the array size does not match
     * the matrix height.
     * @throws ArraySizeException
     */
    @Test
    public void testInsertCol2 () throws ArraySizeException {
        Matrix<String> test1 = new MatrixV0<String>(3, 2, "c");
        test1.insertCol(2, new String[] {"cee", "cee"});
        assertMatrixEquals(
            new String[][]
                {{"c", "c", "cee", "c"},
                {"c", "c", "cee", "c"}}, test1
                , "Checks if column is inserted in middle.");
        test1.insertCol(0, new String[] {"dee", "dee"});
        assertMatrixEquals(
            new String[][]
                {{"dee", "c", "c", "cee", "c"},
                {"dee", "c", "c", "cee", "c"}}, test1
                , "Checks if column is inserted in the beginning.");
        test1.insertCol(5, new String[] {"fee", "fee"});
        assertMatrixEquals(
            new String[][]
                {{"dee", "c", "c", "cee", "c", "fee"},
                {"dee", "c", "c", "cee", "c", "fee"}}, test1
                , "Checks if column is inserted at the end.");
        assertEquals(6, test1.width(), "Checks if width is adjsusted.");
        assertThrows(ArraySizeException.class
            , () -> {test1.insertCol(2, new String[] {"noo", "noo", "noo"});}
            , "Checks that exception is thrown when given incorrect col size.");
    } // testInsertCol2()

    /**
     * Tests whether row is deleted correctly and if fields are adjusted accordingly.
     * Checks if out of bounds exception is thrown when matrix is empty and method is called.
     */
    @Test
    public void testDeleteRow() {
        Matrix<String> test1 = new MatrixV0<String>(3, 3, "d");
        test1.deleteRow(1);
        assertMatrixEquals(
            new String[][]
                {{"d", "d", "d"},
                {"d", "d", "d"}}, test1, "Checks that row is removed correctly.");

        Matrix<String> test2 = new MatrixV0<String>(3, 1, "e");
        test2.deleteRow(0);
        assertEquals(0, test2.height(), "Checks that matrix is deleted if only one row.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test2.deleteRow(0);}
            , "Checks that exception is thrown if invalid row position is passed.");
    } // testDeleteRow()

    /**
     * Tests whether column is deleted correctly and if fields are adjusted accordingly.
     * Checks if out of bounds exception is thrown when matrix is empty and method is called.
     */
    @Test
    public void testDeleteCol() {
        Matrix<String> test1 = new MatrixV0<String>(3, 3, "f");
        test1.deleteCol(1);
        assertMatrixEquals(
            new String[][]
                {{"f", "f"},
                {"f", "f"},
                {"f", "f"}}, test1, "Checks that column is removed correctly.");
        
        Matrix<String> test2 = new MatrixV0<String>(1, 3, "g");
        test2.deleteCol(0);
        assertEquals(0, test2.width(), "Checks that matrix is deleted if only one column.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test2.deleteCol(0);}
        , "Checks that exception is thrown if invalid column position is passed.");
    } // testDeleteCol()

    /**
     * Tests if desired region is filled correctly.
     * Checks if out of bounds exception is thrown for various invalid input cases.
     */
    @Test
    public void testFillRegion() {
        Matrix<String> test1 = new MatrixV0<String>(4, 4, "g");
        test1.fillRegion(   0, 0, 4, 4, "gee");
        assertMatrixEquals(
            new String[][]
                {{"gee", "gee", "gee", "gee"},
                {"gee", "gee", "gee", "gee"},
                {"gee", "gee", "gee", "gee"},
                {"gee", "gee", "gee", "gee"}}, test1, "Checks if entire region is filled.");
        test1.fillRegion(1, 1, 3, 3, "geg");
        assertMatrixEquals(
            new String[][]
                {{"gee", "gee", "gee", "gee"},
                {"gee", "geg", "geg", "gee"},
                {"gee", "geg", "geg", "gee"},
                {"gee", "gee", "gee", "gee"}}, test1, "Checks if region is filled in right positions.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.fillRegion(2,2,1,1, "g");}
            , "Checks if exception is thrown when end values are less than start values.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.fillRegion(1, 1, 5, 5, "e");}
            , "Checks if exception is thrown when end values exceed matrix size.");
    } // testFillRegion()

    /**
     * Tests if line is filled with desired value at correct indices even if the end bounds are
     * greater than the matrix size.
     * Checks if out of bounds exception is thrown for various invalid input cases.
     */
    @Test
    public void testFillLine() {
        Matrix<String> test1 = new MatrixV0<String>(5, 5, "h");
        test1.fillLine(0, 0, 2, 2, 5, 5, "him");
        assertMatrixEquals(
            new String[][]
                {{"him", "h", "h", "h", "h"},
                {"h", "h", "h", "h", "h"},
                {"h", "h", "him", "h", "h"},
                {"h", "h", "h", "h", "h"},
                {"h", "h", "h", "h", "him"}}, test1, "Checks that line is filled correctly.");
        test1.fillLine(0, 3, 1, 0, 7, 7, "her");
        assertMatrixEquals(
            new String[][]
                {{"him", "h", "h", "her", "h"},
                {"h", "h", "h", "her", "h"},
                {"h", "h", "him", "her", "h"},
                {"h", "h", "h", "her", "h"},
                {"h", "h", "h", "her", "him"}}, test1
                , "Checks that line is filled correctly when end bounds are greater than matrix size.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.fillLine(2, 1, 1, 1, 0, 0, "j");}
            , "Checks if exception is thrown then end values are less than start values.");
        assertThrows(IndexOutOfBoundsException.class, () -> {test1.fillLine(-1, 0, 1, 1, 5, 5, "k");}
            , "Checks if exception is thrown when start values are negative.");
    } // testFillLine()

    /**
     * Checks if matrix is cloned correctly and that the cloned matrix is not linked to the original matrix.
     */
    @Test
    public void testClone() {
        Matrix<String> test1 = new MatrixV0<String>(2, 3, "l");
        Matrix<String> test2 = test1.clone();
        assertMatrixEquals(
            new String[][]
                {{"l", "l"},
                {"l", "l"},
                {"l", "l"}}, test2, "Checks that matrix is cloned correctly.");
        test2.insertRow(0);
        assertMatrixEquals(
            new String[][]
                {{"l", "l"},
                {"l", "l"},
                {"l", "l"},
                {"l", "l"}}, test2, "Adding one row at the beginning.");
        assertMatrixEquals(
                    new String[][]
                        {{"l", "l"},
                        {"l", "l"},
                        {"l", "l"}}, test1, "Checks that initial matrix is not modified (they should not be linked)");
    } // testClone()

    /**
     * Checks if matrices are equal to one another when they should be, and are not
     * equal when they should not be.
     */
    @Test
    public void testEquals() {
        Matrix<String> test1 = new MatrixV0<String>(7, 7, "m");
        Matrix<String> test2 = test1.clone();
        assertEquals(true, test2.equals(test1), "Checking that two identical matrices are equal.");
        assertEquals(true, test1.equals(test2), "Checking that the equals method works inversely on the same matrices.");
        // Increasing the width of the second matrix.
        test2.insertCol(2);
        assertFalse(test2.equals(test1), "Checking that two matrices with different dimensions are not equal.");
        // Returning the second matrix to its original size.
        test2.deleteCol(2);
        // Changing the values of the second matrix but keeping the dimensions the same.
        test2.fillRegion(0, 0, 7, 7, "n");
        assertFalse(test1.equals(test2), "Checks that two matrices with equal dimensions but different values are not equal.");
    } // testEquals()
} // class TestsByStudent
