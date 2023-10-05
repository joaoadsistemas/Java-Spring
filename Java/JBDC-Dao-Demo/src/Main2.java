import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDAo();


        System.out.println("=== TEST 1: department findById ===");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println();


        System.out.println("=== TEST 3: department findAll ===");
        List<Department> list;
        list = departmentDao.findAll();
        for (Department obj: list
        ) {
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("=== TEST 4: department insert ===");
        Department newDepartment = new Department(null, "Logistic");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());


        System.out.println();

        System.out.println("=== TEST 5: department update ===");
        department = departmentDao.findById(1);
        department.setName("Program");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println();

        System.out.println("=== TEST 6: department delete ===");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");
        sc.close();


    }
}