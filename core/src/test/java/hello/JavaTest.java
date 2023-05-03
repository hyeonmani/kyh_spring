package hello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JavaTest {

    @Test
    @DisplayName("map을 테스트")
    void mapTest(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("apple", "Banna", "Melon", "grape", "Strawberry"));
//        System.out.println("list = " + list);

        System.out.println(list.stream().map(s -> s.toUpperCase()).collect(Collectors.joining(" | ")));
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.joining(" | ")));
        System.out.println(list.stream().map(s -> s.toLowerCase()).collect(Collectors.toList()));

        list.stream().map(s -> s.toLowerCase()).forEach(s -> System.out.println(s));
    }

    @Test
    @DisplayName("filter를 테스트")
    void filterTest(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("apple", "Banna", "Melon", "grape", "Strawberry"));

        System.out.println(list.stream().filter(t -> t.length() > 5).collect(Collectors.toList()));
    }

    @Test
    @DisplayName("sort를 테스트")
    void sortTest(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("apple", "Banna", "Melon", "grape", "Strawberry"));

        System.out.println(list.stream().map(s -> s.toUpperCase()).sorted().collect(Collectors.toList()));
    }
}
