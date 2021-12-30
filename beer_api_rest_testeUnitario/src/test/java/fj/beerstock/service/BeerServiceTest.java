package fj.beerstock.service;

import fj.beerstock.dto.BeerDTO;
import fj.beerstock.entity.Beer;
import fj.beerstock.exception.BeerAlreadyRegisteredException;
import fj.beerstock.exception.BeerNotFoundException;
import fj.beerstock.exception.BeerStockExceededException;
import fj.beerstock.mapper.BeerMapper;
import fj.beerstock.repository.BeerRepository;
import fj.beerstock.builder.BeerDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// avisar a classe de teste que sera adiconado o Mockito
// para criar os objetos duble

@ExtendWith(MockitoExtension.class)

public class BeerServiceTest {

    private static final long INVALID_BEER_ID = 1L;

    @Mock
    // criar o mock da classe BeerRepository
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;
// ----------------------------------------------------------------------------------------------------------
// Testar
// Criação de cerveja

    // espera que tenha como padrão de entrada um DTO preenchido
    // foi utilizado o BerrDTObuilder , ja com valores preenchidos
    // // a ser usada em várias partes dos testes . A uxilia  nos testes e
    // ajuda a retornar um objeto com todos os valores preenchidos
    // rodar com debbuger para anaisar o passo a passo

    @Test
    void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
        // given
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedSavedBeer = beerMapper.toModel(expectedBeerDTO);

        // when
        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

        //then
        BeerDTO createdBeerDTO = beerService.createBeer(expectedBeerDTO);

        // Testando métodos parte 2
        // usando o Hamcrest
        assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId())));
        assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName())));

        // testando quantidade com hamcrest
        // Hamcrest permite verificar se valor é maior que outro
        // se um objeto é igual ao outro
        // vai alem do equals(), tem diversos tipos de validações
        assertThat(createdBeerDTO.getQuantity(), is(equalTo(expectedBeerDTO.getQuantity())));

    }
// ----------------------------------------------------------------------------------------------------------
// Testar
// Criação de cerveja -  cerveja já cadastrada, não fazer novo cadastro

    @Test
    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
        // given
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer duplicatedBeer = beerMapper.toModel(expectedBeerDTO);

        // when
        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

        // then
        // do JUNIT tem assertThrows
        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));
    }



 // ----------------------------------------------------------------------------------------------------------
// testar
// Pesquisa de Cerveja ( usar genetor do intellij -> Test method )

    @Test
    void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {

        // given
        BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

        // when
        // aproveitar a cerveja do DTO
        when(beerRepository.findByName(expectedFoundBeer.getName())).thenReturn(Optional.of(expectedFoundBeer));

        // then
        // declarar a excessão
        BeerDTO foundBeerDTO = beerService.findByName(expectedFoundBeerDTO.getName());

        // usndo o hamcrest
        // comparar direto no objeto DTO
        assertThat(foundBeerDTO, is(equalTo(expectedFoundBeerDTO)));
    }

 // ----------------------------------------------------------------------------------------------------------
// Testar
//tratar a se a exceção vai ser lançada quando uma cerveja nao registrada for pesquisada

    @Test
    void whenNotRegisteredBeerNameIsGivenThenThrowAnException() {
        // given
        BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

        // when
        when(beerRepository.findByName(expectedFoundBeerDTO.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(BeerNotFoundException.class, () -> beerService.findByName(expectedFoundBeerDTO.getName()));
    }

// ----------------------------------------------------------------------------------------------------------
// Testar
// a emissão de uma lista com dados  ou lista vazia

    @Test
    void whenListBeerIsCalledThenReturnAListOfBeers() {
        // given
        // insumos de entrada
        BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

        //when
        when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));

        //then
        List<BeerDTO> foundListBeersDTO = beerService.listAll();

        // hamcrest
        assertThat(foundListBeersDTO, is(not(empty())));
        assertThat(foundListBeersDTO.get(0), is(equalTo(expectedFoundBeerDTO)));
    }

// ----------------------------------------------------------------------------------------------------------
// Testar
// quando lista não retornar nehum resultado - lista vazia
// não precisa de DTO pois não há dados sendo passados

    @Test
    void whenListBeerIsCalledThenReturnAnEmptyListOfBeers() {
        //when
        when(beerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        // repository retorna uma lista vazia
        List<BeerDTO> foundListBeersDTO = beerService.listAll();

        assertThat(foundListBeersDTO, is(empty()));
    }

// ----------------------------------------------------------------------------------------------------------
// Testar
// exclusão de cervejas . passando o id da cerveja - delete
// deve retornar o codigo 204

    @Test
    void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted() throws BeerNotFoundException{
        // given
        // dados e entidade
        BeerDTO expectedDeletedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedDeletedBeer = beerMapper.toModel(expectedDeletedBeerDTO);

        // when
        when(beerRepository.findById(expectedDeletedBeerDTO.getId())).thenReturn(Optional.of(expectedDeletedBeer));
        // chamando metodo stanadr do mockito
        doNothing().when(beerRepository).deleteById(expectedDeletedBeerDTO.getId());

        // then
        beerService.deleteById(expectedDeletedBeerDTO.getId());
        // verify do mockito
        verify(beerRepository, times(1)).findById(expectedDeletedBeerDTO.getId());
        verify(beerRepository, times(1)).deleteById(expectedDeletedBeerDTO.getId());
    }


// ----------------------------------------------------------------------------------------------------------
// TDD - Desenvolvimento orientado por teste
// primeiro faço o teste e depois o codigo que passe no teste
// validar increnato de cerveja
    @Test
    void whenIncrementIsCalledThenIncrementBeerStock() throws BeerNotFoundException, BeerStockExceededException {
        //given
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);

        //when
        // como mehoria do teste
        // vamos pegar a cerveja no repositório
        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
        when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);

        int quantityToIncrement = 10;
        int expectedQuantityAfterIncrement = expectedBeerDTO.getQuantity() + quantityToIncrement;

        // then
        BeerDTO incrementedBeerDTO = beerService.increment(expectedBeerDTO.getId(), quantityToIncrement);

        assertThat(expectedQuantityAfterIncrement, equalTo(incrementedBeerDTO.getQuantity()));
        assertThat(expectedQuantityAfterIncrement, lessThan(expectedBeerDTO.getMax()));
    }


// ----------------------------------------------------------------------------------------------------------
// testar que o incremento não ultrapasse o estoque pemitido
//
    @Test
    void whenIncrementIsGreatherThanMaxThenThrowException() {
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);

        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));

        int quantityToIncrement = 80;
        assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement));
    }

// ----------------------------------------------------------------------------------------------------------
// testar exceção
// lançar uma exceção se a soma do incremnto com estoque atual é maior que  estoque pemitido
//
    @Test
    void whenIncrementAfterSumIsGreatherThanMaxThenThrowException() {
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);

        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));

        int quantityToIncrement = 45;
        assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement));
    }

// ----------------------------------------------------------------------------------------------------------
// testar se não foi chamado um id de cerveja inválido
//
//
    @Test
    void whenIncrementIsCalledWithInvalidIdThenThrowException() {
        int quantityToIncrement = 10;

        when(beerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty());

        assertThrows(BeerNotFoundException.class, () -> beerService.increment(INVALID_BEER_ID, quantityToIncrement));
    }

}
