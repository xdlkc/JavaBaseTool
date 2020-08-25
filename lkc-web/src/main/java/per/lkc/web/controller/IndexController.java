package per.lkc.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/1/11.
 *
 * @author zhangjunbo.
 */
@RestController
@RequestMapping("/")
@Slf4j
public class IndexController {

    @RequestMapping("/index")
    public void index() {
        try {
            throw new Exception("sdasd");
        } catch (Exception e) {
            log.error("sdasd",e);
        }
    }
}
