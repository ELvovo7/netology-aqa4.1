package netology41;

import java.util.ArrayList;
import java.util.Random;

public class WrongPhoneNumList {

    public static String getWrongPhoneNumber() {
        ArrayList<String> phone = new ArrayList<>();
        phone.add("+7000123456");
        phone.add("+");
        phone.add("00000000000");
        phone.add("telefon");
        phone.add("<br>");

        Random random = new Random();
        return phone.get(random.nextInt(phone.size()));
    }
}