package uk.co.pasquotto.supermarket.reader.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import uk.co.pasquotto.supermarket.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CSVFileReaderImplTest {

    private CSVFileReaderImpl underTest;

    @Before
    public void setUp() {
        underTest = new CSVFileReaderImpl();
    }

    @Test
    public void read() throws IOException {
        File f = new ClassPathResource("products.csv").getFile();

        List<Product> products = underTest.read(f.getAbsolutePath());

        assertNotNull(products);
        assertEquals("A", products.get(0).getId());
        assertEquals("Apple", products.get(0).getName());
        assertEquals(50D, products.get(0).getPrice(), 0.001D);

        assertEquals("B", products.get(1).getId());
        assertEquals("Banana", products.get(1).getName());
        assertEquals(30D, products.get(1).getPrice(), 0.001D);

        assertEquals("C", products.get(2).getId());
        assertEquals("Carrot", products.get(2).getName());
        assertEquals(20D, products.get(2).getPrice(), 0.001D);
    }

    @Test(expected = ProductFileNotFoundException.class)
    public void readNoFound() throws IOException {
        underTest.read("file.txt");
    }
}