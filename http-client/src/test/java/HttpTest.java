import com.ylzinfo.ms.utils.HttpClientUtil;
import org.junit.Test;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 11:22
 **/
public class HttpTest {


    @Test
    public void testHttp() {
        String url = "http://baidu.com";
        String result = HttpClientUtil.doPost(url, "");
        System.out.println(result);
    }
}
