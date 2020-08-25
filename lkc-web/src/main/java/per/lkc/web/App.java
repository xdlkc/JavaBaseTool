package per.lkc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/18.
 *
 * @author zhangjunbo.
 */
@SpringBootApplication(scanBasePackages = "per.lkc.web")
public class App {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }
}
