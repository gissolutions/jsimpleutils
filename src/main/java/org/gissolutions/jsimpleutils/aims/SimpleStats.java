package org.gissolutions.jsimpleutils.aims;

import java.util.ArrayList;
import java.util.List;

public class SimpleStats {
	private final String serviceName;
	private List<Double> data;
	
	public SimpleStats(String serviceName) {
		super();
		this.serviceName = serviceName;
		this.data = new ArrayList<Double>();
	}
	public int addData(double dat) {
		this.data.add(dat);
		return this.data.size();		
	}
	
	public int getCount() {
		return this.data.size();
	}
	public double getTotal() {
		double tot =0;
		for (Double dat : data) {
			tot += dat.doubleValue();
		}
		return tot;
	}
	
	public double getAverage() {
		double avg = getTotal()/getCount();
		return avg;
	}
	public double getStandardDeviation() {
		double avg = getAverage();
		double t =0;
		for (Double d : data) {
			t += Math.pow(d.doubleValue() - avg, 2.0);
		}
		double t2 = Math.sqrt(t / (getCount()-1));
		return t2;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	
	
}
