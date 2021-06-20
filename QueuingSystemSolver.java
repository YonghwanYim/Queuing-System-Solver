/*
 * Project Title : Final Project - Queuing System Solver (M/M/1/N Ver.)
 * Author : Yonghwan Yim
 * Final Update : 2017.12.03
 */
package queuingsystemsolver;
import java.util.Scanner;
import java.io.*;

// 각 메뉴 선택에 대한 상수 선언 (Java에서 간단한 상수 선언시에는 주로 enum 대신 interface를 사용).
interface INIT_MENU { int EXIT = 0, KEYBOARD_INPUT = 1, FILE_INPUT = 2, OUTPUT = 3, FILE_OUTPUT = 4; }

public class QueuingSystemSolver {

    public static void main(String[] args) {
        int nMenu = 0;           
        boolean boolFlag = true;      // 메뉴선택화면의 escape variable.
        
        QueuingDetailStorage storage = new QueuingDetailStorage(); // 데이터를 저장하기 위한 저장소 생성.
        QueuingParameterStorageImp storagePointer = storage; // 다형성 활용. QueuingParameterStorageImp형 storagePointer가 QueuingDetailStorage형 storage를 참조함.
                                                 
        Scanner scan = new Scanner(System.in);
        
        File dataFile = new File("LogData.txt"); // LogData 출력파일 (Project 폴더 내에 생성).
        
        while(boolFlag) // 0이 입력되어 boolFlag가 false로 바뀌기 전까지 무한루프.
        {
            System.out.println("\r\n==== M/M/1/N Queuing System Solver ====");
            System.out.println("1. 데이터 입력");                            // 현실적인 데이터를 입력해야함. 불가능한 데이터 입력시 N을 찾을 수 없음. Example로 제시하는 데이터 입력할것.
            System.out.println("2. 파일로 데이터 입력 (InputDataFile.txt)"); // Project 폴더 내에 파일 존재 (자동으로 데이터 읽음).
            System.out.println("3. 전체 로그 데이터 출력");                  // 실행화면상에 로그 데이터 출력.
            System.out.println("4. 전체 로그 데이터 파일로 내보내기");        // Project 폴더 내의 LogData.txt로 로그 데이터 출력.
            System.out.println("0. 종료");                                  // boolFlag를 false로 바꾸어주고, while문 탈출 (프로그램 종료).
            System.out.print("메뉴 선택 : ");
            
            try { nMenu = Integer.parseInt(scan.nextLine()); }
            catch (Exception e)
            {
                System.out.println("잘못된 입력입니다. 0~4의 숫자를 입력해주세요.");
                continue;
            }
            
            switch(nMenu)
            {
                case INIT_MENU.KEYBOARD_INPUT:  // 키보드로 데이터를 입력받음. 아래에 명시해둔 16, 14, 0.15를 입력할것. 
                    try                         // N을 찾을 수 없는 불가능한 수치를 대입하면, Error 발생.
                    {
                        System.out.println("도착율, 서비스율, 목표이탈률을 입력하세요. (ex. 16 14 0.15)");
                        System.out.print("입력 : ");
                        String line;
                        line = scan.nextLine(); // 줄단위로 입력을 받음.
                        String[] tmpData = line.split(" ");  // 줄단위로 읽은 String을 space를 구분자로 분리 후 tmpData에 저장.
                        storage.addQueuingParameterInfo(Double.valueOf(tmpData[0]), Double.valueOf(tmpData[1]), Double.valueOf(tmpData[2]));  // tmpData의 각 요소를 Double로 변환 후 storage에 저장.
                    } catch(Exception e) {}
                    System.out.println("Input Complete");
                    break;
                    
                case INIT_MENU.FILE_INPUT:  // 파일로 데이터를 입력받음. 자동으로 수행됨.
                    try
                    {
                        FileInputStream fis = new FileInputStream("InputDataFile.txt"); // Project 폴더 내에 있는 InputDataFile.
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        String line;
                        
                        while((line = br.readLine()) != null) // 파일에서 줄 단위로 데이터를 읽고, EOF까지 while문 반복.
                        {
                            String[] tmpData = line.split(" ");  // 줄단위로 읽은 String을 space를 구분자로 분리 후 tmpData에 저장.
                            storage.addQueuingParameterInfo(Double.valueOf(tmpData[0]), Double.valueOf(tmpData[1]), Double.valueOf(tmpData[2]));  // tmpData의 각 요소를 Double로 변환 후 storage에 저장.
                        }
                        br.close();
                    } catch(Exception e) {}
                    System.out.println("File Input Complete");
                    break;
                    
                case INIT_MENU.OUTPUT:
                    try
                    {
                        for(int i = 0; i <storagePointer.mapSize(); i++)    // storage에 저장된 데이터 수만큼 반복. (다형성 활용 -> storagePointer 사용)
                            System.out.println(storage.getDiscription(i));  // getDiscription Method를 통해 System 특성 출력.
                    } catch(Exception e) {}
                    break;
                    
                case INIT_MENU.FILE_OUTPUT:
                    try
                    {
                        FileOutputStream file = new FileOutputStream(dataFile);
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        
                        for(int i = 0; i < storagePointer.mapSize(); i++)  // storage에 저장되어있는 LogData들을 파일로 출력. (다형성 활용 -> storagePointer 사용)
                            out.writeObject(storage.getDiscription(i));    // Object형태로 Write함.
                        out.close();
                    }
                    catch(IOException e) 
                    { 
                        e.printStackTrace(); 
                    }
                    System.out.println("File output Complete");
                    break;
                    
                case INIT_MENU.EXIT:  // 0이 입력되면 프로그램 종료.
                    boolFlag = false;
                    break;
            } 
        }
    }
}
