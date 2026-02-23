package com.example.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class Myclass {

    @GetMapping("hello")
    public  String sayhello(){
        return "Hello Harshad !!";
    }

    @RestController
    @RequestMapping("/api")
    public static class MessageController {

        @PostMapping("/message")
        public String receiveMessage(@RequestBody Blog message) {

           System.out.print(message.getTitle());
           System.out.println(" By :- "+message.getUser());
            String content = message.getContent();
            content=content.replace(".", ".\n");
            System.out.println(content);

            return "Received";
        }
    }
}
