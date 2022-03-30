import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "plane")
public class Plane extends Vehicle {

    private final static String TYPE = "PLANE";

    private Integer passengerCapacity ;

    public Plane() {
    }

    public Plane(String model, BigDecimal price, String fuelType, Integer passengerCapacity) {
        super(TYPE, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }


}