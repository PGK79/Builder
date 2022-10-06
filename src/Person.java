import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person(String name, String surname, int age, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public boolean hasAge() {
        if (getAge().getAsInt() != 0) { // использовал по принципу "не пропадать же методу"
            return true;                // хотел писать age != 0. Все равно не бросит NoSuchElementException.
        } else {                        // значение есть всегда, даже если нет возраста (значение 0)
            return false;               // isPresent() за счет значения 0 не работает
        }
    }

    public boolean hasAddress() {
        if (!address.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {   // возвращаемое значение в методе увидел в Реализация
        return OptionalInt.of(age); // практической ценности не понял. Интовое поле age даже без значения
                                    // инициализируется по умолчанию (т.е. 0).
    }                               // Но 0 это тоже возраст (до года возраст 0 лет, а мы пишем в годах)
                                    // В контейнере будет значение 0. Получить значение возраста можно
                                    // через getAge только с дополнительным методом.
                                    // Поэтому не ясно зачем возвращать контейнер в котором всегда есть значение
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (hasAge()) {
            age++;
        }
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder builder = new PersonBuilder();
        builder.surname = getSurname();
        builder.address = getAddress();
        return builder;
    }

    @Override
    public String toString() {
        if (!hasAge() && hasAddress()) {
            return surname + " " + name + " " + "(живет в " + address + ")";
        } else if (!hasAddress() && hasAge()) {
            return surname + " " + name + " " + age + " года ";
        } else {
            return surname + " " + name + " " + age + " года " + "(живет в " + address + ")";
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }
}
