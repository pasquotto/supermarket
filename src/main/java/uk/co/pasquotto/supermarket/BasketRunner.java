package uk.co.pasquotto.supermarket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import uk.co.pasquotto.supermarket.service.CheckoutService;
import uk.co.pasquotto.supermarket.writer.Output;

@Controller
public class BasketRunner implements CommandLineRunner {

    private CheckoutService checkoutService;
    private Output output;

    public BasketRunner(CheckoutService checkoutService, Output output) {
        this.checkoutService = checkoutService;
        this.output = output;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (args.length < 1) throw new IllegalArgumentException("At least one product needs to be selected.");
            checkoutService.checkoutProducts(args);
        } catch (Exception e) {
            output.print("Error:\n" + e.getMessage() + "\n" + usage());
        }

    }

    private String usage() {
        return "Use:\njava -jar supermarket-0.0.1-SNAPSHOT.jar {list of products}\n";
    }
}
