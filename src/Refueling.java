import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Refueling {
    private String fuelName;
    private Double fuelPrice;
    private Double fuelAmount;
    private LocalDate refuellingDate;

    public Refueling() {
    }

    public Refueling(String information) {

        super();

        var refuelingList = new ArrayList<String>();
        var st = new StringTokenizer(information, "|");

        while (st.hasMoreTokens()) {
            refuelingList.add(st.nextToken());
        }

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        this.setFuelName(refuelingList.get(0));
        this.setFuelPrice(Double.parseDouble(refuelingList.get(1).replaceAll(",", ".")));
        this.setFuelAmount(Double.parseDouble(refuelingList.get(2).replaceAll(",", ".")));
        this.setRefuellingDate(LocalDate.parse(refuelingList.get(3), dateTimeFormatter));
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName.toUpperCase();
    }

    public String getFuelName() {
        return this.fuelName;
    }

    public void setFuelPrice(Double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public Double getFuelPrice() {
        return this.fuelPrice;
    }

    public void setFuelAmount(Double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public Double getFuelAmount() {
        return this.fuelAmount;
    }

    public void setRefuellingDate(LocalDate refuellingDate) {
        this.refuellingDate = refuellingDate;
    }

    public LocalDate getRefuellingDate() {
        return this.refuellingDate;
    }
}
