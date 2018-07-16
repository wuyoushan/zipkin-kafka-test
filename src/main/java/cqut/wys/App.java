package cqut.wys;

import brave.Span;
import cqut.wys.biz.ZipkinCollectSpanV2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.lang.Thread.sleep;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

    public static void main( String[] args ) throws InterruptedException {
        SpringApplication.run(App.class,args);
        System.out.println( "Hello World!" );
    }

    @Bean
    public ApplicationRunner runner(ZipkinCollectSpanV2 zipkinCollectSpanV2) throws InterruptedException {
        while (true) {
            Span span = zipkinCollectSpanV2.startSpan("zipkin-kafka-test", "test");
            System.out.println("start test");
            sleep(5000);
            zipkinCollectSpanV2.endSpan(span,"request app","localhost");
        }
    }
}
