package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void respondBodyV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> respondBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String respondBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> respondBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * HttpStatus를 동적으로 쓰고싶으면 ResponseEntity를 사용한다.
     * @ResponseStatus는 고정해둬서 동적으로 설정할 수가 없다.
     * @return
     */

    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData respondBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }

}
