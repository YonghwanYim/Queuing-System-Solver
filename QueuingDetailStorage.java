/*
 * Project Title : Final Project - Queuing System Solver (M/M/1/N Ver.)
 * Author : Yonghwan Yim
 * Final Update : 2017.12.03
 */
package queuingsystemsolver;

class QueuingDetailStorage extends QueuingParameterStorageImp
{
    QueuingDetailStorage() { super(); }   
    
    @Override
    public void addQueuingParameterInfo(double arrivalRate, double serviceRate, double goalBounceRate)
    {
        map.put(key, new QueuingSystemDetail(arrivalRate, serviceRate, goalBounceRate, calculateN(arrivalRate, serviceRate, goalBounceRate))); // N을 계산한 후 결과를 생성자의 4번째 인자로 넣어줌.
        key++;
    }
    
    @Override
    protected int calculateN(double arrivalRate, double serviceRate, double goalBounceRate) // 목표 이탈률보다 이탈률이 작도록 하는 N의 최소값을 찾아냄.
    {
        QueuingSystemDetail tmp = new QueuingSystemDetail(arrivalRate, serviceRate, goalBounceRate);
        int N = 1;  // N은 1부터 시작.
        while(true)
        {
            tmp.calculateCharacteristic(N);  // N에 해당되는 System 특성치를 계산함.
            if(tmp.getPN() <= tmp.getGoalBounceRate())  // PN이 목표 이탈률보다 작으면 while문 탈출.
                break;
            N++;  // 1 증가.
            
            if(N > 199)  // N이 200이 넘어간다는 것은, 입력 데이터가 잘못된 경우임. 프로그램이 무한루프에 빠지지 않도록 break를 걸어 안정화.
            {
                System.out.println("계산 불가. 잘못된 입력입니다.");
                break;
            }
        }
        return N;
    }
    
    @Override
    public String getDiscription(int key) // 대기행렬 시스템 특성치 출력.
    {
        String result;
        result = "\r\n======= Queuing System Parameter (Data Number : " + (key + 1) + ") =======\r\n";
        result = result + "arrival Rate : " + map.get(key).getArrivalRate() + "\r\n";
        result = result + "Service Rate : " + map.get(key).getServiceRate() + "\r\n";
        result = result + "Goal Bounce Rate : " + map.get(key).getGoalBounceRate() + "\r\n\r\n";
        result = result + "Current Bounce Rate (PN) : " + map.get(key).getPN() + "\r\n";
        result = result + "N : " + map.get(key).getN() + "\r\n";
        result = result + "P0 : " + map.get(key).getP0() + "\r\n";
        result = result + "p : " + map.get(key).getp() + "\r\n";
        result = result + "Average arrival rate (lambdaAlpha) : " + map.get(key).getLambdaAlpha() + "\r\n";
        result = result + "Number of customers receiving service (Ls) : " + map.get(key).getLs() + "\r\n";
        result = result + "Average number of customers in the queue (Lq) : " + map.get(key).getLq() + "\r\n";
        result = result + "Average service hours (Ws) : " + map.get(key).getWs() + "\r\n";
        result = result + "Average wait time in the queue (wq) : " + map.get(key).getWq() + "\r\n";
        
        return result;
    }
}