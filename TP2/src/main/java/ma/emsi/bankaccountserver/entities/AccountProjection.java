package ma.emsi.bankaccountserver.entities;

import ma.emsi.bankaccountserver.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();

}
