import com.google.sparkproject.dao.DAOFactory;
import org.junit.Test;

import java.util.UUID;

public class DAOFactoryTest {

    @Test
    public void DAOFactorytest(){
        System.out.println(
                DAOFactory.getTaskDAO().findById(1));
    }

    @Test
    public void test() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }


}