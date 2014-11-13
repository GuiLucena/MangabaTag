package mangaba.com.br.mangabatag.controller;

/**
 * Created by GuilhermeLucena on 25/10/2014.
 */
public class InvalidPasswordExeption extends Exception {
    public InvalidPasswordExeption(String detailMessage) {
        super(detailMessage);
    }
}
