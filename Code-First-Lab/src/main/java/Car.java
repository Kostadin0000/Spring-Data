import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "car")
public class Car extends Vehicle {

    private final static String TYPE = "CAR";

    private Integer seats;

    public Car() {}

    public Car(String model, BigDecimal price, String fuelType, Integer seats) {
        super(TYPE, model, price, fuelType);
        this.seats = seats;
    }


}
