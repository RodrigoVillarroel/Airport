package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.MessageFormat;

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
        return MessageFormat.format("Person'{'name=''{0}'', surname=''{1}'', age={2}, numberIdentify={3}'}'", getName(), getSurname(), getAge(), getNumberIdentify());
    }
}