import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment_05 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Employee> resultList = em.createQuery("SELECT e FROM Employee e " +
                " WHERE e.department.name = 'Research and Development' " +
                " ORDER BY e.salary ASC," +
                " e.id ASC", Employee.class).getResultList();

        for (Employee employee : resultList) {
            System.out.printf(employee.getFirstName() + " " + employee.getLastName() +
                    " from" + " Research and Development " + "- %.2f%n",
                    employee.getSalary());
        }
        em.getTransaction().commit();
    }
}
