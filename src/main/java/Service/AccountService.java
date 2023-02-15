package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();

}
public AccountService(AccountDAO accountDAO){
    this.accountDAO = accountDAO;

}
public Account addAccount(Account account) {
    if(account.username != "" && account.password.length() >= 4){
    return accountDAO.CreateNewUsers(account);
    }
    return null;

}
public Account PostLogins(String username, String password){
    return accountDAO.ProcessUserLogins(username, password);
}
}