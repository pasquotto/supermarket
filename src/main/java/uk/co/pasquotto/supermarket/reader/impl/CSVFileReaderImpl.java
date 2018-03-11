package uk.co.pasquotto.supermarket.reader.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import uk.co.pasquotto.supermarket.model.Product;
import uk.co.pasquotto.supermarket.reader.CSVFileReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class CSVFileReaderImpl implements CSVFileReader {
    @Override
    public List<Product> read(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) throw new ProductFileNotFoundException("File: " + filePath + " no found");

        List<Product> list;
        try {
            list = getList(f);
        } catch (IOException e) {
            throw new ProductFileNotFoundException("File: " + filePath + " could not be read", e);
        }
        return list;
    }


    private List<Product> getList(File file) throws IOException {
        try (Reader in = new FileReader(file)) {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(in)
                    .withType(Product.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }
}
