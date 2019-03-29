package jdbc.dao;

/**
 * description：
 *
 * @author ajie
 * data 2018/8/13 12:50
 */
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = -1770510730768385681L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
