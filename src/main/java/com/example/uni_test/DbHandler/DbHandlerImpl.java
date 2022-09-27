package com.example.uni_test.DbHandler;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DbHandlerImpl {


    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "admin";
    public static final String PASSWORD = "admin";


    public  String findHeadOfDepartment(String input){
        String query = "SELECT head_of_department_name FROM \"departments\" WHERE department_name='" + input + "'";
        ArrayList<String> result = runQuery(query);
        if(!result.isEmpty())
            return result.get(0);
        else return null;
    }

    public static ArrayList<String> getStatistics(String department){
        String query = "SELECT d.department_name, " +
                "(SELECT COUNT(*)AS assistants FROM lectors WHERE degree = 'assistant'), " +
                "(SELECT COUNT(*)AS \"associate professors\" FROM lectors WHERE degree = 'associate professor'), " +
                "(SELECT COUNT(*)AS professors FROM lectors WHERE degree = 'professor') " +
                "FROM " +
                "\"lectorsOnDepartments\" " +
                "inner join departments d on \"lectorsOnDepartments\".departments_id = d.id " +
                "where d.department_name = '"+ department +
                "' group by d.department_name;";
        ArrayList<String> result = runQuery(query);
        if(!result.isEmpty())
            return result;
        else return null;

    }

    public static String getEmployeeCount(String department){
        String query =
                "select COUNT(\"lectorsOnDepartments\".lectors_id), departments.department_name from \"lectorsOnDepartments\" inner join departments on" +
                        "    \"lectorsOnDepartments\".departments_id = departments.id where department_name = '"+ department +"' group by departments.department_name";
        ArrayList<String> result = runQuery(query);
        if(!result.isEmpty())
            return result.get(0);
        else return null;

    }

    public static String getAverageSalaryForDepartment(String department){
        String query =
                "select AVG(lectors.salary) as salary, d.department_name from lectors " +
                        "inner join \"lectorsOnDepartments\" lOD on lectors.id = lOD.lectors_id " +
                        "inner join departments d on d.id = lOD.departments_id " +
                        "where department_name = '" + department +
                        "' group by d.department_name";
        ArrayList<String> result = runQuery(query);
        if(!result.isEmpty())
            return result.get(0);
        else return null;

    }

    public static ArrayList<String> globalSearch(String searchWord){
        String query = "Select name from lectors where lectors.name like '%"+ searchWord +"%'";
        ArrayList<String> result = runQuery(query);
        if(!result.isEmpty())
            return result;
        else return null;

    }

    private static ArrayList<String> runQuery(String query){
        ArrayList<String> valuesList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()){
                    for (int i = 1; i <= result.getMetaData().getColumnCount();  i++){
                            valuesList.add(result.getString(i));
                        }
                    }
                    return valuesList;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("There is an issue..Try again", e);
        }
    }
}
