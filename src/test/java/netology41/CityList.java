package netology41;

import java.util.ArrayList;
import java.util.Random;

public class CityList {

    public static String getRandomCity() {
        ArrayList<String> city = new ArrayList<>();
        city.add("Санкт-Петербург");
        city.add("Москва");
        city.add("Волгоград");
        city.add("Грозный");
        city.add("Самара");
        city.add("Ростов-на-Дону");
        city.add("Краснодар");
        city.add("Владивосток");
        city.add("Новосибирск");

        Random random = new Random();
        return city.get(random.nextInt(city.size()));
    }
}