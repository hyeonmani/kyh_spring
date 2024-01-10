package hello.advenced;

import hello.advenced.trace.logTrace.FieldLogTrace;
import hello.advenced.trace.logTrace.LogTrace;
import hello.advenced.trace.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
