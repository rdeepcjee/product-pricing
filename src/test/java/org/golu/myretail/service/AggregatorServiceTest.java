package org.golu.myretail.service;

import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.CurrentProductPriceQuote;
import org.golu.common.domain.Product;
import org.golu.common.domain.ProductPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AggregatorServiceTest {
    @Mock
    private LocalProductServiceImpl productService;
    @Mock
    private LocalProductPriceServiceImpl productPriceService;
    @InjectMocks
    private AggregatorService aggregatorService;

    private List<ProductPrice> priceList;
    private List<Product> productList;
    private List<CurrentProductPriceQuote> currentProductPriceList;

    @BeforeEach
    public void setUp() {
        priceList = List.of(new ProductPrice(13860428, 13.49, "USD"),
                new ProductPrice(13860429, 14.39, "USD"),
                new ProductPrice(13860430, 19.43, "USD"));
        lenient().when(productPriceService.findProducts()).thenReturn(priceList);

        productList = List.of(new Product(13860428, "The Big Lebowski (Blu-ray) (Widescreen)", "Working Title Films"),
                new Product(13860429, "The Green Mile", "Castle Rock"),
                new Product(13860430, "Forrest Gump", "The Tisch Company"));
        lenient().when(productService.findProducts()).thenReturn(productList);

        currentProductPriceList = List.of(new CurrentProductPriceQuote(13860428, "The Big Lebowski (Blu-ray) (Widescreen)", new CurrentPrice(13.49, "USD")),
                new CurrentProductPriceQuote(13860429, "The Green Mile", new CurrentPrice(14.39, "USD")),
                new CurrentProductPriceQuote(13860430, "Forrest Gump", new CurrentPrice(19.43, "USD")));
    }

    @Test
    public void testListProducts() {
        assertTrue(aggregatorService.listProducts().containsAll(currentProductPriceList)
                    && currentProductPriceList.containsAll(aggregatorService.listProducts()));
    }
}
