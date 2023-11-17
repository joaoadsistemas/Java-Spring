package org.example.services;

import org.example.entities.Employee;

public class SalaryService {

    private TaxService taxService;
    private PensionService pensionService;

    public SalaryService(TaxService taxService, PensionService pensionService) {
        this.taxService = taxService;
        this.pensionService = pensionService;
    }


    public double netSalary(Employee employee) {

         double netSalary = employee.getGrossSalary() - (
                taxService.taxService(employee.getGrossSalary()) + pensionService.discount(employee.getGrossSalary()));

         return netSalary;
    }

}
