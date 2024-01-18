import java.time.LocalDate;
import java.util.Objects;

public class Account {

    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthday;
    private double balance;
    private String gender;

    public Account() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Account(String firstName, String lastName, String country, LocalDate birthday, double balance, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthday = birthday;
        this.balance = balance;
        this.gender = gender;
    }

    public static class Builder {

//        private final String firstName;
//        private String lastName;
//        private String country;
//        private LocalDate birthday;
//        private double balance;
//        private String gender;
//
//        public Builder(String firstName){
//            this.firstName = firstName;
//        }
//
//        public Builder lastName(String lastName){
//            this.lastName = lastName;
//            return this;
//        }
//
//        public Builder country(String country){
//            this.country = country;
//            return this;
//        }
//
//        public Builder birthday(LocalDate birthday){
//            this.birthday = birthday;
//            return this;
//        }
//
//        public Builder balance(double balance){
//            this.balance = balance;
//            return this;
//        }
//        public Builder gender(String gender){
//            this.gender = gender;
//            return this;
//        }
//
//        public Account build(){
//            return new Account(firstName, lastName, country, birthday, balance, gender);
//        }

        private final Account account = new Account();

        public Builder() {
        }

        public Builder firstName(String firstName) {
            account.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            account.lastName = lastName;
            return this;
        }

        public Builder country(String country) {
            account.country = country;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            account.birthday = birthday;
            return this;
        }

        public Builder balance(double balance) {
            account.balance = balance;
            return this;
        }

        public Builder gender(String gender) {
            account.gender = gender;
            return this;
        }

        public Account build() {
            return account;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && firstName.equals(account.firstName) && lastName.equals(account.lastName) && country.equals(account.country) && birthday.equals(account.birthday) && gender.equals(account.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, country, birthday, balance, gender);
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                ", gender='" + gender + '\'' +
                '}';
    }
}