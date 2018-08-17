/*
 * This file is part of the Disco Deterministic Network Calculator.
 *
 * Copyright (C) 2013 - 2018 Steffen Bondorf
 * Copyright (C) 2017+ The DiscoDNC contributors
 *
 * Distributed Computer Systems (DISCO) Lab
 * University of Kaiserslautern, Germany
 *
 * http://discodnc.cs.uni-kl.de
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

package de.uni_kl.cs.discodnc.nc;

import de.uni_kl.cs.discodnc.CurveBackend;
import de.uni_kl.cs.discodnc.CurveBackend_DNC;
import de.uni_kl.cs.discodnc.curves.Curve;
import de.uni_kl.cs.discodnc.numbers.NumBackend;
import de.uni_kl.cs.discodnc.minplus.MinPlus;

import java.io.File;

public final class CalculatorConfig {
	private static CalculatorConfig instance = new CalculatorConfig();
	private NumBackend NUM_BACKEND = NumBackend.REAL_DOUBLE_PRECISION;
	private CurveBackend CURVE_IMPLEMENTATION = CurveBackend_DNC.DNC;
	private boolean ARRIVAL_CURVE_CHECKS = false;
	private boolean SERVICE_CURVE_CHECKS = false;
	private boolean MAX_SERVICE_CURVE_CHECKS = false;
	private boolean FIFO_MUX_CHECKS = false;
	private boolean DECONVOLUTION_CHECKS = false;

	protected CalculatorConfig() {
	}

	public static CalculatorConfig getInstance() {
		return instance;
	}

	public NumBackend getNumBackend() {
		return NUM_BACKEND;
	}

	public boolean setNumBackend(NumBackend backend) {
		if (NUM_BACKEND == backend) {
			return false;
		} else {
			NUM_BACKEND = backend;
			return true;
		}
	}

	public CurveBackend getCurveImpl() {
		return CURVE_IMPLEMENTATION;
	}

	private void checkMPARTC() throws RuntimeException {
		String classpath = System.getProperty("java.class.path");
		for (String classpathEntry : classpath.split(File.pathSeparator)) {
			if (classpathEntry.contains("rtc.jar")) {
				return;
			}
		}
		throw new RuntimeException("rtc.jar cannot be found on the classpath!");
	}

	public boolean setCurveImpl(CurveBackend curve_impl) {
		checkMPARTC();

		if (CURVE_IMPLEMENTATION == curve_impl) {
			return false;
		}
		CURVE_IMPLEMENTATION = curve_impl;
		return true;
	}

	public void disableAllChecks() {
		ARRIVAL_CURVE_CHECKS = false;
		SERVICE_CURVE_CHECKS = false;
		MAX_SERVICE_CURVE_CHECKS = false;
		FIFO_MUX_CHECKS = false;
		DECONVOLUTION_CHECKS = false;
	}

	public void enableAllChecks() {
		ARRIVAL_CURVE_CHECKS = true;
		SERVICE_CURVE_CHECKS = true;
		MAX_SERVICE_CURVE_CHECKS = true;
		FIFO_MUX_CHECKS = true;
		DECONVOLUTION_CHECKS = true;
	}

	public boolean exec_arrival_curve_checks() {
		return ARRIVAL_CURVE_CHECKS;
	}

	public boolean exec_service_curve_checks() {
		return SERVICE_CURVE_CHECKS;
	}

	public boolean exec_max_service_curve_checks() {
		return MAX_SERVICE_CURVE_CHECKS;
	}

	public boolean exec_fifo_mux_checks() {
		return FIFO_MUX_CHECKS;
	}

	public boolean exec_deconvolution_checks() {
		return DECONVOLUTION_CHECKS;
	}

	@Override
	public String toString() {
		StringBuffer calculator_config_str = new StringBuffer();

		calculator_config_str.append(getNumBackend().toString());
		calculator_config_str.append(", ");
		calculator_config_str.append(getCurveImpl().toString());

		if (exec_arrival_curve_checks()) {
			calculator_config_str.append(", ");
			calculator_config_str.append("AC checks");
		}
		if (exec_service_curve_checks()) {
			calculator_config_str.append(", ");
			calculator_config_str.append("SC checks");
		}
		if (exec_max_service_curve_checks()) {
			calculator_config_str.append(", ");
			calculator_config_str.append("MSC checks");
		}
		if (exec_fifo_mux_checks()) {
			calculator_config_str.append(", ");
			calculator_config_str.append("FIFO checks");
		}
		if (exec_deconvolution_checks()) {
			calculator_config_str.append(", ");
			calculator_config_str.append("deconv checks");
		}

		return calculator_config_str.toString();
	}
	
	public MinPlus getMinPlus() {
		return CURVE_IMPLEMENTATION.getMinPlus();
	}
	
	public Curve getCurve() {
		return CURVE_IMPLEMENTATION.getCurveFactory();
	}
}
