import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class MyArrayWords {
	final Random random = new Random();
	private MyWords Words[] = null;
	private int NumWordsShows = 10;
	private MyWords WordNow = null; //Переменная с активным словом
	private boolean FirstAttempForActiveWord = true;
	
	public MyArrayWords() {
		try {
			LoadWords("./w");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Загрузка слов из файла определенного формата
	private void LoadWords(String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		ArrayList<MyWords> data = new ArrayList<>();
		while ((line = br.readLine()) != null) {
		System.out.println("Debug"+line+"@"+line.length());	
			if(line.length()!=0){ 
				int start = line.indexOf(" ");
				String question = line.substring(start);
				String answer = line.substring(0,start);
				MyWords word = new MyWords(answer.trim(),question.trim());
				data.add(word);
			}
		}
		br.close();
		if(!data.isEmpty()){
			Words = new MyWords[data.size()];
			for(int i=0;i<data.size();i++){
				Words[i] = data.get(i);
			}
		}else{
			Words = null;
		}
	}
	public void selectActiveWord() throws Exception{
		NumWordsShows++;
		System.out.println("NumWordsShows = "+NumWordsShows);
		if(Words == null){
			throw new Exception("No word load");
		}
		
		for(int a =0;a<Words.length;a++){
			System.out.println("In Word "+	Words[a].Answer+" Rate: "+Words[a].Rate+" LastShow: "+Words[a].LastTimeShow+" addr "+Words[a]);
		}
		
		
		int maxRate = 0;
		//Поиск максимального Rate
		for(int a =0;a<Words.length;a++){
			if(Words[a].Rate > maxRate){
				maxRate = Words[a].Rate;
			}
		}
		//System.out.println("Count max = "+maxRate);

		
		//Выбор числа слов из которых будет в лучайном пороядке выбираться слово
		int NumWordForTmpArray = 10;
		if(Words.length<11){
			NumWordForTmpArray = Words.length;
		}
		
		//Минимальное число для найденных слов
		int NumMinWordForTmpArray = 4;
		if(Words.length<5){
			NumMinWordForTmpArray = Words.length;
		}
		
		//Фильтр для слов которые недавно показывались
		int LastTimeShowEage = 3;
		
		MyWords searchWords[] = new MyWords[NumWordForTmpArray]; //Массив для первых NumWordForTmpArray слов с наибольшим Rate
		
		int found_words=0;
		
		//Поиск списка слов из массива которые плохо выучены
		while(true){ //Иногда мало слов находяться при maxRate. В этом цикле будем уменьшать его что бы получить больше слов
			found_words=0;
			//Если попалсиь только слова которые недавно показывались, но все слова выучены
			if(maxRate == 0){
				LastTimeShowEage = LastTimeShowEage -1;
			}
			//Выставляем maxRate чуть меньше. Все слова больше этого maxRate будут отобраны.
			if(maxRate > 2){
				maxRate = maxRate - 2;
			}else{
				maxRate = 0;
			}
			
			for(int a =0;a<Words.length;a++){
				if(Words[a].Rate >= maxRate){
					if(Words[a].LastTimeShow < NumWordsShows-LastTimeShowEage){
						searchWords[found_words] = Words[a];
						found_words++;
					}
				}
				//System.out.println("Debug found_words="+found_words+" a="+a +" Name="+Words[a].Answer +" NumWordForTmpArray="+NumWordForTmpArray);
				if(found_words>=NumWordForTmpArray){
					break;
				}
	
			}
			
			if(found_words>=NumMinWordForTmpArray)
				break;
			//System.out.println("Debug 1 found_words="+found_words+" maxRate="+maxRate);
		}
		
		//System.out.println("Found "+found_words+" words for random select");
		

		//for(int a =0;a<found_words;a++){
		//	System.out.println("out Word "+	searchWords[a].Answer+" Rate: "+searchWords[a].Rate+" LastShow: "+searchWords[a].LastTimeShow);
		//}
		int a = random.nextInt(found_words);
		searchWords[a].LastTimeShow = NumWordsShows;
		//Create audio file. This check if file exists.
		searchWords[a].createAudioFile();
		WordNow = searchWords[a];
		FirstAttempForActiveWord = true;
		
	}
	public void sayActiveWords(){
		WordNow.PlayAudio();
	}
	
	
	public String getActiveWordAnswer(){
		return WordNow.Answer;
	}

	public String getActiveWordQuestion(){
		return WordNow.Question;
	}
	
	public boolean checkAnswer(String ans){
		boolean res = false;
    	if(ans.compareTo(WordNow.Answer)==0){
    		res = true;
    		System.out.println("answer true");
    	}
    	
    	if(FirstAttempForActiveWord){
    		FirstAttempForActiveWord = false;
    		if(res){
	    		WordNow.Rate--;
	    		if(WordNow.Rate<0){
	    			WordNow.Rate=0;
	    		}
    		}else{
	    		WordNow.Rate++;
    		}
    	}
    	return res;
	}


	private class MyWords {
		private String Answer;
		private String Question;
		//private int State = 0;;
		private int LastTimeShow = 0;
		private int Rate = 5;
		private String audiofilename = "";
		public MyWords(String answer, String question) {
			super();
			Answer = answer;
			Question = question;
		}
		public void PlayAudio(){
			System.out.println("Play audio file: "+audiofilename);
			try {
				MyAudio audio = new MyAudio();
				audio.Play(audiofilename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void createAudioFile(){
			if( audiofilename == "" ) {
				audiofilename = Answer.hashCode()+".wav";
				try {
					MyAudio audio = new MyAudio();
					audio.CreateAudio(Answer, audiofilename);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}


}








