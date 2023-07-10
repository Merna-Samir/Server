package Server;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

	public class pro {
	public static void main(String[] args) {
	// write your code here
	int jobs_num = 5; // I will generate a 100 jobs only to be enter to the server
	int iterations = jobs_num;
	Queue jobs = generat_job(jobs_num); // Generating the queue with the arrival time
	HandleServers(jobs, iterations); // System.exit(-1);
	}
	private static void HandleServers(Queue jobs, int
	size) {
	float endTime1 = 0, endTime2 = 0;
	int pc1 = 0, pc2 = 0;
	int c1 = 0, c2 = 0;
	while (size > 0) {
	//create a callable for each method
	Callable<Void> callable1 = new
	Callable<Void>() {
	@Override
	public Void call() throws Exception {
	PC1(jobs, pc1, c1);
	return null;
	}
	};
	Callable<Void> callable2 = new
	Callable<Void>() {
	@Override
	public Void call() throws Exception {
	PC2(jobs, pc2, c2);
	return null;
	}
	};
	//add to a list
	List<Callable<Void>> taskList = new
	ArrayList<Callable<Void>>();
	taskList.add(callable1);
	taskList.add(callable2);
	//create a pool executor with 2 threads
	ExecutorService executor = Executors.newFixedThreadPool(2);
	 try {
         //start the threads and wait for them to finish
         executor.invokeAll(taskList);
     } catch (InterruptedException ie) {
     }
  //   while (pc1 == 1 && pc2 ==1){
     //}
     size -= 2;
 }

/*   int arr[] = new int[2];
 arr[0] = c1;
 arr[1] = c2;
 return arr;*/
}

 private static float Unirorm_random ( int min, int max){
     return (float) ((Math.random() * (max - min)) + min);
 }

	public static int Expo_random () {
	float number = Unirorm_random(0, 1);
	int expo = (int) Math.ceil(-Math.log(1 -
	number));
	return expo;
	}
	
	public static Queue generat_job ( int size){
		
	Queue job = new Queue(size);
	// the queue will take the arrival time only
	//Generate random float number to be converted to exponential
	float ExponentialNumber = Expo_random();
	// float expo_random = (float) (- Math.log(1 - random_num));
	
	// Calculate the system time
	Calendar TimeNow = Calendar.getInstance();
	float TIME1_HOUR = Math.abs(TimeNow.get(Calendar.HOUR));
	float TIME1_minute = Math.abs(TimeNow.get(Calendar.MINUTE));
	
	boolean condition = true;
	if (size != 0 && condition == true ) {
	// Calculate the system time
	Calendar Time = Calendar.getInstance();
	float TIME2_HOUR = Math.abs(Time.get(Calendar.HOUR));
	float TIME2_minute = Math.abs(Time.get(Calendar.MINUTE));
	
	if((TIME2_HOUR-TIME1_HOUR)<=2 &&
	(TIME2_minute- TIME1_minute)<=48){
	// this is the system time in MilliSeconds
	Calendar rightNow = Calendar.getInstance();
	float secondsRightNow = rightNow.get(Calendar.SECOND);
	job.enqueue((secondsRightNow + ExponentialNumber)); // here we put the arrival time in the queue.
	// I just let the program to sleep as it takes the time of the system, so give it the ability tochange.
	
	int sleep_time = (int) Unirorm_random(1,20); // it will be the sleeping time to generate different time
	try {
	TimeUnit.SECONDS.sleep(sleep_time);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	size--;
	}
	
	else
	condition = false;
	}
	// System.out.println("Total tasks arrival time in the queue " + arrival_time_in_the_queue);
	return job;
	}
	
	
	private static void PC1 (Queue jobs,int pc1, int
	c1 ){
	float arrival_time = jobs.dequeue();
	pc1 = 1;
	// Calculate the system time
	Calendar rightNow = Calendar.getInstance();
	float timeNow =
	Math.abs(rightNow.get(Calendar.SECOND));
	//Generate random service time
	float service_time = Unirorm_random(1,25);
	// Calculate the End time for this service
	float endTime1 = (timeNow + service_time + arrival_time);
	c1++;
	System.out.println("The service which has arrival time= " + arrival_time + " seconds\n it's processing with PC number " + 1);
	
	try {
	TimeUnit.SECONDS.sleep((int) endTime1);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	
	Calendar ended = Calendar.getInstance();
	float endedTime = Math.abs(rightNow.get(Calendar.SECOND));
	System.out.println("The which has arrival time= " + arrival_time +" second is ended at " + endedTime	+ " Seconds, And has taken " + Math.abs(endedTime -arrival_time) + " Seconds");
	pc1 = 0;
	}
	private static void PC2 (Queue jobs,int pc2, int
	c2 ){
	float arrival_time = jobs.dequeue();
	pc2 = 1;
	// Calculate the system time
	Calendar rightNow = Calendar.getInstance();
	float timeNow = Math.abs(rightNow.get(Calendar.SECOND));
	//Generate random service time
	float service_time = Unirorm_random(1, 25);
	// Calculate the End time for this service
	float endTime1 = (timeNow + service_time + arrival_time);
	c2++;
	System.out.println("The service which has arrival time= " + arrival_time + " seconds\nit's processing with PC number " + 2);
	
	try {
	TimeUnit.SECONDS.sleep((int) endTime1);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	
	Calendar ended = Calendar.getInstance();
	float endedTime = Math.abs(rightNow.get(Calendar.SECOND));
	System.out.println("The service which has arrival time= " + arrival_time +" second is ended at " +
	endedTime + " Seconds, And it has taken " + Math.abs(endedTime - arrival_time) + " Seconds");
	pc2 = 0;
	}
	}
	class Queue {
	float[] arr; // array to store queue elements
	int front; // front points to the from element in the queue
	int rear; // rear points to the last element in the queue
	int capacity; // maximum capacity of tasks in the queue
	int count; // current size of the queue
	// Constructor to initialize a queue
	Queue(int size) {
	arr = new float[size];
	capacity = size;
	front = 0;
	rear = -1;
	count = 0;
	}
	// Utility function to dequeue the front element
	public float dequeue() {
	// check for queue underflow
	if (isEmpty()) {
	try {
	TimeUnit.SECONDS.sleep(60);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	System.out.println("Program Terminated,... ");
	System.exit(-1);
	}
	float x = arr[front];
	// System.out.println("Removing " + x);
	front = (front + 1);
	count--;
	return x;
	}
	// Utility function to add an item to the queue
	public void enqueue(float item) {
	// check for queue overflow
	if (isFull()) {
	System.out.println("Overflow\n Program Terminated");
	System.exit(-1);
	}
	rear = (rear + 1);
	arr[rear] = item;
	count++;
	}
	// Utility function to return the front element of the queue
	public float peek() {
	if (isEmpty()) {
 System.out.println("Program Terminated, You just finished all the queries. ^_^ ");
	//System.exit(-1);
	}
	return arr[front];
	}
	// Utility function to return the size of the queue
	public int size() {
	return count;
	}
	// Utility function to check if the queue is empty or not
	public boolean isEmpty() {
	return (size() == 0);
	}
	// Utility function to check if the queue is full or not
	public boolean isFull() {
	return (size() == capacity);
	}
	}

