package de.uni_kl.cs.discodnc;

import de.uni_kl.cs.discodnc.curves.Curve;
import de.uni_kl.cs.discodnc.curves.LinearSegment;
import de.uni_kl.cs.discodnc.curves.dnc_affine.AffineLinearSegment_DNC;
import de.uni_kl.cs.discodnc.curves.dnc_pwaffine.Curve_DNC;
import de.uni_kl.cs.discodnc.minplus.MinPlus;
import de.uni_kl.cs.discodnc.minplus.MinPlus_DNC;

public enum CurveBackend_DNC implements CurveBackend {
		DNC;
	
		@Override
		public MinPlus getMinPlus() {
			return MinPlus_DNC.MIN_PLUS_DNC;
		}

		@Override
		public Curve getCurveFactory() {
			return Curve_DNC.getFactory();
		}

		@Override
		public LinearSegment.Builder getLinearSegmentFactory() {
			return AffineLinearSegment_DNC.getBuilder();
		}
}
