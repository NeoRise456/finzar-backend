package pe.edu.upc.smartfinance.finzar.wallets.application.internal.outboundservice;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.iam.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.iam.interfaces.acl.IamContextFacade;

import java.util.Optional;

@Service
public class ExternalIamService {

    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<User> fetchUserById(Long UserId) {
        return iamContextFacade.fetchUserById(UserId);
    }
}
