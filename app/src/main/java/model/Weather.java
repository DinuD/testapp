package model;

/**
 * Created by DinuD-PC on 8/9/2017.
 */

public class Weather {

    public Place place = new Place();
    public String iconData;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
