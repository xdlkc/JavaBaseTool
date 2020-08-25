package per.lkc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.lkc.web.filter.AFilter;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/26.
 *
 * @author zhangjunbo.
 */
@Configuration
public class BeanConfig {
    // @Bean
    // public RestHighLevelClient restHighLevelClient() {
    //     RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
    //             new HttpHost("localhost", 9201, "http")
    //     ));
    //     return client;
    // }

    // @Bean("aFilter")
    // public AFilter aFilter() {
    //     AFilter aFilter = new AFilter();
    //     return aFilter;
    // }
}
