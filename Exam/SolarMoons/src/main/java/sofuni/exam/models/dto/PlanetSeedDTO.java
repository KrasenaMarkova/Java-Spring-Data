package sofuni.exam.models.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import sofuni.exam.models.enums.Type;

import java.math.BigInteger;

public class PlanetSeedDTO {

    @Size(min = 3, max = 20)
    private String name;

    private Type type;
    @Positive
    private int diameter;
    @Positive
    private double orbitalPeriod;
    @Positive
    private BigInteger distanceFromSun;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public BigInteger getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(BigInteger distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }
}
