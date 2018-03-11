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

 | Product ID    | Product name | Unit Price (pence)  |
 | ------------- |--------------|---------------------|
 | A             | Apple        |                  160|
 | B             | Banana       |                  220|
 | C             | Carrot       |                   75|

## DRL file format
This file contains the rules to apply promotions over the products on the basket.


TODO: Add an exemple of the file

## Run:
` java -jar target/supermarket-0.0.1-SNAPSHOT.jar {List of Products in the basket}`

