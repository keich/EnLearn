import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class MyWindowApp extends JFrame  implements KeyListener { //Наследуя от JFrame мы получаем всю функциональность окна. KeyListener для контроля нажатий клавиатуры
    /**
	 * h
	 */
	private static final long serialVersionUID = 1L;
	private MyArrayWords Words = new MyArrayWords(); //Массив слов
	private JTextField question_field = null; //Поле для отображение вопроса
	private JTextField answer_field = null;// Поле для ввода
	private boolean FirstAfterWrongAnswer = false; //Переменная true когда неверно набран ответ и false после начала набора ответа
	
	//События о нажании клавиатуры
	public void keyPressed(KeyEvent e) {
		
        int key = e.getKeyCode();
        //System.out.println("Key:"+key);

        
        if(FirstAfterWrongAnswer){
        	answer_field.setText("");
        	answer_field.setForeground(Color.GRAY);
        	FirstAfterWrongAnswer = false;
        }else{
            if(key == 10) {
        		try {
    				CheckAnswer();
    			} catch (Exception e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
            	return;
            }
        }

    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    //Конструктор
    public MyWindowApp() throws Exception {
        super("Learn Words"); //Заголовок окна
        
        setBounds(100, 100, 200, 200);  //Размеры окна
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
        
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Создали менеджер разметки
        
        question_field = new JTextField(20); //Поле для вопроса
        question_field.setHorizontalAlignment(JTextField.CENTER); //По центру
        question_field.setFont(new Font("SansSerif", Font.BOLD, 40)); //Шрифт для вопроса
        question_field.setForeground(Color.GRAY); //Цвет для текста
        question_field.setBackground(Color.WHITE); //Цвет для фона
        question_field.setEditable(false); //Редактировать нельзя
		add(question_field); //Добавим в менеджер разметки

		
		

        answer_field = new JTextField(1); //Поля для ответа
        answer_field.setHorizontalAlignment(JTextField.CENTER); //По центру
        answer_field.setFont(new Font("SansSerif", Font.BOLD, 40)); //Шрифт для ответа
        answer_field.setForeground(Color.GRAY);//Цвет для текста
        answer_field.setBackground(Color.WHITE);//Цвет для фона
		add(answer_field); //Добавим в менеджер разметки
		
		answer_field.addKeyListener(this);

		ShowQuestion(); //Загрузка первого вопроса
		pack();
		answer_field.requestFocus(); //Фокус на поле для ответа
		
		
		
    }
    
    //Показать вопрос
    private void ShowQuestion() throws Exception{
    	Words.selectActiveWord(); //Случайный вопрос
    	question_field.setText(Words.getActiveWordQuestion()); 
    	answer_field.setText("");
    }
    
    //Проверка ответа
    private void CheckAnswer() throws Exception{
    	if(Words.checkAnswer(answer_field.getText().trim())){
    		Words.sayActiveWords();
    		ShowQuestion();
    		FirstAfterWrongAnswer = false;
    	}else{
    		answer_field.setText(Words.getActiveWordAnswer());
    		answer_field.setCaretPosition(0);
    		answer_field.setForeground(Color.RED);
    		FirstAfterWrongAnswer = true;
    		Words.sayActiveWords();
    	}
    	
    }
    
    
    public static void main(String[] args) throws Exception { //эта функция может быть и в другом классе
        //System.out.println("Start");
        MyWindowApp app = new MyWindowApp(); //Создаем экземпляр нашего приложения
        app.setVisible(true); //С этого момента приложение запущено!
    }
}
