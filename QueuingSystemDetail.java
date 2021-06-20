/*
 * Project Title : Final Project - Queuing System Solver (M/M/1/N Ver.)
 * Author : Yonghwan Yim
 * Final Update : 2017.12.03
 */
package queuingsystemsolver;

class QueuingSystemDetail extends QueuingParameterInfo
{
    private int N;              // capacity N
    private double p;           // utilization factor
    private double P0;
    private double PN;          // the proportion of time that system is full
    private double lambdaAlpha; // average arrival rate
    private double Ls;          // number of customers receiving service
    private double Lq;          // average number of customers in the queue
    private double Ws;          // average service hours
    private double Wq;          // average wait time in the queue
    
    public QueuingSystemDetail(double arrivalRate, double serviceRate, double goalBounceRate) // N을 찾기 전에 이 생성자를 임의로 호출 (N을 모르면 System특성치를 계산할 수 없음).
    { 
        super(arrivalRate, serviceRate, goalBounceRate);
    }
    
    public QueuingSystemDetail(double arrivalRate, double serviceRate, double goalBounceRate, int N) // N을 찾은 후, HashMap에 저장할 때 호출.
    {
        super(arrivalRate, serviceRate, goalBounceRate);
        calculateCharacteristic(N); // System 특성치를 계산, 저장.
    }
    
    // N을 받아서 나머지 System 특성치를 계산하는 Method
    public void calculateCharacteristic(int N)
    {
        this.N = N;
        calculate_p(arrivalRate, serviceRate);
        calculate_P0(p, N);
        calculate_PN(P0, p, N);
        calculate_lambdaAlpha(arrivalRate, PN);
        calculate_Ls(p, N);
        calculate_Lq(Ls, P0);
        calculate_Ws(Lq, lambdaAlpha, serviceRate);
        calculate_Wq(Ws, serviceRate);
    }
    
    // Private 맴버변수의 값을 외부에서 반환하기 위한 Get Method.
    public int getN() { return N; }
    public double getp() { return p; }
    public double getP0() { return P0; }
    public double getPN() { return PN; }
    public double getLambdaAlpha() { return lambdaAlpha; }
    public double getLs() { return Ls; }
    public double getLq() { return Lq; }
    public double getWs() { return Ws; }
    public double getWq() { return Wq; }
    
    // M/M/1/N Queuing System Characteristic Formula
    private void calculate_p(double arrivalRate, double serviceRate) { p = arrivalRate / serviceRate; }
    private void calculate_P0(double p, int N) { P0 = (1-p) / (1 - Math.pow(p,N+1)); }
    private void calculate_PN(double P0, double p, int N) { PN = P0 * Math.pow(p, N); }
    private void calculate_lambdaAlpha(double arrivalRate, double PN) { lambdaAlpha = arrivalRate * (1 - PN); }
    private void calculate_Ls(double p, int N) { Ls = p * ((1 - (N+1) * Math.pow(p, N) + N * Math.pow(p, N+1)) / ( (1-p) * (1 - Math.pow(p, N+1)) )); }
    private void calculate_Lq(double Ls, double P0) { Lq = Ls - (1 - P0); }
    private void calculate_Ws(double Lq, double lambdaAlpha, double serviceRate) { Ws = (Lq / lambdaAlpha) + (1 / serviceRate); }
    private void calculate_Wq(double Ws, double serviceRate) { Wq = Ws - (1 / serviceRate); }
}