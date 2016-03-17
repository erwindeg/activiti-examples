import org.springframework.stereotype.Component;

/**
 * Created by Erwin on 17/03/16.
 */
@Component
public class TestBean {

    public String test(String text){
        System.out.println("testbean variable:"+text);
        return "hello from testbean";
    }
}
