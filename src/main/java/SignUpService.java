public class SignUpService {
    private final PasswordBlackList passwordBlackList;

    private final EmailValidator emailValidator;

    public SignUpService(PasswordBlackList passwordBlackList, EmailValidator emailValidator) {
        this.passwordBlackList = passwordBlackList;
        this.emailValidator = emailValidator;
    }

    public void signUp(String email, String password) {

        if (emailValidator.isValid(email)) {
            System.out.println("Email хороший");
        } else {
            System.err.println("Email не подходит");
        }

        if (passwordBlackList.contains(password)) {
            System.err.println("Пароль не подходит!");
        } else {
            System.out.println("Пароль хороший");
        }
    }

}
