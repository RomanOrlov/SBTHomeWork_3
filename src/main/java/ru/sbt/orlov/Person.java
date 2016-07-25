package ru.sbt.orlov;

public class Person {

    private final String name;

    private final boolean man;

    private Person spouse;

    public Person(String name, boolean man) {
        this.name = name;
        this.man = man;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) {
        if (person == null || man == person.isMan() || this.spouse == person) {
            return false;
        } else {
            if (spouse != null) {
                divorce();
            }
            this.spouse = person;
            person.marry(this);
            return true;
        }
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (spouse == null) {
            return false;
        } else {
            Person exSpouse = spouse;
            spouse = null;
            exSpouse.divorce();
            return true;
        }
    }

    public boolean isMan() {
        return man;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person name " + name + " (" + (man ? "male) " : "female) " + (spouse == null ? "single" : "with " + spouse.getName()));
    }
}