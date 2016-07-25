package ru.sbt.orlov

class PersonTest extends spock.lang.Specification {

    def "In order to marry the gender should be different"() {
        when:
        def male1 = new Person("male1",true);
        def male2 = new Person("male2",true);
        def female1 = new Person("female1",false)
        def female2 = new Person("female2",false);
        then:
        !male1.marry(male2)
        !male2.marry(male1)
        !female1.marry(female2)
        !female2.marry(female1)
        female1.marry(male2)
        male1.marry(female2)
    }

    def "Divorce test (true only if status changed)"() {
        when:
        def male1 = new Person("male1",true);
        def male2 = new Person("male2",true);
        def female1 = new Person("female1",false)
        def female2 = new Person("female2",false);
        male1.marry(female1);
        then: "When one of married calling divorce, other would be in divorce too (status wouldn't changed)"
        male1.divorce()
        !female1.divorce()
        !male2.divorce()
        then: "After cross marry two of them must be divorced"
        male1.marry(female1)
        male2.marry(female2)
        male1.marry(female2);
        !female1.divorce()
        !male2.divorce()
    }

    def "Some other features"() {
        expect:
        a.marry(b) == result

        where:
        a                      | b    | result
        new Person("a", false) | null | false
    }
}