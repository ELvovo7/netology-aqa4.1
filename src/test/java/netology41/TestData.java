package netology41;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class TestData {

    public static class TestUser {
        public String city;
        public String fullName;
        public String phoneNumber;

        public TestUser(String city, String fullName, String phoneNumber) {
            this.city = city;
            this.fullName = fullName;
            this.phoneNumber = phoneNumber;
        }

        public String getCity() {
            return city;
        }

        public String getFullName() {
            return fullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

    }

    public static TestUser newValidUser () {
        Faker faker = new Faker(new Locale("ru"));
        return new TestUser(
                CityList.getRandomCity(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    public static TestUser newInvalidUser () {
        Faker faker = new Faker(new Locale("en"));
        return new TestUser(
                CityList.getRandomCity(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    public static TestUser newUserWithInvalidPhone () {
        Faker faker = new Faker(new Locale("ru"));
        return new TestUser(
                CityList.getRandomCity(),
                faker.name().fullName(),
                WrongPhoneNumList.getWrongPhoneNumber()
        );
    }

    public static String shiftMyDate(int days){
        LocalDate date = LocalDate.now().plusDays(days);
        return String.format("%02d.%02d.%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
}