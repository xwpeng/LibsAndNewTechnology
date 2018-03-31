package method_reference;

public class ComparisonProvider {
    public int compareByName(Personn a, Personn b)
    {
        return a.getName().compareTo(b.getName());
    }

    public int compareByAge(Personn a, Personn b)
    {
        return a.getBirthday().compareTo(b.getBirthday());
    }

}
