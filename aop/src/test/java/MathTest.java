import com.aop.Math;
import com.aop.Mathimp;
import com.prox.Prox;
import org.junit.Test;

public class MathTest {
    @Test
    public void test(){
        Math mathimp = new Mathimp();
        Math getprox = Prox.getprox(mathimp);
        getprox.add(1,2);
        getprox.cheng(1,2);
    }
}
