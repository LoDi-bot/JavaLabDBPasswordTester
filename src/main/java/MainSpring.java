import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSpring {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SignUpService signUpService = context.getBean(SignUpService.class);
        signUpService.signUp("asadullin.iskander@gmail.com", "123");

        System.out.println("Workflow has been finished!");
    }
}
