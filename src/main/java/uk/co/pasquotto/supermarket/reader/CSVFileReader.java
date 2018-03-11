package uk.co.pasquotto.supermarket.reader;

import uk.co.pasquotto.supermarket.model.Product;

import java.util.List;

public interface CSVFileReader {
    List<Product> read(String filePath);
}
