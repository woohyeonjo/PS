package Chapter_12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class E16ExcuteExample {
	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for(int i = 0 ; i < 10 ; i ++){
			Runnable runnable = new Runnable(){
			@Override
			public void run(){
				ThreadPoolExecutor threadPoolExcutor =
						(ThreadPoolExecutor)executorService;
				int poolSize = threadPoolExcutor.getPoolSize();
				String threadName = Thread.currentThread().getName();
				System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름 : " + threadName);
				int value = Integer.parseInt("삼");
			}
			};
			
			//executorService.execute(runnable);
			executorService.submit(runnable);
			
			Thread.sleep(10);
		}
		
		executorService.shutdown();
	}
}
