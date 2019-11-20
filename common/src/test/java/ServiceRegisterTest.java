import com.crown.servicecommon.register.ServiceRegister;
import com.crown.servicecommon.register.ServiceRegisterImpl;
import org.junit.Test;

public class ServiceRegisterTest {

    @Test
    public void Test(){
        ServiceRegister serviceRegister = new ServiceRegisterImpl();
        serviceRegister.register("com.crown.user.UserService","192.168.10.14:88021","provider");
    }
}
