package mangaba.com.br.mangabatag.controller;

import mangaba.com.br.mangabatag.models.User;

/**
 * Created by GuilhermeLucena on 17/11/2014.
 */
public interface UserReceiver {
    public void onUserReceived(User user);
}
