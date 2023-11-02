package hello.typeconvert.convert;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        // 등록
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new StringToIntegerConverter());
        defaultConversionService.addConverter(new IntegerToStringConvert());
        defaultConversionService.addConverter(new StringToIpPortConvert());
        defaultConversionService.addConverter(new IpPortToStringConvert());

        // 사용
        Integer result = defaultConversionService.convert("10", Integer.class);
        System.out.println("result = " + result);
    }
}
