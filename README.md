# Supermarket

Instruction on how to run it:

## Compile
``` mvn clean install ```

## configure
Create a file called `application.properties` and add the following configuration:

```product.repository.source='{absolute URI of the CSV file}'```

```rules.file='{absolute URI of the DRL file}' ```

## Product file CSV format
This file contains all the products and it's unit price of the supermarket.

 | id    | name | price  |
 | ------------- |--------------|---------------------|
 | A             | Apple        |                  160|
 | B             | Banana       |                  220|
 | C             | Carrot       |                   75|

## DRL file format
This file contains the rules to apply promotions over the products on the basket.


This is a role that can be used

```
rule "Product A 3 for 130"
    when

        $b : Basket( getQuantityOfProduct("A") >= 3);
    then
        $b.addDiscount(
                new Discount($b.getProduct("A"),
                        // 20p discount for every 3 products
                        fixedDiscountPerQuantity($b.getQuantityOfProduct("A"), 3, 20),
                        "3 for 130"
        ));
end
```

## Run:
` java -jar target/supermarket-0.0.1-SNAPSHOT.jar {List of Products in the basket}`

