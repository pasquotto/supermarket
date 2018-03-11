package uk.co.pasquotto.supermarket.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import uk.co.pasquotto.supermarket.model.Basket;
import uk.co.pasquotto.supermarket.service.BasketCalculatorService;


@Service
public class BasketCalculatorServiceImpl implements BasketCalculatorService {

    private KieContainer kContainer;

    public BasketCalculatorServiceImpl(KieContainer kContainer) {
        this.kContainer = kContainer;
    }

    @Override
    public void calculateDiscounts(Basket basket) {
        KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(basket);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
