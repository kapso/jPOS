/*
 * Copyright (c) 2006 jPOS.org
 *
 * See terms of license at http://jpos.org/license.html
 *
 */

package org.jpos.iso;

import junit.framework.TestCase;

import org.jpos.iso.EbcdicInterpreter;
import org.jpos.iso.Interpreter;

/**
 * @author joconnor
 */
public class EbcdicInterpreterTest extends TestCase {
    private Interpreter inter;

    /*
	 * @see TestCase#setUp()
	 */
    protected void setUp() throws Exception {
        inter = EbcdicInterpreter.INSTANCE;
    }

    public void testInterpret() {
        byte[] b = new byte[3];
        inter.interpret("123", b, 0);
        TestUtils.assertEquals(new byte[] {(byte)0xF1, (byte)0xF2, (byte)0xF3}, b);
    }

    public void testUninterpret() {
        byte[] b = new byte[] {(byte)0xF1, (byte)0xF2, (byte)0xF3};
        assertEquals("123", inter.uninterpret(b, 0, 3));
    }

    public void testGetPackedLength() {
        assertEquals(3, inter.getPackedLength(3));
    }

    public void testReversability() {
        String origin = "Abc123:.-";
        byte[] b = new byte[origin.length()];
        inter.interpret(origin, b, 0);
        
        assertEquals(origin, inter.uninterpret(b, 0, b.length));
    }
}