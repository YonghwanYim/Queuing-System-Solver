/*
 * Project Title : Final Project - Queuing System Solver (M/M/1/N Ver.)
 * Author : Yonghwan Yim
 * Final Update : 2017.12.03
 */
package queuingsystemsolver;
import java.util.*;

public abstract class QueuingParameterStorageImp implements QueuingParameterStorage
{
    HashMap<Integer, QueuingSystemDetail> map = new HashMap<>();
    protected int key = 0; // HashMap에 저장된 순서를 Key로 기억함. (후에 순차적으로 log데이터를 출력해주기 위함)
    QueuingParameterStorageImp() { } // Constructor
    
    @Override
    public void addQueuingParameterInfo(double arrivalRate, double serviceRate, double goalBounceRate)
    {
        map.put(key,new QueuingSystemDetail(arrivalRate, serviceRate, goalBounceRate));
        key++;
    }
    
    public int getKey() { return key; }          // key를 반환.
    public int mapSize() { return map.size(); }  // 저장된 데이터 수를 반환.
    protected abstract int calculateN(double arrivalRate, double serviceRate, double goalBounceRate);
    public abstract String getDiscription(int key);
}