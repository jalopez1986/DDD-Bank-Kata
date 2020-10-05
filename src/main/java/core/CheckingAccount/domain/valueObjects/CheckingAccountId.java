package core.CheckingAccount.domain.valueObjects;

import core.shared.domain.valueObjects.IdValueObject;

import java.util.UUID;

public class CheckingAccountId extends IdValueObject {
    public CheckingAccountId(UUID value) {
        super(value);
    }


}
