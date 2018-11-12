package mandelbrot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double real2 = 5;
    private final double imaginary = 10;
    private final double imaginary2 = 4;


    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract(){
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    /** Tâche 2, creation des tests */

    @Test
    void testReal(){
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, 0);
        assertEquals(c2,c1.real(real));
        Complex c3 = new Complex(real2, imaginary2);
        Complex c4 = new Complex(real2, 0);
        assertEquals(c4,c3.real(real2));
    }

    @Test
    void testAdd() {
        //test1
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        Complex c3 = new Complex(real+real, imaginary+imaginary);
        assertEquals(c3, c1.add(c2));
        //test2
        Complex c4 = new Complex(real2, imaginary2);
        Complex c5 = new Complex(real2, imaginary2);
        Complex c6 = new Complex(real2+real2, imaginary2+imaginary2);
        assertEquals(c6, c4.add(c5));
    }

    @Test
    void testSubtract() {
        //test1
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        Complex c3 = new Complex(real-real, imaginary-imaginary);
        assertEquals(c3, c1.subtract(c2));
        //test2
        Complex c4 = new Complex(real2, imaginary2);
        Complex c5 = new Complex(real2, imaginary2);
        Complex c6 = new Complex(real2-real2, imaginary2-imaginary2);
        assertEquals(c6, c4.subtract(c5));
    }

    @Test
    void testMultiply() {
        //test1
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        Complex c3 = new Complex(real * real - imaginary * imaginary,
                real * imaginary + real * imaginary);
        assertEquals(c3, c1.multiply(c2));
        //test2
        Complex c4 = new Complex(2, 1);
        Complex c5 = new Complex(1, 2);
        Complex c6 = new Complex(0, 5);
        assertEquals(c6, c4.multiply(c5));
    }

    @Test
    void testSquaredModulus() {
        //test1
        Complex c1 = new Complex(2, 3);
        assertEquals(13, c1.squaredModulus());
        //test2
        Complex c2 = new Complex(4, 6);
        assertEquals(52, c2.squaredModulus());
    }

}