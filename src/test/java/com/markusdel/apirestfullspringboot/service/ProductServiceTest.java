package com.markusdel.apirestfullspringboot.service;

import com.markusdel.apirestfullspringboot.dto.UpdateProductDTO;
import com.markusdel.apirestfullspringboot.exception.ProductNotFoundException;
import com.markusdel.apirestfullspringboot.utils.ProductDTOBuilder;
import com.markusdel.apirestfullspringboot.dto.CreateProductDTO;
import com.markusdel.apirestfullspringboot.mapper.ProductMapper;
import com.markusdel.apirestfullspringboot.model.Product;
import com.markusdel.apirestfullspringboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    private ProductMapper mapper = ProductMapper.INSTANCE;

    @InjectMocks
    private ProductService service;


    @Test
    void quandoUmProdutoForCriado() {
        // given
        CreateProductDTO createProductDTO = ProductDTOBuilder.returnCreateDTOProduct();
        Product newProductCreated = mapper.toModel(createProductDTO);

        // when
        when(repository.save(newProductCreated)).thenReturn(ProductDTOBuilder.returnProductFromRepository());

        // then
        Product expectedProductCreated = service.createNewProduct(createProductDTO);

        assertThat(expectedProductCreated.getName(), is(equalTo(newProductCreated.getName())));
        assertThat(expectedProductCreated.getId(), is(equalTo(1L)));
    }

    @Test
    void quandoForBuscaUmProdutoPeloId() {
        Product findProductById = ProductDTOBuilder.returnProductFromRepository();

        // when
        when(repository.findById(1L)).thenReturn(Optional.of(findProductById));

        // then
        Product searchProductById = service.getProductById(1L);

        assertThat(searchProductById, is(equalTo(findProductById)));
    }

    @Test
    void quandoUmIDInvalidoForEnviado() {

        // given
        when(repository.findById(2L)).thenReturn(Optional.empty());

        // then
        assertThrows(ProductNotFoundException.class, () -> service.getProductById(2L));
    }

    @Test
    void quandoForSolicitadoAListaDeProdutos() {
        Product productInList = ProductDTOBuilder.returnProductFromRepository();

        // when
        when(repository.findAll()).thenReturn(List.of(productInList));

        // given
        List<Product> listProducts = service.getAllProduct();

        assertThat(listProducts, is(not(empty())));
        assertThat(listProducts.get(0), is(equalTo(productInList)));
    }


    @Test
    void quandoUmProdutoForAtualizado() {
        UpdateProductDTO updateProductDTO = ProductDTOBuilder.returnUpdateDTOProduct();
        Product oldProduct = ProductDTOBuilder.returnProductFromRepository();

        Product updateProduct = oldProduct;
        updateProduct.setPrice(updateProductDTO.getPrice());
        updateProduct.setAmount(updateProductDTO.getAmount());

        // when
        when(repository.findById(oldProduct.getId())).thenReturn(Optional.of(oldProduct));
        when(repository.save(updateProduct)).thenReturn(updateProduct);

        // given
        Product expectedProductUpdate = service.updateProduct(oldProduct.getId(), updateProductDTO);

        assertThat(expectedProductUpdate, is(equalTo(updateProduct)));
        verify(repository, times(1)).findById(oldProduct.getId());
        verify(repository, times(1)).save(updateProduct);
    }

    @Test
    void quandoUmProdutoForDeletado() {
        Product oldProduct = ProductDTOBuilder.returnProductFromRepository();

        // when
        when(repository.findById(oldProduct.getId())).thenReturn(Optional.of(oldProduct));
        doNothing().when(repository).delete(oldProduct);

        // given
        service.deleteProductById(oldProduct.getId());

        verify(repository, times(1)).findById(oldProduct.getId());
        verify(repository, times(1)).delete(oldProduct);
    }
}