/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public final class emCHART_OPERATION_MODE {
	public final static emCHART_OPERATION_MODE CHART_NODOING = new emCHART_OPERATION_MODE(
			"CHART_NODOING", elecchartcoreJNI.CHART_NODOING_get());
	public final static emCHART_OPERATION_MODE CHART_ZOOMOUT = new emCHART_OPERATION_MODE(
			"CHART_ZOOMOUT");
	public final static emCHART_OPERATION_MODE CHART_ZOOMIN = new emCHART_OPERATION_MODE(
			"CHART_ZOOMIN");
	public final static emCHART_OPERATION_MODE CHART_MPAN = new emCHART_OPERATION_MODE(
			"CHART_MPAN");
	public final static emCHART_OPERATION_MODE CHART_CPAN = new emCHART_OPERATION_MODE(
			"CHART_CPAN");
	public final static emCHART_OPERATION_MODE CHART_OPENWZOOMOUT = new emCHART_OPERATION_MODE(
			"CHART_OPENWZOOMOUT");
	public final static emCHART_OPERATION_MODE CHART_HHCL = new emCHART_OPERATION_MODE(
			"CHART_HHCL");
	public final static emCHART_OPERATION_MODE CHART_PICK = new emCHART_OPERATION_MODE(
			"CHART_PICK");
	public final static emCHART_OPERATION_MODE CHOPM_NUMS = new emCHART_OPERATION_MODE(
			"CHOPM_NUMS");

	public final int swigValue() {
		return swigValue;
	}

	public String toString() {
		return swigName;
	}

	public static emCHART_OPERATION_MODE swigToEnum(int swigValue) {
		if (swigValue < swigValues.length && swigValue >= 0
				&& swigValues[swigValue].swigValue == swigValue)
			return swigValues[swigValue];
		for (int i = 0; i < swigValues.length; i++)
			if (swigValues[i].swigValue == swigValue)
				return swigValues[i];
		throw new IllegalArgumentException("No enum "
				+ emCHART_OPERATION_MODE.class + " with value " + swigValue);
	}

	private emCHART_OPERATION_MODE(String swigName) {
		this.swigName = swigName;
		this.swigValue = swigNext++;
	}

	private emCHART_OPERATION_MODE(String swigName, int swigValue) {
		this.swigName = swigName;
		this.swigValue = swigValue;
		swigNext = swigValue + 1;
	}

	private emCHART_OPERATION_MODE(String swigName,
			emCHART_OPERATION_MODE swigEnum) {
		this.swigName = swigName;
		this.swigValue = swigEnum.swigValue;
		swigNext = this.swigValue + 1;
	}

	private static emCHART_OPERATION_MODE[] swigValues = { CHART_NODOING,
			CHART_ZOOMOUT, CHART_ZOOMIN, CHART_MPAN, CHART_CPAN,
			CHART_OPENWZOOMOUT, CHART_HHCL, CHART_PICK, CHOPM_NUMS };
	private static int swigNext = 0;
	private final int swigValue;
	private final String swigName;
}
