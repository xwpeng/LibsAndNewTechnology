package method_reference;

import java.time.LocalDate;

public class Personn
{
    public Personn(String name, LocalDate birthday)
    {
        this.name = name;
        this.birthday = birthday;
    }

    String name;
    LocalDate birthday;

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public static int compareByAge(Personn a, Personn b)
    {
        return a.birthday.compareTo(b.birthday);
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}