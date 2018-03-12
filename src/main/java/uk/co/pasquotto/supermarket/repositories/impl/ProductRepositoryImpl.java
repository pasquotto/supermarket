package uk.co.pasquotto.supermarket.repositories.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import uk.co.pasquotto.supermarket.model.Product;
import uk.co.pasquotto.supermarket.reader.CSVFileReader;
import uk.co.pasquotto.supermarket.repositories.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Value("${product.repository.source:products.csv}")
    private String productFilePath = "products.csv";

    private CSVFileReader csvFileReader;

    private Map<String, Product> productsById = new HashMap<>();

    public ProductRepositoryImpl(CSVFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    @Override
    public Product getProductById(String id) {
        return productsById.get(id);
    }

    @PostConstruct
    public void setUp() {
        List<Product> productList = csvFileReader.read(productFilePath);
        productsById = productList.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }


}
