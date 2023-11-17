package org.example;

import org.example.entities.Employee;
import org.example.services.PensionService;
import org.example.services.SalaryService;
import org.example.services.TaxService;

public class Main {
    public static void main(String[] args) {

        Employee employee = new Employee("Joao", 4000.0);
        SalaryService service = new SalaryService(new TaxService(), new PensionService());
        System.out.println(service.netSalary(employee));


    }
}