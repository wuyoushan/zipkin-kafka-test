package cqut.wys.biz;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkin2.Endpoint;

/**
 * --------神兽出没--------/
 *   ┌─┐     ┌─┐
 * ┌─┘─┴─────┘─┴─┐
 * │      ─      │
 * │  ┬─┘   └─┬  │
 * │             │
 * │      ┴      │  Code is far away from bug with the animal protecting
 * └───┐      ┌──┘  神兽保佑,代码无bug
 *     │      │
 *     │      └──────┐
 *     │             ├┐
 *     │             ┌┘
 *     └┐ ┐ ┌───┬─┐ ┌┘
 *      │ ┤ ┤    │ ┤ ┤
 *      └─┴─┘    └─┴─┘
 * --------感觉萌萌哒--------/
 *
 * zipkin收集类 v2数据模型
 *
 * @author wuyoushan
 * @date 2018/7/6
 */
@Component
public class ZipkinCollectSpanV2 {

    private static final Logger logger = LoggerFactory.getLogger(ZipkinCollectSpanV2.class);

    @Autowired
    private KafkaReporterSpanV2 kafkaReporter;

    /**
     * 请求开始,记录Span
     *
     * @param appName 应用名
     * @param reuquestMethodName 请求方法
     * @return Span
     */
    public Span startSpan(String appName,String reuquestMethodName){

        Tracing tracing = Tracing.newBuilder()
                .localServiceName("zipkin-kafka-test")
//                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
//                .currentTraceContext(ThreadContextCurrentTraceContext.create())
                .spanReporter(kafkaReporter)
                .build();

        Tracer tracer = tracing.tracer();
        Span span = tracer.newTrace()
                .name(reuquestMethodName)
                .annotate("hahaha")
                .tag("ss","22222")
                .start();

        return span;
    }


    /**
     * 请求结束
     *
     * @param span Span
     * @param appName 应用名
     * @param ipAddress 远程ip
     * @return Span
     */
    public void endSpan(Span span, String appName, String ipAddress) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                span.remoteEndpoint(
                        Endpoint.newBuilder().serviceName(appName)
                                .ip(ipAddress)
                                .build())
                        .tag("qq", "wwwww");
                System.out.println("sss");
                try {
                    span.finish();
                } catch (Exception e) {
                    logger.error("添加zipkin log出错:" + e.getMessage(), e);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
