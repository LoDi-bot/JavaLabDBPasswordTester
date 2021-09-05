import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorRegexImpl implements EmailValidator {

    private String regex;

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String email) {
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }
}
