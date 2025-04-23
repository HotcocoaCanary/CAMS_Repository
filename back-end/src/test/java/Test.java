import org.example.back.service.ScoreService;
import org.example.back.util.ExcelOp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    @org.junit.Test
    public void ExcelOpTestInit(){
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    ScoreService service = app.getBean(ScoreService.class);
    service.initCourseSelection("testdata.xlsx");
    }

    @org.junit.Test
    public void ExcelOpTestScore(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ScoreService service = app.getBean(ScoreService.class);
        service.readScoreToDb("testdata.xlsx");
    }

    @org.junit.Test
    public void getids(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ScoreService service = app.getBean(ScoreService.class);
        System.out.println(service.getAllStudentIds());
    }
    
    @org.junit.Test
    public void initEvaluationResult(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ScoreService service = app.getBean(ScoreService.class);
        service.initEvaluationResult("大一上");
    }
    
    @org.junit.Test
    public void computeEvaluationResult(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ScoreService service = app.getBean(ScoreService.class);
        service.computeEvaluationResult("大一上");
    }
    
}
