package hello.typeconvert.formatter;

import hello.typeconvert.convert.IpPortToStringConvert;
import hello.typeconvert.convert.StringToIpPortConvert;
import hello.typeconvert.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        // 컨버터 등록
        conversionService.addConverter(new StringToIpPortConvert());
        conversionService.addConverter(new IpPortToStringConvert());

        // 포멧터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        // 컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8090", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8090));

        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000 );

    }
}
