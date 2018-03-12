package uk.co.pasquotto.supermarket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.pasquotto.supermarket.service.CheckoutService;
import uk.co.pasquotto.supermarket.writer.Output;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasketRunnerTest {

    @Mock
    private Output output;

    @Mock
    private CheckoutService checkoutService;


    @InjectMocks
    private BasketRunner underTest;

    @Test
    public void testRunWithProducts() throws Exception {
        String[] products = {"A", "B", "A", "C", "A", "D"};
        underTest.run(products);


        verify(checkoutService).checkoutProducts(products);
    }

    @Test
    public void testRunNoParameters() throws Exception{
        underTest.run();
        verify(output).print("Error:\nAt least one product needs to be selected.\nUse:\njava -jar supermarket-0.0.1-SNAPSHOT.jar {list of products}\n");
    }
}