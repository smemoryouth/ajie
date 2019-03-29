package jdbc.classtest.dao;

/**
 * description：dao层异常处理
 *
 * @author ajie
 * data 2018/10/23 17:48
 */
public class UserDaoException extends RuntimeException {

    private static final long serialVersionUID = -4387798912613237638L;

    public UserDaoException() {
    }

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDaoException(Throwable cause) {
        super(cause);
    }

    public UserDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
