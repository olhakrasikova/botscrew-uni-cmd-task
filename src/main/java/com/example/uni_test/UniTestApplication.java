package com.example.uni_test;

import com.example.uni_test.InputHandler.InputHandler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Scanner;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class UniTestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(UniTestApplication.class, args);
    }

    @Override
    public void run( ApplicationArguments args ) throws Exception
    {
        System.out.println("Hi, here are input options:\n" +
                "Who is head of department {department_name}\n" +
                "Show {department_name} statistics\n" +
                "Show the average salary for the department {department_name}\n" +
                "Show count of employee for {department_name}\n" +
                "Global search by {template}\n\n" +
                "If you want to end the program enter \"end\"\n\n" +
                "Please, enter your question:\n");
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.equals("end"))
                break;
            else
                inputHandler.handleInput(line);
        }
    }
}
