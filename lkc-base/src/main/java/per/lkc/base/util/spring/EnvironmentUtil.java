package per.lkc.base.util.spring;

import org.springframework.core.env.Environment;

public class EnvironmentUtil {

    private static Environment environment;

    /**
     * 通过key获取property
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        Environment env = getEnvironment();
        return env.getProperty(key);
    }

    /**
     * 获取env变量
     *
     * @return
     */
    private static Environment getEnvironment() {
        if (environment != null) {
            return environment;
        }
        environment = SpringContextUtil.getBean(Environment.class);
        if (environment == null) {
            throw new RuntimeException();
        }
        return environment;
    }
}
