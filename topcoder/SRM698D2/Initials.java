public class Initials
{
    private static final String DELIMITER = " ";

    public String getInitials(String name) {
        String[] tokens = name.split(DELIMITER);
        StringBuilder acronym = new StringBuilder();
        for (String token : tokens) {
            acronym.append(token.charAt(0));
        }
        return acronym.toString();
    }
}
