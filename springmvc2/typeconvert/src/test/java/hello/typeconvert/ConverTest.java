package hello.typeconvert;

import hello.typeconvert.convert.IpPortToStringConvert;
import hello.typeconvert.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverTest {

    @Test
    void stringToIpport(){
        IpPortToStringConvert converter = new IpPortToStringConvert();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");

    }
}
