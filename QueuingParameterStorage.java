/*
 * Project Title : Final Project - Queuing System Solver (M/M/1/N Ver.)
 * Author : Yonghwan Yim
 * Final Update : 2017.12.03
 */
package queuingsystemsolver;
/*
M/M/1/N의 Queuing System에서 도착률, 서비스율, 목표 고객이탈률(ex.15% 이하)을 입력시 목표 고객이탈률을 만족시키는 최소의 N값을 반환하는 프로그램.
"arrivalRate, serviceRate, goalBounceRate"을 입력받아야 함
입력된 정보를 바탕으로 goalBounceRate를 만족시키는 N을 찾고, 계산 결과를 저장하기 위한 Interface.
*/
public interface QueuingParameterStorage {
    
    void addQueuingParameterInfo(double arrivalRate, double serviceRate, double goalBounceRate);
}
/*
 * Queuing System의 입력 Parameter를 저장하기 위한 Class.
 * "QueuingSystemDetail"이 "QueuingParameterInfo"를 상속함.
 */
class QueuingParameterInfo
{
    protected double arrivalRate;
    protected double serviceRate;
    protected double goalBounceRate;
    
    QueuingParameterInfo(double arrivalRate, double serviceRate, double goalBounceRate) 
    {
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
        this.goalBounceRate = goalBounceRate;
    }
    
    double getArrivalRate() { return arrivalRate; }
    double getServiceRate() { return serviceRate; }
    double getGoalBounceRate() { return goalBounceRate; }
}

