package hello.typeconvert;


import hello.typeconvert.convert.IntegerToStringConvert;
import hello.typeconvert.convert.IpPortToStringConvert;
import hello.typeconvert.convert.StringToIntegerConverter;
import hello.typeconvert.convert.StringToIpPortConvert;
import hello.typeconvert.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 우선순위 때문에 주석처리 : convert -> formatter
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConvert());
        registry.addConverter(new StringToIpPortConvert());
        registry.addConverter(new IpPortToStringConvert());

        // 추가
        registry.addFormatter(new MyNumberFormatter());
    }


}
