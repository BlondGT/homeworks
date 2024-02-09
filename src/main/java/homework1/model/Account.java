package homework1.model;

import java.time.LocalDate;
import java.util.Objects;

public class Account {

    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthday;
    private double balance;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String gender;

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

    public Account(Long id, String firstName, String lastName, String country, LocalDate birthday, double balance, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.birthday = birthday;
        this.balance = balance;
        this.gender = gender;
    }

    public static class Builder {

        private Long id;
        private String firstName;
        private String lastName;
        private String country;
        private LocalDate birthday;
        private double balance;
        private String gender;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Account build() {
            return new Account(id, firstName, lastName, country, birthday, balance, gender);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && id.equals(account.id) && firstName.equals(account.firstName) && lastName.equals(account.lastName) && country.equals(account.country) && birthday.equals(account.birthday) && gender.equals(account.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, country, birthday, balance, gender);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                ", gender='" + gender + '\'' +
                '}';
    }
}