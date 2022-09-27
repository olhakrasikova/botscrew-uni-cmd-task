package com.example.uni_test.InputHandler;

import com.example.uni_test.DbHandler.DbHandlerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InputHandler {

    private DbHandlerImpl dbHandler = new DbHandlerImpl();

        private static final String WHO_IS_HEAD_OF_DEPARTMENT = "Who is head of department ";
        private static final String SHOW = "Show ";
        private static final String STATISTICS = " statistics";
        private static final String AVERAGE_SALARY  = "the average salary for the department ";
        private static final String EMPLOYEE_COUNT = "count of employee for ";
        private static final String GLOBAL_SEARCH = "Global search by ";


        public  void handleInput(String input){
            if(input.contains(WHO_IS_HEAD_OF_DEPARTMENT)){
                String departmentName = input.replace(WHO_IS_HEAD_OF_DEPARTMENT, "");
                System.out.println("Head of " + departmentName + " department is " + dbHandler.findHeadOfDepartment(departmentName));

            }
            else if (input.contains(SHOW)){
                input = input.replace(SHOW, "");
                if(input.contains(STATISTICS)){
                    input = input.replace(STATISTICS, "");
                    ArrayList<String> statistics = DbHandlerImpl.getStatistics(input);
                    if(statistics != null)
                        System.out.println("assistants - " + statistics.get(1) +
                                        "\nassociate professors - " + statistics.get(2)+
                                        "\nprofessors - " + statistics.get(3));
                    else System.out.println("Nothing was found");
                }
                else if(input.contains(EMPLOYEE_COUNT)){
                    input = input.replace(EMPLOYEE_COUNT, "");
                    System.out.println(DbHandlerImpl.getEmployeeCount(input));
                }
                else if(input.contains(AVERAGE_SALARY)){
                    String departmentName = input.replace(AVERAGE_SALARY, "");
                    System.out.println("The average salary of " + departmentName+ " is " + DbHandlerImpl.getAverageSalaryForDepartment(departmentName));
                }
            }
            else if(input.contains(GLOBAL_SEARCH)){
                input = input.replace(GLOBAL_SEARCH, "");
                System.out.println(DbHandlerImpl.globalSearch(input));
            }
        }

    }


