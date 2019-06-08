import com.google.sparkproject.conf.ConfigurationManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationManagerTest {

    @Test
    public void test(){
        int size = ConfigurationManager.getInteger("jdbc.datasource.size");
        System.out.println(size);
    }


}