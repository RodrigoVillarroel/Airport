package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.MessageFormat;
import java.util.Objects;

public class Person {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("numberIdentify")
    private Integer numberIdentify;

    public Person () {

    }

    public Person(String name, String surname, Integer age, Integer numberIdentify) {
        setName(name);
        setSurname(surname);
        setAge(age);
        setNumberIdentify(numberIdentify);
    }

    // region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumberIdentify() {
        return numberIdentify;
    }

    public void setNumberIdentify(Integer numberIdentify) {
        this.numberIdentify = numberIdentify;
    }
    // endregion

    @Override
    public String toString() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        return "Nombre: " + getName() +" - " + " Apellido: " + getSurname();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberIdentify);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Person)) return false;
        Person person = (Person) obj;
        return Objects.equals(person.getNumberIdentify(), this.getNumberIdentify());
    }
}