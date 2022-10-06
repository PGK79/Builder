public class PersonBuilder {
    protected String name;
    protected String surname;
    protected int age;
    protected String address;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) throws IllegalArgumentException {
        if (age >= 0 && age < 100) {
            this.age = age;
            return this;
        } else {
            throw new IllegalArgumentException("Возраст указан не корректно");
        }
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() throws IllegalStateException {
        if(name != null|| surname != null){
            return new Person(name,surname,age,address);
        }else {
            throw new IllegalStateException("Вы указали не достаточное количество данных");
        }
    }
}