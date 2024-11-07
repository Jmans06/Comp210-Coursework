package assn05;

public class Main {

    /**
     * Write a series of tests to check the functionality of each task
     * @param args
     */
    public static void main(String[] args) {
        testT1();
        testT2();
        testT3();
        testT4();
    }

    /**
     * Test Task 1 - Write some tests to convince yourself that your code for Task 1 is working
     */
    public static void testT1(){
        SimpleEmergencyRoom er=new SimpleEmergencyRoom();
        er.addPatient(1,65);
        er.addPatient(2, 50);
        er.addPatient(3, 10);
        System.out.println(er.getPatients());
        er.dequeue();
        System.out.println(er.getPatients());
        er.dequeue();
        System.out.println(er.getPatients());
    }

    /**
     * Test Task 2 - Write some tests to convince yourself that your code for Task 2 (A & B) is working
     */
    public static void testT2(){
        MaxBinHeapER ER=new MaxBinHeapER();
        ER.enqueue(1);
        ER.dequeue();
    }

    /**
     * Test Task 3 - This part can be used to test for task 3.
     */
    public static void testT3(){
        MaxBinHeapER transfer = new MaxBinHeapER();
        transfer.enqueue(0, -1);
        transfer.enqueue(1, 2);
        transfer.enqueue(2, 1);
        transfer.enqueue(3, 0);
        transfer.enqueue(4, -1);
        transfer.updatePriority(0, -1);
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < arr.length; i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
        transfer.updatePriority(0, 3);
        arr = transfer.getAsArray();
        System.out.println();
        for(int i = 0; i < arr.length; i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /**
     * Test Task 4 - Write some tests to convince yourself that your code for Task 4 is working
     * You can use some of the helper methods already given below
     */
    public static void testT4() {
        compareRuntimes();
    }

    /**
     * fills up an Emergency Room based on a MaxBinHeapER
     * @param complexER an initially empty MaxBinHeapER
     */
    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    /**
     * fills up an Emergency Room based on a SimpleEmergencyRoom (overloaded)
     * @param simpleER an initially empty SimpleEmergencyRoom
     */
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    /**
     * Creates an array of patients
     * @return returns this array of patients
     */
    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    /**
     * TODO (Task 4): compareRuntimes
     * Compares the Run Times of the SimpleEmergencyRoom vs MaxBinHeapER
     * @return an array of results as follows:
     * index 0: total nanosec for simpleER
     * index 1: average nanosec for simpleER
     * index 2: total nanosec for maxHeapER
     * index 3: average nanosec for maxHeapER
     */
    public static double[] compareRuntimes() {
        // Array which you will populate as part of Task 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.1) Here
        double totalTimeSimple=0;
        for (int i=0; i<100000; i++){
            double startTime1=System.nanoTime();
            simplePQ.dequeue();
            double endTime1=System.nanoTime();
            totalTimeSimple+=(endTime1-startTime1);
        }
        results[0]=totalTimeSimple;
        results[1]=totalTimeSimple/100000;
        // Code for (Task 4.2) Here
        double totalTimeComplex=0;
        for (int i=0; i<100000; i++){
            double startTime2=System.nanoTime();
            binHeap.dequeue();
            double endTime2=System.nanoTime();
            totalTimeComplex+=(endTime2-startTime2);
        }
        results[2]=totalTimeComplex;
        results[3]=totalTimeComplex/100000;
        System.out.println(results[0]+" "+results[1]+" "+results[2]+" "+results[3]);
        return results;
    }
}
