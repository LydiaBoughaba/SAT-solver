package AntSys;

public class Parametre<T> {
	
    static double ALPHA; 
    static double BETA ; 
    static double V_RATE ; 
    static double I_PHEROMON ; 
    static int MAX_ITERATIONS; 
    static int ANT_COUNT ; 
    static double Q_0 ;
     
	public Parametre(double aLPHA, double bETA, double v_RATE, double i_PHEROMON, int mAX_ITERATIONS, int aNT_COUNT,double q_0) {
		super();
		ALPHA = aLPHA;
		BETA = bETA;
		V_RATE = v_RATE;
		I_PHEROMON = i_PHEROMON;
		MAX_ITERATIONS = mAX_ITERATIONS;
		ANT_COUNT = aNT_COUNT;
		Q_0 = q_0;
	}
	public double getALPHA() {
		return ALPHA;
	}
	public void setALPHA(double aLPHA) {
		ALPHA = aLPHA;
	}
	public double getBETA() {
		return BETA;
	}
	public void setBETA(double bETA) {
		BETA = bETA;
	}
	public double getV_RATE() {
		return V_RATE;
	}
	public void setV_RATE(double v_RATE) {
		V_RATE = v_RATE;
	}
	public double getI_PHEROMON() {
		return I_PHEROMON;
	}
	public void setI_PHEROMON(double i_PHEROMON) {
		I_PHEROMON = i_PHEROMON;
	}
	public int getMAX_ITERATIONS() {
		return MAX_ITERATIONS;
	}
	public void setMAX_ITERATIONS(int mAX_ITERATIONS) {
		MAX_ITERATIONS = mAX_ITERATIONS;
	}
	public int getANT_COUNT() {
		return ANT_COUNT;
	}
	public void setANT_COUNT(int aNT_COUNT) {
		ANT_COUNT = aNT_COUNT;
	}
	public double getQ_0() {
		return Q_0;
	}
	public void setQ_0(double q_0) {
		Q_0 = q_0;
	} 
     
    
}
