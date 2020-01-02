package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CustomerResource {

  @Autowired
  private CustomerRepository repository;
  /*
   * Construtor do CustomerResource, preparando uma lista de produtos
   */
  public CustomerResource(CustomerRepository repository) {
    this.repository = repository;
  }

  /**
   * Metodo de requisicao do tipo GET, para uma lista
   * @param raca tipo de raca para filtrar
   * @return lista de produtos, filtrados ou nao
   */
  @RequestMapping(value = "/clientes/", method = RequestMethod.GET)
  public Iterable<Customer> buscarProdutos(@RequestParam(required = false) String raca) {
    return this.repository.findAll();
  }

  /**
   * Metodo de requisicao do tipo GET, para um item
   * @param id identificador ou indice da colecao dos produtos
   * @return item de produto unico
   */
  @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
  public Optional<Customer> buscarProduto(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  /**
   * Metodo de requisicao do tipo DELETE, para remover um item
   * @param id identificador ou indice da colecao dos produtos
   */
  @RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
  public void removerProduto(@PathVariable Long id) {
    this.repository.deleteById(id);
  }

  @RequestMapping(value = "/clientes/", 
  method = RequestMethod.POST)
  public Customer criarProduto(@RequestBody Customer Customer) {
    String nome = Customer.getFirstName();
    String sobrenome = Customer.getLastName();
    return this.repository.save(new Customer(nome, sobrenome));
  }

  @RequestMapping(value="/clientes/{id}", 
  method=RequestMethod.PUT)
  public void alterarProduto(@PathVariable Long id,
  @RequestBody Customer produtoParam) {
      Customer cliente = this.repository.findById(id).get();
      cliente.setFirstName(produtoParam.getFirstName());
      cliente.setLastName(produtoParam.getLastName());
  }

}
