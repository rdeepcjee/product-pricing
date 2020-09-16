package org.golu.myretail.service;

import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.ProductPrice;
import org.golu.myretail.exceptions.ResourceNotFoundException;
import org.golu.myretail.repository.ProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class LocalProductPriceServiceTest {

    @Mock
    private ProductPriceRepository repository;

    @InjectMocks
    private LocalProductPriceServiceImpl productPriceService;

    private List<ProductPrice> dataStoreEntries;

    @BeforeEach
    public void setUp() {
        dataStoreEntries = List.of(new ProductPrice(13860428, 13.49, "USD"),
                new ProductPrice(13860429, 14.39, "USD"),
                new ProductPrice(13860430, 91.43, "USD"));
        lenient().when(repository.findAll()).thenReturn(dataStoreEntries);
    }

    @Test
    public void testFindProductPrice_success() {
        Integer id = 13860428;
        ProductPrice price = new ProductPrice(13860428, 13.49, "USD");
        lenient().when(repository.findById(13860428)).thenReturn(Optional.of(price));
        assertEquals(price, productPriceService.findProductPrice(id));
    }

    @Test
    public void testFindProductPrice_failure() {
        Integer id = 13860431;
        lenient().when(repository.findById(13860431)).thenReturn(Optional.ofNullable(null));
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productPriceService.findProductPrice(id));
        assertEquals("Product with ID 13860431 not found in cache data store", exception.getMessage());
    }

    @Test
    public void testFindProducts() {
        assertEquals(dataStoreEntries, productPriceService.findProducts());
    }

    @Test
    public void testUpdateProductPrice() {
        Integer id = 13860431;
        CurrentPrice price = new CurrentPrice(13.58, "USD");
        assertDoesNotThrow(() -> {
            productPriceService.updateProductPrice(id, price);
        });
    }

}
