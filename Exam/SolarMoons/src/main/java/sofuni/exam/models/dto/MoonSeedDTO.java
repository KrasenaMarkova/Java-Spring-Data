package sofuni.exam.models.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MoonSeedDTO {

    @XmlElement(name = "name")
    @Size(min = 2, max = 10)
    private String name;
    @XmlElement(name = "radius")
    @Positive
    private double radius;
    @XmlElement(name = "distance_from_planet")
    @Positive
    private int distanceFromPlanet;
    @XmlElement(name = "discovered")
    private String discovered;
    @XmlElement(name = "planet_id")
    private Long planet;
    @XmlElement(name = "discoverer_id")
    private Long discoverer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getDistanceFromPlanet() {
        return distanceFromPlanet;
    }

    public void setDistanceFromPlanet(int distanceFromPlanet) {
        this.distanceFromPlanet = distanceFromPlanet;
    }

    public String getDiscovered() {
        return discovered;
    }

    public void setDiscovered(String discovered) {
        this.discovered = discovered;
    }

    public Long getPlanet() {
        return planet;
    }

    public void setPlanet(Long planet) {
        this.planet = planet;
    }

    public Long getDiscoverer() {
        return discoverer;
    }

    public void setDiscoverer(Long discoverer) {
        this.discoverer = discoverer;
    }
}
