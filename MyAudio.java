import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyAudio {
	
	public void Play(String audiofilename){
		String arg = "aplay /tmp/"+audiofilename;
        try {
            Process process = new ProcessBuilder(
					"/bin/sh","-c",arg).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				  System.out.println(line);
			}
        } catch (IOException e) {
            e.printStackTrace();  
        }
	}
	public void CreateAudio(String Answer,String audiofilename){
		String arg = "echo \""+Answer+"\" | text2wave -o /tmp/"+audiofilename;
		System.out.println("Create audio file: "+audiofilename+" procces arg: "+arg);
		MyRunCMD runCMD = new MyRunCMD(arg);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(runCMD);
	}
	
	private class MyRunCMD implements Runnable {
		private String arg = "";
		public MyRunCMD(String arg) {
			super();
			this.arg = arg;
		}
		@Override
		public void run() {
	        try {
	            Process process = new ProcessBuilder(
						"/bin/sh","-c",arg).start();
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					  System.out.println(line);
				}
	        } catch (IOException e) {
	            e.printStackTrace();  
	        }
		}

	}
}
