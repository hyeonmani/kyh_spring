package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        // scan 경로 지정하지 않으면 @ComponentScan가 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        // 설정 클래스를 프로젝트 상단에 두는 것을 추천한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // Configuration 어노테이션이 붙은건 제외하고 스캔한다.
        // 예제에서 안전히 사용 위해
)
public class AutoAppConfig {
}
