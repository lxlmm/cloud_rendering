import com.info_system.service.NotifyZookeeperService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NotifyZookeeperServiceTest {

    @Test
    public void test()throws Exception{
        NotifyZookeeperService service=new NotifyZookeeperService();
        Map x=new HashMap();
        x.put("sd","dnk");
        service.notify("/watcher",x);
    }
}
