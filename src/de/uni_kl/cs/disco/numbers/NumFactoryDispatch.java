/*
 * This file is part of the Disco Deterministic Network Calculator v2.4.0beta2 "Chimera".
 *
 * Copyright (C) 2014 - 2017 Steffen Bondorf
 * Copyright (C) 2017 The DiscoDNC contributors
 *
 * Distributed Computer Systems (DISCO) Lab
 * University of Kaiserslautern, Germany
 *
 * http://disco.cs.uni-kl.de/index.php/projects/disco-dnc
 *
 *
 * The Disco Deterministic Network Calculator (DiscoDNC) is free software;
 * you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; 
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package de.uni_kl.cs.disco.numbers;

import de.uni_kl.cs.disco.nc.CalculatorConfig;
import de.uni_kl.cs.disco.nc.CalculatorConfig.NumClass;
import de.uni_kl.cs.disco.numbers.implementations.RationalBigIntFactory;
import de.uni_kl.cs.disco.numbers.implementations.RationalIntFactory;
import de.uni_kl.cs.disco.numbers.implementations.RealDoubleFactory;
import de.uni_kl.cs.disco.numbers.implementations.RealSingleFactory;


public abstract class NumFactoryDispatch {
    private static NumFactory realdouble_factory = new RealDoubleFactory();
    private static NumFactory realsingle_factory = new RealSingleFactory();
    private static NumFactory rationalint_factory = new RationalIntFactory();
    private static NumFactory rationalbigint_factory = new RationalBigIntFactory();

    private static NumFactory factory = getNumFactory();

    private static NumFactory getNumFactory() {
        switch (CalculatorConfig.getInstance().getNumClass()) {
            case REAL_SINGLE_PRECISION:
                return realsingle_factory;
            case RATIONAL_INTEGER:
                return rationalint_factory;
            case RATIONAL_BIGINTEGER:
                return rationalbigint_factory;
            case REAL_DOUBLE_PRECISION:
            default:
                return realdouble_factory;
        }
    }

    public static void setNumClass(NumClass num_class) {
        switch (num_class) {
            case REAL_SINGLE_PRECISION:
                factory = realsingle_factory;
                return;
            case RATIONAL_INTEGER:
                factory = rationalint_factory;
                return;
            case RATIONAL_BIGINTEGER:
                factory = rationalbigint_factory;
                return;
            case REAL_DOUBLE_PRECISION:
            default:
                factory = realdouble_factory;
                return;
        }
    }

    public static Num getPositiveInfinity() {
        return factory.getPositiveInfinity();
    }

    public static Num createPositiveInfinity() {
        return factory.createPositiveInfinity();
    }

    public static Num getNegativeInfinity() {
        return factory.getNegativeInfinity();
    }

    public static Num createNegativeInfinity() {
        return factory.createNegativeInfinity();
    }

    public static Num getNaN() {
        return factory.getNaN();
    }

    public static Num createNaN() {
        return factory.createNaN();
    }

    public static Num getZero() {
        return factory.getZero();
    }

    public static Num createZero() {
        return factory.createZero();
    }

    public static Num getEpsilon() {
        return factory.getEpsilon();
    }

    public static Num createEpsilon() {
        return factory.createEpsilon();
    }

    public static Num create(double value) {
        return factory.create(value);
    }

    public static Num create(int num, int den) {
        return factory.create(num, den);
    }

    public static Num create(String num_str) throws Exception {
        return factory.create(num_str);
    }
}