import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries_10 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        /*em.createQuery("UPDATE Employee e " +
                "SET e.salary = e.salary * 1.12 " +
                " WHERE e.department.name = 'Engineering' " +
                "OR e.department.name = 'Tool Design' " +
                "OR e.department.name = 'Marketing' " +
                "OR e.department.name = 'Information Services' ").executeUpdate();
              throws Exception  */

        List<Employee> resultList = em.createQuery("SELECT e FROM Employee e " +
                " WHERE e.department.name = 'Engineering' " +
                "OR e.department.name = 'Tool Design' " +
                "OR e.department.name = 'Marketing' " +
                "OR e.department.name = 'Information Services'", Employee.class).getResultList();

       for (Employee employee : resultList) {
            if (isValid(employee)) {
                BigDecimal salary = employee.getSalary().multiply(BigDecimal.valueOf(1.12));
                employee.setSalary(salary);
                em.persist(employee);
                System.out.printf(""+employee.getFirstName() + " " + employee.getLastName() + " ($%.2f)%n" , employee.getSalary());
            }
        }
        em.getTransaction().commit();

    }

    private static boolean isValid(Employee employee) {
        return employee.getDepartment().getName().equals("Engineering") || employee.getDepartment().getName().equals("Tool Design")
                || employee.getDepartment().getName().equals("Marketing") || employee.getDepartment().getName().equals("Information Services");
    }
}
