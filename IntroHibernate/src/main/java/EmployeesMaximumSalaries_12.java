import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeesMaximumSalaries_12 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<BigDecimal> maxSalaries = em.createQuery("SELECT MAX(e.salary)  FROM Employee e " +
                "GROUP BY e.department ORDER BY e.department.id asc", BigDecimal.class).getResultList();

        List<String> namesOfDepartments = em.createQuery("SELECT d.name  FROM Department d ORDER BY d.id asc", String.class).getResultList();

        Map<String, BigDecimal> departmentNameAndSalary = new LinkedHashMap<>();

        maxSalaries = maxSalaries
                .stream()
                .filter(f-> isInRange(f))
                .collect(Collectors.toList());


        for (int index = 0; index < maxSalaries.size(); index++) {
            departmentNameAndSalary.put(namesOfDepartments.get(index), maxSalaries.get(index));
        }

        departmentNameAndSalary
                .entrySet()
                .forEach(a -> System.out.println(a.getKey() + " " + a.getValue()));

        em.getTransaction().commit();

    }
    private static boolean isInRange(BigDecimal f) {
        return f.compareTo(BigDecimal.valueOf(30000)) < 0 ||
                f.compareTo(BigDecimal.valueOf(70000)) > 0;
    }
}
